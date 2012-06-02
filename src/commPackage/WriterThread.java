package commPackage;

import java.io.PrintWriter;
import java.net.Socket;

import commands.account.Logout;

import clientPackage.Client;

public class WriterThread extends Thread {
	
	private Client client;
	private PrintWriter out;
	
	public WriterThread(Client client, Socket socket){
		this.client = client;
		try{
			out = new PrintWriter(socket.getOutputStream());
		}catch (Exception e) {}
	}
	
	public void run() {		
		try{
			while (!client.isClosed()) {	// while client is not closed
			
				String messageToSend;
				
				synchronized(client) {
					messageToSend = client.getMessageOut();	// Get the message
					client.setMessageOut("");
				}
				
				if (client.isClosed())	// Check again if client is closed
					break; // If it is exit and don't send the message
					
				if (messageToSend.length() > 0){// check if message length is >0
					out.print(messageToSend);	// If it is finally send the message
					if(client.getInfo()!=null){
						System.out.println("Message: "+ messageToSend + "send to: " + client.getInfo().getUsername());
					}
					out.flush();		
					if (out.checkError())
						throw new Exception("Error while sending to client.");
				}
					
				// Sleep for some time
				synchronized(client) {
					if (client.getMessageOut().length() == 0) {
						try{
							client.wait(10*(50+(int)(15*Math.random()))*1000);
						}
						catch (InterruptedException e) {}
					}
				}
			}
		}catch (Exception e) {
			new Logout().execute(client, new String[]{});
		}
	}
}
