package commands.table;

import table.*;
import client.Client;
import commands.Command;

public class SendTableChatMessage extends Command {

	private static boolean enabled = true;
	private static int counter = 0;
	
	@Override
	public void execute(Client client, String... params) {
		String text = params[1];
		Table table = client.getTable();
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
