package tablePackage;


import java.util.TreeSet;

import clientPackage.Client;

public class CustomTableManager {

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
		CustomTable t = client.getTable();
		if(t!=null){
			
		}
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
			CustomTableManager.unsubscribeClient(host);
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
