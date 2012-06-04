package clientPackage;
import java.net.Socket;
import java.util.TreeSet;

import tablePackage.Table;

import mainPackage.Database;

import commPackage.ReaderThread;
import commPackage.WriterThread;

public class Client implements Comparable<Client>{

	private static TreeSet<Client> clients = new TreeSet<Client>(); // all connected clients
	
	private TreeSet<Client> onlineContacts = new TreeSet<Client>(); //online contacts of THIS client
	
	private Socket socket;
	private boolean closed = false;
	private String messageOut = "";
	
	private ReaderThread readerThread;
	private WriterThread writerThread;
	
	private Info info;
	private Statistics statistics;
	private Table table;
	
	
	public Client(Socket socket){

		this.socket = socket;
		readerThread = new ReaderThread(this, socket);
		writerThread = new WriterThread(this, socket);
		
		readerThread.start();
		writerThread.start();
	}
	
	
	// Getters
	public Info getInfo (){ return this.info; }
	public Statistics getStatistics() { return this.statistics; }
	public synchronized String getMessageOut() { return this.messageOut; }
	public Socket getSocket(){ return this.socket; }
	public Table getTable(){return table;}
	
	// Setters
	public void setInfo(Info info) { this.info = info; }
	public void setStatistics(Statistics statistics) { this.statistics = statistics; }
	public synchronized void setMessageOut(String messageOut) { this.messageOut = messageOut; }
	public boolean isClosed(){ return this.closed; }
	public void setTable(Table aTable){table = aTable;}
	
	/**
	 * Method to close connection to client.
	 * This happens if client wants to close
	 * the connection or if an error occurs.
	 */
	public synchronized void close() {
		if (!closed){	// If client is not closed
			closed = true;
			try {
				socket.close();	// close the connection
				System.out.println("Connection to client was closed.");
			}
			catch (Exception e) {}
			notify();	// Notify thread to wake up and close
		}
	}
	
	
	/**
	 * Method to send message to client
	 * @param message
	 */
	public synchronized void send(String message) {
		messageOut += message;
		notify();
	}
	
	public static synchronized boolean addClient(Client client){
		return clients.add(client);
	}
	
	public static synchronized void removeClient(Client client){
		clients.remove(client);
	}
	
	public static int getClientsSize() { return clients.size(); }
	
	public static synchronized Client getClient(String username){
		for(Client client: clients){
			if(client.info.getUsername().equals(username)){
				return client;
			}
		}
		return null;
	}
	
	
	public synchronized void addOnlineContact(Client client){
		onlineContacts.add(client);
	}
	
	
	public synchronized TreeSet<String> getOnlineUsernames(){
		TreeSet<String> onlineUsernames = new TreeSet<String>();
		if(onlineContacts.size() != 0){
			for(Client client: onlineContacts){
				onlineUsernames.add(client.info.getUsername());
			}
		}
		return onlineUsernames;
	}
	
	
	public TreeSet<Client> getOnlineContacts(){
		return onlineContacts;
	}
	

	
	
	public synchronized void removeOnlineContact(Client client){
		onlineContacts.remove(client);
	}
	
	public int getOnlineContactsSize() { return onlineContacts.size(); }
	
	public synchronized void registerStatisctics(Statistics statistics){
		Database.registerStatisctics(statistics);
	}
	
	public synchronized TreeSet<String> loadOnlineContacts(){
		try{
			TreeSet<String> usernames = Database.getContacts(this.info.getUsername());
			TreeSet<String> offlineUsernames = new TreeSet<String>();
			
			if(usernames == null || usernames.size() == 0)
				return offlineUsernames;
									
			boolean ended = false;

			Client tempClient = clients.first();
			for(String username : usernames){
				if(!ended){
					boolean flag = true;
					while (flag && !ended){
	
						int comparison = tempClient.info.getUsername().compareTo(username);

						if(comparison == 0){
							onlineContacts.add(tempClient);
							flag = false;
						}else if(comparison < 0){
							tempClient = clients.higher(tempClient);
							if(tempClient==null){
								ended=true;
								offlineUsernames.add(username);
							}
						}else{
							offlineUsernames.add(username);
							flag = false;
						}
					}
				}else{
					offlineUsernames.add(username);
				}
			}
			return offlineUsernames;
		}catch(Exception e){
			e.printStackTrace();
		}		
		return null;
	}


	@Override
	public int compareTo(Client anotherClient) {
		try{
			return this.info.getUsername().compareTo(anotherClient.info.getUsername());
		}catch(Exception e){
			//NullPointerException
			return 0;
		}

	}
}
