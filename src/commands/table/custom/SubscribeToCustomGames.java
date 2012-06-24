package commands.table.custom;

import table.CustomTable;
import client.Client;
import commands.Command;

public class SubscribeToCustomGames extends Command{

	private static boolean enabled = true;
	private static int counter = 0;
	
	@Override
	public void execute(Client client, String... params) {
		CustomTable.subscribeClient(client);	
	}

	@Override
	public boolean isEnabled() {
		return SubscribeToCustomGames.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		SubscribeToCustomGames.enabled = enabled;
	}

	@Override
	public String getCode() {
		return "3a";
	}

	@Override
	public void increaseCounter() {
		counter++;
	}

	@Override
	public long getCounter() {
		return counter;
	}

}
