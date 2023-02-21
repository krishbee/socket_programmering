package sock;

import java.io.*;
import java.net.Socket;

public class ReadThread extends Thread {

    private Socket connSocket;
    private boolean trusted = false;
    private boolean correctPassword = false;
    private String password = "password";

    public ReadThread(Socket connSocket) {
        this.connSocket = connSocket;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connSocket.getOutputStream());

            if (reader.readLine().equals(password)) {
                outToClient.writeBytes(password + "\n");
                System.out.println("connection established");
                trusted = true;
            } else {
                System.out.println("Untrusted sender");
            }


            while (trusted) {
                if (reader.readLine().equals(password)) {
                }
                System.out.println(reader.readLine());

                if (reader.readLine().equals("fin")) {
                    System.out.println("connection closing...");
                    trusted = false;
                    outToClient.writeBytes("fin" + "\n");
                }
            }
            outToClient.writeBytes("fin" + "\n");
            reader.close();
            System.out.println("connection closed!");
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
