package table;

import java.util.TreeSet;

import client.Client;

public class RankedTable extends Table{

	
	public RankedTable(String identifier) {
		super(identifier);

	}

	@Override
	public void startGame(Client c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addHost(Client c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//========================================================================================================
	//RankedTableManager
	//========================================================================================================

	private static TreeSet<RankedTable> rankedTables = new TreeSet<RankedTable>();


	
	public static synchronized boolean add(RankedTable table, Client host){
		return false;
	}
	
	public static synchronized void remove(RankedTable table){

	}
	
	
	public static int getRankedTablesSize(){ return rankedTables.size(); }


}
