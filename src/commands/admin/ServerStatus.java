package commands.admin;

import tablePackage.TableManager;
import clientPackage.Client;
import commands.Command;

public class ServerStatus implements Command {

	private static boolean enabled = true;
	
	@Override
	public void execute(Client client, String... params) {
		int size[] = {Client.getClientsSize(), TableManager.getCustomTablesSize(), TableManager.getRankedTablesSize() };
		
		String output = "";
		for (int i=0; i<size.length; i++)
			output += size[i] + "~";
		client.send("69bR~" + output + "\n");
	}

	@Override
	public void setEnabled(boolean enabled) {
		ServerStatus.enabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return ServerStatus.enabled;
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
		return "69b";
	}

}
