package commands.customGame;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import javax.swing.plaf.ListUI;

import tablePackage.CustomTable;
import tablePackage.Table;
import tablePackage.TableManager;
import clientPackage.Client;
import commands.Command;

public class InviteAnswer extends Command {

	/**
	 * • param[0] : command string
	 * • param[1] : table name
	 * • param[2] : reply 
	 */
	
	private static boolean enabled = true;
	
	@Override
	public void execute(Client client, String... params) {
		if (!InviteAnswer.enabled)
			return;
		
		CustomTable table = TableManager.getCustomTable(params[1]);
		boolean reply = Boolean.parseBoolean(params[2]);
		
		List<Client> clients = table.getAllClients();
		for (Client c : clients)
			client.send("3hR~" + reply + "~");
		
		if (reply == true){
			for (Client c : clients){
				client.send("3c1R~" + reply + "~");
				if (c != client)
					client.send("3c2R~" + reply + "~");
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
	public String getDescription() {
		return super.getDescription(this);
	}

	@Override
	public String getCode() {
		return "3h";
	}

}
