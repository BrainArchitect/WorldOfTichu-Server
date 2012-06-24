package commands.customGame;

import tablePackage.*;
import clientPackage.Client;
import commands.Command;

public class SendTableChatMessage extends Command {

	private static boolean enabled = true;
	private static int counter = 0;
	
	@Override
	public void execute(Client client, String... params) {
		String text = params[1];
		CustomTable table = client.getTable();
		if(table!=null){
			table.sendText(client, text);
		}

	}

	@Override
	public boolean isEnabled() {
		return SendTableChatMessage.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		SendTableChatMessage.enabled = enabled;
	}


	@Override
	public String getCode() {
		return "3i";
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
