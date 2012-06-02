package commands.contacts;

import commands.Command;
import clientPackage.Client;

public class ChatMessageToContact implements Command{

	private static boolean enabled = true;
	
	/**
	 * Implements 2f requirement
	 */
	@Override
	public void execute(Client sourceClient, String... params) {
		if (!ChatMessageToContact.enabled)
			return;
		
		String sourceUsername = sourceClient.getInfo().getUsername();
		String targetUsername = params[1];
		Client targetClient = Client.getClient(targetUsername);
		if(targetClient != null){
			String message = params[2];
			String response = "2fR~" + sourceUsername +"~"+ targetUsername +"~"+message+ "~\n";
			targetClient.send(response);
			sourceClient.send(response);
		}		
	}

	@Override
	public boolean isEnabled() {
		return ChatMessageToContact.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		ChatMessageToContact.enabled = enabled;
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
		return "2f";
	}
}
