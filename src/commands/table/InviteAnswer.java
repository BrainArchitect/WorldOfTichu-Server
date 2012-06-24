package commands.table;

import java.util.List;


import tablePackage.CustomTable;
import tablePackage.CustomTableManager;
import clientPackage.Client;
import commands.Command;

public class InviteAnswer extends Command {

	/**
	 * • param[0] : command string
	 * • param[1] : table name
	 * • param[2] : reply 
	 */
	
	private static boolean enabled = true;
	private static int counter = 0;
	
	@Override
	public void execute(Client client, String... params) {

		
		if (!InviteAnswer.enabled || params.length!=3)
			return;
		
		CustomTable table = CustomTableManager.getCustomTable(params[1]);
		if (table != null && table.isInvitationPending(client) && client.getTable()==null){
			boolean reply = Boolean.parseBoolean(params[2]);
			table.replyToInvitation(client, reply ? "Accepted" : "Declined");
			List<Client> clients = table.getAllClients();
			for (Client c : clients)
				c.send("3hR~" +client.getInfo().getUsername()+"~"+ reply + "~\n");
			if (reply){
				table.addObserver(client);
			}
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		InviteAnswer.enabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return InviteAnswer.enabled;
	}

	@Override
	public String getCode() {
		return "3h";
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
