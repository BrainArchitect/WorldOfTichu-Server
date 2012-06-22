package tablePackage;

import clientPackage.Client;

public class CustomTable extends Table {
	
	
	/*private int pointLimit;
	private int timeLimit;
	private boolean passwordProtected;
	private String password;*/
	
	public CustomTable(String identifier){
		super(identifier);
		seatedClients = new Client[4];
	}
	
	public boolean isCustomTable(){		
		return true;
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
				TableManager.unsubscribeClientToCustomGame(c);
				
				//Send message with the table details to the observer that have now joined this table
				//*************************************************************************************************
				String msgTableDetails="3c1R~"+super.getID()+"~"+ c.getInfo().getUsername() +"~"+getNumOfPlayers()+"~"+observers.size()+"~"+invitations.size()+"~";
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
				TableManager.removeCustomTable(this);
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
			TableManager.unsubscribeClientToCustomGame(c);
			

			observers.remove(c);
			seatedClients[sitNo]= c;
			
			String message = "3dR~"+sitNo+"~"+c.getInfo().getUsername()+"~\n";
			this.sendMessage(message);

			message = "3uR~"+super.getID()+"~"+this.getNumOfPlayers()+"~\n";
			TableManager.tableValuesUpdated(message);

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
				TableManager.tableValuesUpdated("3uR~"+super.getID()+"~"+this.getNumOfPlayers()+"~\n");
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
	
	public String getID(){
		return super.getID();
	} 
	
	public void startGame(Client c){

		if(seatedClients[0]==c && getNumOfPlayers()==4){
			started = true;
			Player[] players = new Player[4];
			for(int i=0; i<4;i++){
				players[i] = new Player(seatedClients[i], this);
			}
			game = new Game();
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
	
	
	private void sendMessage(String message){
		for (Client c : seatedClients){
			if (c!=null){
				c.send(message);
			}
		}
		for (Client c : observers){
			c.send(message);
		}
		
	}
	
	
	private void sendMessageBut(String message, Client client){
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
	
	
	
	private boolean doesNotExist(Client client){
		for(Client c : seatedClients){
			if(client == c){
				return false;
			}
		}
		
		return !observers.contains(client);
	}
	
	private boolean doesNotSitAnyWhere(Client client){
		for(Client c : seatedClients){
			if(client == c){
				return false;
			}
		}
		return true;
	}
	
	




//====================================================================================
	
	

}
