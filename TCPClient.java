package sock;
import java.io.*;
import java.net.*;

public class TCPClient {

	public static void main(String argv[]) throws Exception{
//		String sentence;
//		String modifiedSentence;
//		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

//		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
//		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//		sentence = inFromUser.readLine();
//		outToServer.writeBytes(sentence + '\n');
//		modifiedSentence = inFromServer.readLine();
//		System.out.println("FROM SERVER: " + modifiedSentence);
//		clientSocket.close();
		/**
		 * UDP
		 */
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		String sentence = inFromUser.readLine();
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		clientSocket.receive(receivePacket);

		String clientIP = new String(receivePacket.getData());
		Socket clientConnectionSocket = new Socket(clientIP.trim(),6789);
		WriteThread writeThread = new WriteThread(clientConnectionSocket);
		ReadThread readThread = new ReadThread(clientConnectionSocket);
		writeThread.start();
		readThread.start();
	}
}


