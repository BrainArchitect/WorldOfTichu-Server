package commands.customGame;

import tablePackage.TableManager;
import clientPackage.Client;
import commands.Command;

public class UnsubscribeToCustomGames implements Command {

	private static boolean enabled = true;
	
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
	public String getDescription() {
		String name = this.getClass().getName();
		name = name.substring(name.lastIndexOf('.')+1);
		String finalName = "";
		for (int i=0; i<name.length(); i++){
			if (Character.isUpperCase( name.charAt(i) ))
				finalName += " ";
			
			finalName += name.charAt(i);
		}
		return finalName;
	}

	@Override
	public String getCode() {
		return "3w";
	}

}
