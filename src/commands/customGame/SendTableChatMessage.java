package commands.customGame;

import tablePackage.*;
import clientPackage.Client;
import commands.Command;

public class SendTableChatMessage extends Command {

	private static boolean enabled = true;
	
	@Override
	public void execute(Client client, String... params) {
		String text = params[1];
		Table table = client.getTable();
		if(table!=null && table.isCustomTable()){
			((CustomTable) table).sendText(client, text);
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
	public String getDescription() {
		return super.getDescription(this);
	}

	@Override
	public String getCode() {
		return "3i";
	}

}
