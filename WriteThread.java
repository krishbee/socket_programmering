package sock;

import java.io.*;
import java.net.Socket;

public class WriteThread extends Thread{

    private Socket connSocket;

    public WriteThread(Socket connSocket){
        this.connSocket = connSocket;
    }

    public void run(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            DataOutputStream write = new DataOutputStream(connSocket.getOutputStream());
            while (true){
                write.writeBytes(bufferedReader.readLine() + "\n");
            }
        } catch (IOException e){

        }

    }
}
