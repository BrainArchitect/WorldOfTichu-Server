package commands.admin;

import client.Client;
import commands.Command;

public class ServerStatus extends Command {

	private static boolean enabled = true;
	private static int counter = 0;
	
	@Override
	public void execute(Client client, String... params) {
		/*int size[] = {Client.getClientsSize(), CustomTableManager.getCustomTablesSize(), TableManager.getRankedTablesSize() };
		
		String output = "";
		for (int i=0; i<size.length; i++)
			output += size[i] + "~";
		client.send("69bR~" + output + "\n");*/
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
	public String getCode() {
		return "69b";
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
