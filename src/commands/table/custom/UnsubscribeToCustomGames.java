package commands.table.custom;

import table.CustomTableManager;
import client.Client;
import commands.Command;

public class UnsubscribeToCustomGames extends Command {

	private static boolean enabled = true;
	private static int counter = 0;
	
	@Override
	public void execute(Client client, String... params) {
		CustomTableManager.unsubscribeClient(client);
	}

	@Override
	public boolean isEnabled() {
		return UnsubscribeToCustomGames.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		UnsubscribeToCustomGames.enabled = enabled;
	}

	@Override
	public String getCode() {
		return "3w";
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
