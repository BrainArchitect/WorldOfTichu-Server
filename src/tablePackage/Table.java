package tablePackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import clientPackage.Client;

public abstract class Table implements Comparable<Table>{
	
	protected Client[] players;
	protected TreeSet<Client> observers = new TreeSet<Client>();
	protected TreeSet<Invitation> invitations = new TreeSet<Invitation>();
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
	
	public boolean inviteToTable(Client c){
		Invitation invitation = getInvitation(c);
		if(invitation==null){
			invitations.add(new Invitation(c));
			return true;
		}else if(invitation.reply.equals("Declined")){
			invitation.reply = "Pending";
			return true;
		}
		
		return false;

		
	}
	
	public boolean replyToInvitation(Client c, String reply){
		Invitation invitation = getInvitation(c);
		if(invitation!=null){
			invitation.reply = reply;
			return true;
		}
		return false;
	}
	

	
	
	public abstract boolean addObserver(Client c);
	public abstract void remove(Client c);

	public Client[] getPlayers() {
		return players;
	}
	
	public Client getPlayer(int seatNo) {
		if((seatNo<0) || (seatNo>3)){
			return null;
		}
		return players[seatNo];
	}

	public TreeSet<Client> getObservers() {
		return observers;
	}
	
	 public List<Client> getAllClients(){
		 List<Client> list = new ArrayList<Client>();
		 for(Client c: players){
			 if(c!=null){
				 list.add(c);
			 }
		 }
		  List<Client> list2 = new ArrayList<Client> (observers);
		  list.addAll(list2);
		  return list;
	}
	 
	 private Invitation getInvitation(Client c){
		 for(Invitation invitation : invitations){
			 if(invitation.client==c){
				 return invitation;
			 }
		 }
		 return null;
	 }
	 
	 public boolean isInvitationPending(Client c){
		 Invitation invitation = getInvitation(c);
		 if(invitation!=null){
			 return invitation.reply.equals("Pending");
		 }
		 return false;
		 
		
	 }
	 
	 class Invitation implements Comparable<Invitation>{
		 Client client;
		 String reply;

		 Invitation(Client client){
			 this.client = client;
			 reply = "Pending";
		 }

		@Override
		public int compareTo(Invitation other) {
			return this.client.getInfo().getUsername().compareTo(other.client.getInfo().getUsername());
		}

		 
	 }
	
	

	
}