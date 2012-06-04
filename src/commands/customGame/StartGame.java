package commands.customGame;

import tablePackage.*;
import clientPackage.Client;
import commands.Command;

public class StartGame extends Command {

	private static boolean enabled = true;
	
	@Override
	public void execute(Client client, String... params) {
		if(!enabled){
			return;
		}
		
		Table table = client.getTable();
		if(table!=null && table.isCustomTable() && !table.isStarted()){
			((CustomTable) table).startGame(client);
		}
	}

	@Override
	public boolean isEnabled() {
		return StartGame.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		StartGame.enabled = enabled;
	}

	@Override
	public String getDescription() {
		return super.getDescription(this);
	}

	@Override
	public String getCode() {
		return "3j";
	}
}
