package table;

import java.util.TreeSet;

import game.Player;

import client.Client;

public class CustomTable extends Table implements Comparable<CustomTable>{
	
	
	public CustomTable(String identifier){
		super(identifier);
	}	


	@Override
	public int compareTo(CustomTable other) {
		return this.getID().compareTo(other.getID());
	}
	
	
	
	
	public boolean addHost(Client c){
		if(c!=null){
			this.setClient(c, 0);
			c.setTable(this);
		}
		return false;
	}
	
	

	
//====================================================================================
	public boolean addObserver(Client c){
		boolean success = super.addObserver(c);
		if(success){
			c.setTable(this);
			unsubscribeClient(c);
		}
		
		return success;
	}
//====================================================================================	
	
	public void remove(Client client){
		super.remove(client);
		if(getClient(0)==client){
			remove(this);
		}
	}
	
	public boolean smnGotUp(Client c){
		boolean success = super.smnGotUp(c);
		if(success){
			tableValuesUpdated("3uR~"+getID()+"~"+getNumOfPlayers()+"~\n");
		}
		return success;
			
	}
	
	
	public boolean smnSitDown(Client c, int sitNo){
		boolean success = super.smnSitDown(c,sitNo);
		if(success){
			unsubscribeClient(c);
			String message = "3uR~"+getID()+"~"+getNumOfPlayers()+"~\n";
			tableValuesUpdated(message);
		}
		return success;
	}
	
	
//====================================================================================

	
	public void startGame(Client c){
		if(getClient(0)==c && this.getNumOfPlayers()==SIZE){
			setStarted(true);
			Player[] players = new Player[SIZE];
			for(int i=0; i<SIZE;i++){
				players[i] = getClient(i).getPlayer();
			}
			sendMessage("3jR~\n");
			//MORE CODE NEEDED
			
		}else{
			//MatchMaking code
		}
		
	}
	
	//========================================================================================================
	//CustomTableManager
	//========================================================================================================
	private static TreeSet<CustomTable> customTables = new TreeSet<CustomTable>();
	private static TreeSet<Client> customGameSubscribers = new TreeSet<Client>();

	
	
	public static synchronized boolean subscribeClient(Client client){
		boolean success = customGameSubscribers.add(client);
		
		if(success){
			String message = "3aR~" + customTables.size() + "~";
			System.out.println("Custom tables size:" +customTables.size());
			if(customTables.size()!=0){
				for (CustomTable table : customTables){
					message = message + table.getID()+"~"+table.getNumOfPlayers()+"~";
				}
			}
			message = message+"\n";
			client.send(message);
		}
		
		return success;
	}
	
	public static synchronized void unsubscribeClient(Client client){
		customGameSubscribers.remove(client);
	}
	
	public static synchronized boolean add(CustomTable table, Client host){
		//If host has already a table 
		if(host.getTable()!=null){
			//then he cannot create another.
			String msg ="3bERR~"+"You are already on a table.~\n";
			host.send(msg);
			return false;
		}
		
		boolean success = customTables.add(table);
		if(success){
			table.addHost(host);
			host.send("3b1R~"+table.getID()+"~"+host.getInfo().getUsername()+"~\n");
			unsubscribeClient(host);
			for(Client c: customGameSubscribers){
				c.send("3b2R~"+table.getID()+"~1~\n");
			}
			
		}else{
			String msg ="3bERR~"+"A table with name "+ table.getID()+" already exists! Try again by giving another name.~\n";
			host.send(msg);
		}
		return success;
	}
	
	public static synchronized void remove(CustomTable table){
		customTables.remove(table);
		for(Client tempClient : customGameSubscribers){
			tempClient.send("3k2R~"+table.getID()+"~\n");
		}
	}
	
	
	
	public static synchronized TreeSet<CustomTable> getCustomTables(){
		return customTables;
	}
	
	
	public static synchronized CustomTable getCustomTable(String tableName){
		
		for(CustomTable table: customTables){
			if(table.getID().equals(tableName)){
				return table;
			}
		}
		return null;
	}
	
	public static synchronized void tableValuesUpdated(String values){
		for(Client client : customGameSubscribers){
				client.send(values);

		}
	}

	public static int getCustomTablesSize(){ return customTables.size(); }


	
}