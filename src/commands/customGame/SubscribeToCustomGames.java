package commands.customGame;

import tablePackage.TableManager;
import clientPackage.Client;
import commands.Command;

public class SubscribeToCustomGames extends Command{

	private static boolean enabled = true;
	
	@Override
	public void execute(Client client, String... params) {
		TableManager.subscribeClientToCustomGame(client);	
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

}
