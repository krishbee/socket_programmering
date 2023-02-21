package sock;
import java.net.*;
public class Server {
	
	/**
	 * @param args
	 */

	private String password;


	public static void main(String[] args)throws Exception {


		ServerSocket welcomeSocket = new ServerSocket(6789);
		Socket connectionSocket = welcomeSocket.accept();

		WriteThread writeThread = new WriteThread(connectionSocket);
		ReadThread readThread = new ReadThread(connectionSocket);
		writeThread.start();
		readThread.start();



	}

}
