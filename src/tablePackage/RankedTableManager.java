package tablePackage;


import java.util.TreeSet;

import clientPackage.Client;

public class RankedTableManager {

	private static TreeSet<RankedTable> rankedTables = new TreeSet<RankedTable>();
	private static TreeSet<Client> rankedGameSubscribers = new TreeSet<Client>();

	
	public static synchronized boolean addTable(RankedTable table, Client host){
		return false;
	}
	
	public static synchronized void removeCustomTable(CustomTable table){

	}
	
	

	
	public static synchronized void tableValuesUpdated(String values){

	}

	public static int getCustomTablesSize(){ return rankedTables.size(); }

	
}
