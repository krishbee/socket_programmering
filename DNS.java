package sock;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class DNS {

    private static ArrayList<Bruger> brugere = new ArrayList<>();

    public static ArrayList<Bruger> getBrugere() {
        return new ArrayList<>(brugere);
    }

    public static String getBrugerIP(String navn){
        String ip = "";
        for (Bruger b : getBrugere()){
            if (b.getNavn().equals(navn.trim())){
                ip = b.getIp();
            }
        }
        return ip;
    }

    public static void initRegister(){
        Bruger mia = new Bruger("mia","10.10.138.188");
        Bruger seb = new Bruger("seb", "10.10.136.255");
        Bruger kris = new Bruger("kris", "10.10.140.199");

        brugere.add(mia);
        brugere.add(seb);
        brugere.add(kris);
    }

    public static void main(String[] args) throws IOException {
        initRegister();
        DatagramSocket dnsServerSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            dnsServerSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String brugerIP = getBrugerIP(sentence);
            sendData = brugerIP.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, port);
            dnsServerSocket.send(sendPacket);
        }
    }


}
