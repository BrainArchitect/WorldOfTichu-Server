package mainPackage;

import java.net.ServerSocket;
import java.net.Socket;
import clientPackage.Client;



public class Main {		
	private static ServerSocket listener;	

	
	public Main(int listeningPort){
		try{
			listener = new ServerSocket(listeningPort);	// Initialize listener
		}catch (Exception e) {
			System.out.println("Can't create listening socket on port " + listeningPort);
			System.exit(1);
		}
		
		filotasTestingCodeArea();
		//okanTestingCodeArea();
		
		try{
			System.out.println("Listening on port: " + listeningPort);
			while (true){	// Start to listen...				
				System.out.println("Waiting for client...");
				Socket socket = listener.accept();	// A client was connected
				//Creating a new Client (
				new Client(socket);

				System.out.println("Connection accepted: " + socket.getInetAddress()+"\n");
			}
		}catch (Throwable e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		if (args.length == 1) {
			try{
				int port = Integer.parseInt(args[0]);
				if (port <=0 || port > 65535)
					throw new NumberFormatException();
				new Main(port);
			}catch (Exception e) {}
		}else
			new Main(12345);
	}
	
	
	/**
	 * FOR TESTING PURPOSES ONLY
	 */
	private void filotasTestingCodeArea() {
		Database.emptyTheDatabase();
		Database.initDatabaseForTesting();
		Database.printUsernames();

	}
	
	
	/**
	 * FOR TESTING PURPOSES ONLY
	 */
	private void okanTestingCodeArea(){
		
	}
	
	
}
