package commands.customGame;

import tablePackage.*;
import clientPackage.Client;
import commands.Command;

public class Invite extends Command {

	private static boolean enabled = true;
	
	@Override
	public void execute(Client client, String... params) {
		
		String userName = params[1];
		Client invitedClient = Client.getClient(userName);
		if(invitedClient!=null){
			Table table = client.getTable();
			if(table!=null && table.isCustomTable()){
				 CustomTable temp = ((CustomTable) table)

			}
		}
		
		
		

	}

	@Override
	public void setEnabled(boolean enabled) {
		Invite.enabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return Invite.enabled;
	}

	@Override
	public String getDescription() {
		return super.getDescription(this);
	}

	@Override
	public String getCode() {
		return "3g";
	}
}
