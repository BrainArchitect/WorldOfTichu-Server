package commands.contacts;

import commands.Command;

import mainPackage.Database;
import clientPackage.Client;
import clientPackage.Info;

public class AddInvitationReply extends Command{

	private static boolean enabled = true;
	private static int counter = 0;
	
	/**
	 * PART OF THE ADD CONTACT REQUIREMENT <br><br>
	 * 
	 * Message: Add Invitation Reply <br>
	 * Params : 2a1, username, isAccepted<br><br>
	 * 
	 * For a client to reply for an invitation the following conditions must be met:<br>
	 * <li> Username exists in the database.<br>
	 * <li> This client and the client with the specified username are NOT already friends.<br><br>
	 * 
	 * 
	 * Actions Taken When Conditions Are Met:<br>
	 * <li> Remove Invitation from the database.<br>
	 * <li> Send Add Completed message to the client.<br>
	 * <li> Send Add Completed message to the corresponding client with the specified username if he is online.<br><br>
	 * 
	 * Actions Taken When Conditions Are NOT Met:<br>
	 *<li> Send AddInvitationReport message to the client.<br><br>
	 * 
	 */
	@Override
	public void execute(Client targetClient, String... params) {
		
		try{
			String sourceUsername = params[1];
			boolean accepted = Boolean.parseBoolean(params[2]);
			
			Info sourceBuddyInfo = Database.loadInfo(sourceUsername);
			if(sourceBuddyInfo!=null){
				if(!Database.hasInvitation(sourceBuddyInfo.getUsername(), targetClient.getInfo().getUsername())){
					return;
				}
				
				if(accepted){
					
					if(!Database.addFriendship(sourceBuddyInfo.getUsername(), targetClient.getInfo().getUsername())){
						String response = "2aR~false~You are already friend with "+ sourceBuddyInfo.getUsername()+".~\n";
						targetClient.send(response);
						Database.deleteInvitation(sourceBuddyInfo.getUsername(), targetClient.getInfo().getUsername());
						return;
					}
					
					Client sourceClient = Client.getClient(sourceUsername);
					if(sourceClient!=null){
						targetClient.addOnlineContact(sourceClient);
						targetClient.send("2a2R~" + sourceUsername + "~true~\n");
						
						
						sourceClient.addOnlineContact(targetClient);
						sourceClient.send("2a2R~" + targetClient.getInfo().getUsername() + "~true~\n");
						
					}else{
						targetClient.send("2a2R~" + sourceUsername + "~false~\n");
						
					}
					
				}
				
				Database.deleteInvitation(sourceBuddyInfo.getUsername(), targetClient.getInfo().getUsername());
				
			}else{
				String response = "2aR~false~Username "+ sourceUsername+" does not exist.~\n";
				targetClient.send(response);
				Database.deleteInvitation(sourceUsername, targetClient.getInfo().getUsername());
				return;
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isEnabled() {
		return AddInvitationReply.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		AddInvitationReply.enabled = enabled;
	}

	@Override
	public String getCode() {
		return "2a1";
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
