package commands.contacts;

import commands.Command;

import mainPackage.Database;
import clientPackage.Client;
import clientPackage.Info;

public class AddInvitation implements Command{

	private static boolean enabled = true;
	
	/**
	 * PART OF THE ADD CONTACT REQUIREMENT <br><br>
	 * 
	 * Message: Add Invitation <br>
	 * Params : 2a, username<br><br>
	 * 
	 * For a client to add an invitation the following conditions must be met:<br>
	 * <li> Username exists in the database.<br>
	 * <li> This client and the client with the specified username are NOT already friends.<br>
	 * <li> Add Invitation from this client to the specified username was not sent in the past.<br><br>
	 * 
	 * Actions Taken When Conditions Are Met:<br>
	 * <li> Add Invitation to the database.<br>
	 * <li> Send AddInvitationReport message to the client.<br>
	 * <li> Send AddInvitationRequest message to the corresponding client with the specified username.<br><br>
	 * 
	 * Actions Taken When Conditions Are NOT Met:<br>
	 *<li> Send AddInvitationReport message to the client.<br><br>
	 * 
	 */
	@Override
	public void execute(Client sourceClient, String... params) {
		if (!AddInvitation.enabled)
			return;
		
		String targetUsername = params[1];
		
		String response = "2aR~";
		
		Info targetBuddyInfo = Database.loadInfo(targetUsername);
		if(targetBuddyInfo!=null){
			if(Database.hasFriendship(sourceClient.getInfo().getUsername(), targetBuddyInfo.getUsername())){
				response += "false~You are already friend with "+ targetBuddyInfo.getUsername()+".~\n";
				sourceClient.send(response);
				return;
			}
			if(!Database.addInvitation(sourceClient.getInfo().getUsername(), targetBuddyInfo.getUsername()))
				return;
			response += "true~\n";
			sourceClient.send(response);
		}else{
			response += "false~Username " + targetUsername+ " does not exist.~\n";
			sourceClient.send(response);
			return;
		}
		
		Client targetClient = Client.getClient(targetUsername);
		if(targetClient != null){
			response = "2a1R~" + sourceClient.getInfo().getUsername() + "~\n";
			targetClient.send(response);
		}		
	}
	

	@Override
	public boolean isEnabled() {
		return AddInvitation.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		AddInvitation.enabled = enabled;
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
		return "2a";
	}
}
