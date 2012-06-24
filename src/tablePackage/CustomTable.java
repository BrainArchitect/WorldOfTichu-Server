package tablePackage;

import game.Player;

import clientPackage.Client;

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
			CustomTableManager.unsubscribeClient(c);
		}
		
		return success;
	}
//====================================================================================	
	
	public void remove(Client client){
		super.remove(client);
		if(getClient(0)==client){
			CustomTableManager.remove(this);
		}
	}
	
	public boolean smnGotUp(Client c){
		boolean success = super.smnGotUp(c);
		if(success){
			CustomTableManager.tableValuesUpdated("3uR~"+getID()+"~"+getNumOfPlayers()+"~\n");
		}
		return success;
			
	}
	
	
	public boolean smnSitDown(Client c, int sitNo){
		boolean success = super.smnSitDown(c,sitNo);
		if(success){
			CustomTableManager.unsubscribeClient(c);
			String message = "3uR~"+getID()+"~"+getNumOfPlayers()+"~\n";
			CustomTableManager.tableValuesUpdated(message);
		}
		return success;
	}
	
	
//====================================================================================

	
	public void startGame(Client c){
		if(getClient(0)==c && this.getNumOfPlayers()==4){
			setStarted(true);
			Player[] players = new Player[4];
			for(int i=0; i<4;i++){
				players[i] = getClient(i).getPlayer();
			}
			sendMessage("3jR~\n");
			//MORE CODE NEEDED
			
		}else{
			//MatchMaking code
		}
		
	}


	
}