package table;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import client.Client;

public abstract class Table {

	
	private Client[] seatedClients;
	private TreeSet<Client> observers = new TreeSet<Client>();
	private TreeSet<Invitation> invitations = new TreeSet<Invitation>();
	private boolean started; 
	

	private String id;
	
	
	public Table(String identifier){
		this.id = identifier;
		this.started = false;
		this.seatedClients = new Client[4];
	}
	
	
	
	
	
	
	public abstract void startGame(Client c);
	public abstract boolean addHost(Client c);

	
	
	public boolean smnGotUp(Client c){
		for(int sitNo=0; sitNo<4; sitNo++){
			if(getClient(sitNo)==c){
				observers.add(c);
				seatedClients[sitNo]= null;
				this.sendMessage("3eR~"+sitNo+"~"+c.getInfo().getUsername()+"~\n");
				return true;
			}
		}

		return false;
	}
	
	public boolean smnSitDown(Client c, int sitNo){
		if(sitNo<0 || sitNo>3 || c==null){
			return false;
		}
		
		if(seatedClients[sitNo]==null && doesNotSitAnyWhere(c)){
			observers.remove(c);
			seatedClients[sitNo]= c;
			
			String message = "3dR~"+sitNo+"~"+c.getInfo().getUsername()+"~\n";
			this.sendMessage(message);

			

			return true;
		}
		
		return false;
	}
	
	public boolean addObserver(Client c){
		if(c!=null && doesNotExist(c)){
			boolean success = observers.add(c);
			
			if(success){
			
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
	
	
public void remove(Client client){
		
		boolean success = observers.remove(client);
		
		if(success){
			client.send("3f1R~\n");
			this.sendMessageBut("3f2R~"+client.getInfo().getUsername()+"~\n", client);
		}else{
			if(seatedClients[0]==client){
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
	
	
	

	public int getNumOfPlayers(){
		int sum = 0;
		for(Client c : seatedClients){
			if(c!=null){
				sum++;
			}
		}
		return sum;
	}
	
	
	public void sendMessage(String message){
		for (Client c : seatedClients){
			if (c!=null){
				c.send(message);
			}
		}
		for (Client c : observers){
			c.send(message);
		}
		
	}
	
	
	public void sendMessageBut(String message, Client client){
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
	
	public void sendText(Client client, String text){
		if(!doesNotExist(client)){
			sendMessage("3iR~"+client.getInfo().getUsername()+"~"+text+"~\n");
		}
	}
	
	
	
	public boolean doesNotExist(Client client){
		for(Client c : seatedClients){
			if(client == c){
				return false;
			}
		}
		
		return !observers.contains(client);
	}
	
	public boolean doesNotSitAnyWhere(Client client){
		for(Client c : seatedClients){
			if(client == c){
				return false;
			}
		}
		return true;
	}


	
	public void setClient(Client c, int seatNo) {
		if((seatNo<0) || (seatNo>3)){
			seatedClients[seatNo]=c;
		}
	}
	
	public boolean isStarted(){
		return started;
	}
	
	public void setStarted(boolean started){
		this.started = started;
	}
	

	
	public String getID(){
		return id;
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
	 
	 public Invitation getInvitation(Client c){
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
