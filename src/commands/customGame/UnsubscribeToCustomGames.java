package commands.customGame;

import tablePackage.TableManager;
import clientPackage.Client;
import commands.Command;

public class UnsubscribeToCustomGames extends Command {

	private static boolean enabled = true;
	private static int counter = 0;
	
	@Override
	public void execute(Client client, String... params) {
		TableManager.unsubscribeClientToCustomGame(client);
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
