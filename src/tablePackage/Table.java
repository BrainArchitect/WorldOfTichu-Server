package tablePackage;

import java.util.TreeSet;

import clientPackage.Client;

public abstract class Table implements Comparable<Table>{
	
	protected Client[] players;
	protected TreeSet<Client> observers = new TreeSet<Client>();
	protected boolean started; 

	private String id;
	
	
	public Table(String identifier){
		this.id = identifier;
		this.started = false;
	}
	
	public String getID(){
		return id;
	}

	@Override
	public int compareTo(Table other) {
		return this.id.compareTo(other.id);
	}
	
	public boolean isCustomTable(){
		return false;
	}
	
	public boolean isRankedTable(){
		return false;
	}
	
	public boolean isStarted(){
		return started;
	}
	
	
	public abstract boolean addObserver(Client c);
	public abstract void remove(Client c);
	

	
}