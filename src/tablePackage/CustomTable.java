package tablePackage;

import clientPackage.Client;

public class CustomTable extends Table {
	
	
	/*private int pointLimit;
	private int timeLimit;
	private boolean passwordProtected;
	private String password;*/
	
	public CustomTable(String identifier){
		super(identifier);
		players = new Client[4];
	}
	
	public boolean isCustomTable(){		
		return true;
	}
	
	
	public boolean addHost(Client c){
		if(c!=null){
			players[0]= c;
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
					if(players[sitNo]!=null){
						msgTableDetails = msgTableDetails + sitNo +"~"+ players[sitNo].getInfo().getUsername()+"~";
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
			if(players[0]==client){
				TableManager.removeCustomTable(this);
				this.sendMessage("3k1R~\n");
				for(int sitNo=0;sitNo<4;sitNo++){
					if(players[sitNo]!=null){
						players[sitNo].setTable(null);
					}
					for(Client c : observers){
						c.setTable(null);
					}
				}
				
				
			}else{
				for(int sitNo=1;sitNo<4;sitNo++){
					if(players[sitNo]== client){
						players[sitNo]=null;
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
		

							
		if(players[sitNo]==null && doesNotSitAnyWhere(c)){
			TableManager.unsubscribeClientToCustomGame(c);
			

			observers.remove(c);
			players[sitNo]= c;
			
			String message = "3d1R~"+sitNo+"~"+c.getInfo().getUsername()+"~\n";
			c.send(message);
			message = "3d2R~"+sitNo+"~"+c.getInfo().getUsername()+"~\n";
			this.sendMessageBut(message,c);

			message = "3uR~"+super.getID()+"~"+this.getNumOfPlayers()+"~\n";
			TableManager.tableValuesUpdated(message);

			return true;
		}
		
		return false;
	}
	
//====================================================================================
	public boolean smnGotUp(Client c){
		
		for(int sitNo=0; sitNo<4; sitNo++){
			if(players[sitNo]==c){
				observers.add(c);
				players[sitNo]= null;
				c.send("3e1R~"+sitNo+"~"+c.getInfo().getUsername()+"~\n");
				this.sendMessageBut("3e2R~"+sitNo+"~"+c.getInfo().getUsername()+"~\n",c);
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

		if(players[0]==c && getNumOfPlayers()==4){
			started = true;
			sendMessage("3jR~\n");
			//MORE CODE NEEDED
		}
		
	}
	
	public int getNumOfPlayers(){
		int sum = 0;
		for(Client c : players){
			if(c!=null){
				sum++;
			}
		}
		return sum;
	}
	
	
	private void sendMessage(String message){
		for (Client c : players){
			if (c!=null){
				c.send(message);
			}
		}
		for (Client c : observers){
			c.send(message);
		}
		
	}
	
	
	private void sendMessageBut(String message, Client client){
		for (Client c : players){
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
		for(Client c : players){
			if(client == c){
				return false;
			}
		}
		
		return !observers.contains(client);
	}
	
	private boolean doesNotSitAnyWhere(Client client){
		for(Client c : players){
			if(client == c){
				return false;
			}
		}
		return true;
	}
	
	




//====================================================================================
	
	

}
