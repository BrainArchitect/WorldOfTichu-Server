package commands.table;

import java.util.List;

import table.*;
import client.Client;
import commands.Command;

public class Invite extends Command {

	private static boolean enabled = true;
	private static int counter = 0;
	
	@Override
	public void execute(Client client, String... params) {
		
		String userName = params[1];
		String hostName = client.getInfo().getUsername();
		Client invitedClient = Client.getClient(userName);
		Table table = client.getTable();
		
		if(invitedClient!=null && table!=null){
			if(table.inviteToTable(invitedClient)){
				invitedClient.send("3g1R~"+hostName+"~"+ table.getID()+"~\n");
				List<Client> clients = table.getAllClients();
				for(Client c : clients){
					 c.send("3g2R~"+userName+"~\n");
				}
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
	public String getCode() {
		return "3g";
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
