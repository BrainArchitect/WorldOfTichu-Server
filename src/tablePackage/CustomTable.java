package tablePackage;

import game.Game;
import game.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import clientPackage.Client;

public class CustomTable implements Comparable<CustomTable>{
	
	private Client[] seatedClients;
	private TreeSet<Client> observers = new TreeSet<Client>();
	private TreeSet<Invitation> invitations = new TreeSet<Invitation>();
	private boolean started; 
	private Game game;

	private String id;
	
	
	public CustomTable(String identifier){
		this.id = identifier;
		this.started = false;
		this.seatedClients = new Client[4];
	}
	

	
	public String getID(){
		return id;
	}

	@Override
	public int compareTo(CustomTable other) {
		return this.id.compareTo(other.id);
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
	

	
	
	public boolean addHost(Client c){
		if(c!=null){
			seatedClients[0]= c;
			c.setTable(this);
		}

		return false;
	}
	
//====================================================================================
	public boolean addObserver(Client c){
		if(c!=null && doesNotExist(c)){
			boolean success = observers.add(c);
			
			if(success){
				c.setTable(this);
				CustomTableManager.unsubscribeClientToCustomGame(c);
				
				//Send message with the table details to the observer that have now joined this table
				//*************************************************************************************************
				String msgTableDetails="3c1R~"+id+"~"+ c.getInfo().getUsername() +"~"+getNumOfPlayers()+"~"+observers.size()+"~"+invitations.size()+"~";
				for(int sitNo=0;sitNo<4;sitNo++){
					if(seatedClients[sitNo]!=null){
						msgTableDetails = msgTableDetails + sitNo +"~"+ seatedClients[sitNo].getInfo().getUsername()+"~";
					}
				}
				for(Client client : observers){
					msgTableDetails = msgTableDetails + client.getInfo().getUsername()+"~";
				}
				
				for(Invitation invitation : invitations){
					msgTableDetails = msgTableDetails + invitation.client.getInfo().getUsername()+"~" + invitation.reply+"~";
				}
				msgTableDetails = msgTableDetails + "\n";	
				c.send(msgTableDetails);
				//*************************************************************************************************

				//Send message to the players and the observers of the table that someone joined
				//*************************************************************************************************
				String messageToSend = "3c2R~"+c.getInfo().getUsername()+"~\n";
				this.sendMessageBut(messageToSend,c);
				//*************************************************************************************************
				
				
			}
			return success;
		}
		return false;
	}
//====================================================================================	
	public void remove(Client client){
		
		boolean success = observers.remove(client);
		
		if(success){
			client.send("3f1R~\n");
			this.sendMessageBut("3f2R~"+client.getInfo().getUsername()+"~\n", client);
		}else{
			if(seatedClients[0]==client){
					CustomTableManager.removeCustomTable(this);
					this.sendMessage("3k1R~\n");
					for(int sitNo=0;sitNo<4;sitNo++){
						if(seatedClients[sitNo]!=null){
							seatedClients[sitNo].setTable(null);
						}
						for(Client c : observers){
							c.setTable(null);
						}
					}
				
			}else{
				for(int sitNo=1;sitNo<4;sitNo++){
					if(seatedClients[sitNo]== client){
						seatedClients[sitNo]=null;
						success =true;
						this.smnGotUp(client);
						client.send("3f1R~\n");
						this.sendMessageBut("3f2R~"+client.getInfo().getUsername()+"~\n", client);
						break;
					}
				}
			}
		}
		
				
		

		

	}
	
//====================================================================================
	public boolean smnSitDown(Client c, int sitNo){
		if(sitNo<0 || sitNo>3 || c==null){
			return false;
		}
		

							
		if(seatedClients[sitNo]==null && doesNotSitAnyWhere(c)){
			CustomTableManager.unsubscribeClientToCustomGame(c);
			

			observers.remove(c);
			seatedClients[sitNo]= c;
			
			String message = "3dR~"+sitNo+"~"+c.getInfo().getUsername()+"~\n";
			this.sendMessage(message);

			message = "3uR~"+id+"~"+this.getNumOfPlayers()+"~\n";
			CustomTableManager.tableValuesUpdated(message);

			return true;
		}
		
		return false;
	}
	
//====================================================================================
	public boolean smnGotUp(Client c){
		
		for(int sitNo=0; sitNo<4; sitNo++){
			if(seatedClients[sitNo]==c){
				observers.add(c);
				seatedClients[sitNo]= null;
				this.sendMessage("3eR~"+sitNo+"~"+c.getInfo().getUsername()+"~\n");
				CustomTableManager.tableValuesUpdated("3uR~"+id+"~"+this.getNumOfPlayers()+"~\n");
				return true;
			}
		}

		return false;
	}
//====================================================================================
		public void sendText(Client client, String text){
			if(!doesNotExist(client)){
				sendMessage("3iR~"+client.getInfo().getUsername()+"~"+text+"~\n");
			}
		}
//====================================================================================

	
	public void startGame(Client c){

		if(seatedClients[0]==c && getNumOfPlayers()==4){
			started = true;
			Player[] players = new Player[4];
			for(int i=0; i<4;i++){
				players[i] = seatedClients[i].getPlayer();
			}
			game = new Game(players);
			game.start();
			sendMessage("3jR~\n");
			//MORE CODE NEEDED
			
		}
		
	}
	
	public int getNumOfPlayers(){
		int sum = 0;
		for(Client c : seatedClients){
			if(c!=null){
				sum++;
			}
		}
		return sum;
	}
	
	
	protected void sendMessage(String message){
		for (Client c : seatedClients){
			if (c!=null){
				c.send(message);
			}
		}
		for (Client c : observers){
			c.send(message);
		}
		
	}
	
	
	protected void sendMessageBut(String message, Client client){
		for (Client c : seatedClients){
			if (c!=null && c!=client){
				c.send(message);
			}
		}
		for (Client c : observers){
			if (c!=client){
				c.send(message);
			}
		}
		
	}
	
	
	
	protected boolean doesNotExist(Client client){
		for(Client c : seatedClients){
			if(client == c){
				return false;
			}
		}
		
		return !observers.contains(client);
	}
	
	protected boolean doesNotSitAnyWhere(Client client){
		for(Client c : seatedClients){
			if(client == c){
				return false;
			}
		}
		return true;
	}

	public Client[] getClients() {
		return seatedClients;
	}
	
	public Client getClient(int seatNo) {
		if((seatNo<0) || (seatNo>3)){
			return null;
		}
		return seatedClients[seatNo];
	}

	public TreeSet<Client> getObservers() {
		return observers;
	}
	
	 public List<Client> getAllClients(){
		 List<Client> list = new ArrayList<Client>();
		 for(Client c: seatedClients){
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