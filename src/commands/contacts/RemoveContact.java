package commands.contacts;

import mainPackage.Database;
import commands.Command;

import clientPackage.Client;

public class RemoveContact implements Command{

	private static boolean enabled = true;
	
	/**
	 * Implements 2b requirement
	 */
	@Override
	public void execute(Client client, String... params) {
		if (!RemoveContact.enabled)
			return;
		String contactUsernameToBeRemoved=params[1];
		boolean success = Database.deleteFriendship(client.getInfo().getUsername(), contactUsernameToBeRemoved);
		
		String response = "2bR~";
		if(success){
			client.send(response +contactUsernameToBeRemoved+"~\n");
			Client c = Client.getClient(contactUsernameToBeRemoved);
			if(c!=null){
				c.send(response+client.getInfo().getUsername()+"~\n");
			}
		}else{
			client.send("2bERR~You have no friend with name: " + contactUsernameToBeRemoved+"~\n");
		}
		
	}

	@Override
	public boolean isEnabled() {
		return RemoveContact.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		RemoveContact.enabled = enabled;
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
		return "2b";
	}
}