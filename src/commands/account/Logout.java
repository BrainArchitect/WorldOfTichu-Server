package commands.account;

import java.util.TreeSet;

import tablePackage.TableManager;

import commands.Command;

import clientPackage.Client;

public class Logout extends Command{

	private static boolean enabled = true;
	
	/**
	 * IMPLEMENTS THE LOGOUT REQUIREMENT <br><br>
	 * 
	 * Message: Logout <br>
	 * Params : 1c <br><br>
	 * 
	 * For a client to logout there are no conditions to be met.<br><br>
	 * 
	 * Actions Taken:<br>
	 * <li> Removes the client from the (online) clients set.<br>
	 * <li> Send ContactWentOffline message to the clients' online contacts.<br><br>
	 * 
	 */
	public void execute(Client client, String... params) {
		if (!enabled)
			return;

		TreeSet<Client> onlineContacts = client.getOnlineContacts();
		for(Client aContact: onlineContacts){
			String response="2eR~";
			aContact.removeOnlineContact(client);
			response+=client.getInfo().getUsername()+"~\n";
			aContact.send(response);
		}		
		
		Client.removeClient(client);
		TableManager.unsubscribeClientToCustomGame(client);
		client.close();	

	}

	@Override
	public boolean isEnabled() {
		return Logout.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		Logout.enabled = enabled;
	}

	@Override
	public String getDescription() {
		return super.getDescription(this);
	}

	@Override
	public String getCode() {
		return "1c";
	}
}
