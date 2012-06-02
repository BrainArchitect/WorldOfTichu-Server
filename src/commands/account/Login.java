package commands.account;

import java.util.TreeSet;

import commands.Command;

import mainPackage.Database;
import clientPackage.Client;
import clientPackage.Info;

public class Login implements Command{

	private static int limit = 1000;
	private static boolean enabled = true;
	
	/**
	 * PART OF THE LOGIN REQUIREMENT <br><br>
	 * 
	 * Message: Login <br>
	 * Params : 1b, username, password <br><br>
	 * 
	 * For a client to login the following conditions must be met:<br>
	 * <li> Username exists in the database. CHECK<br>
	 * <li> Password matches with the password stored in the database. CHECK<br>
	 * <li> No client is already logged in with the specified username. CHECK<br><br>
	 * 
	 * Actions Taken When Conditions Are Met:<br>
	 * <li> Add the client to the online clients set. DONE<br>
	 * <li> Send LoginReport message to the client. DONE<br>
	 * <li> Add the client's online contacts to his own online contacts set. DONE<br>
	 * <li> Send ContactsReport message to the client. DONE<br>
	 * <li> Send InvitationsReport message to the client. DONE<br>
	 * <li> Add the client to each of the online contacts set of his online contacts. DONE<br>
	 * <li> Send ContactCameOnline message to the client's online contacts. DONE<br><br>
	 * 
	 * 
	 * Actions Taken When Conditions Are NOT Met:<br>
	 * <li> Send LoginReport message to the client. DONE<br><br>
	 * 
	 */
	public void execute(Client client, String... params) {
		if (!enabled || Client.getClientsSize() >= limit)
			return;
		
	
		String username = params[1];
		String password = params[2];
				
		String response = "1bR~";
			
		Info info = Database.loadInfo(username);
		if (info != null && info.getPassword().equals(password)){
			
			client.setInfo(info);
			client.setStatistics(Database.loadStatistics(info.getUsername()));
			
			if(Client.addClient(client)){
				
				//Successful Login.
				response += "true~"+username+"~\n";
				client.send(response);
				
				
				
				//Code for notifying the current user about the online and offline contacts.
				//=======================================================================
					response = "2cR~";
					TreeSet<String> offlineUsernames = client.loadOnlineContacts();
					TreeSet<String> onlineUsernames = client.getOnlineUsernames();
					
					if(offlineUsernames==null)
						return;
					
					response+= onlineUsernames.size()+"~";
					response+= offlineUsernames.size()+"~";
					
					if(onlineUsernames.size()>0){
						for(String aUserName: onlineUsernames){
							response+=aUserName +"~";
						}
	
						//Code for notifying online Contacts that the current user is now online.
						//=======================================================================
							TreeSet<Client> onlineContacts = client.getOnlineContacts();
							for(Client aContact : onlineContacts){
								aContact.addOnlineContact(client);
								aContact.send("2dR~"+client.getInfo().getUsername()+"~\n");
							}
						//=======================================================================
					}
					
					if(offlineUsernames.size()>0){
						for(String aUserName: offlineUsernames){
							response+=aUserName +"~";
						}
					}
					
					response+="\n";
					client.send(response);
				//=======================================================================
					
					response = "2gR~";
					TreeSet<String> invites = Database.getInvites(client.getInfo().getUsername());
					if(invites!=null && invites.size()!=0){
						for(String anInvitation : invites){
							response += anInvitation +"~";
						}
						response+="\n";
						client.send(response);						
					}				
			}else{
				//Login Failed because user was already logged in.
				response+="false~1~\n";
				client.send(response);
			}
		}else{
			//Login Failed due to wrong username and/or password.
			response+="false~0~\n";
			client.send(response);
		}

	}

	@Override
	public boolean isEnabled() {
		return Login.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		Login.enabled = enabled;
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
		return "1b";
	}
}
