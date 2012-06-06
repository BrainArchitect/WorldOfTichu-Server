package commands;

import commands.account.*;
import commands.admin.ListCommands;
import commands.admin.ServerStatus;
import commands.contacts.*;
import commands.customGame.*;

public class CommandFactory {

	public static Command createCommand(String commandStr){
		if (commandStr.equals("1b"))
			return new Login();
		
		if (commandStr.equals("1c"))
			return new Logout();
		
		if (commandStr.equals("2a"))
			return new AddInvitation();
		
		if (commandStr.equals("2a1"))
			return new AddInvitationReply();
		
		if (commandStr.equals("2b"))
			return new RemoveContact();
		
		if (commandStr.equals("2f"))
			return new ChatMessageToContact();
		
		if (commandStr.equals("3a"))
			return new SubscribeToCustomGames();
		
		if (commandStr.equals("3b"))
			return new CreateTable();
		
		if (commandStr.equals("3c"))
			return new JoinTable();
		
		if (commandStr.equals("3d"))
			return new SitDown();
		
		if (commandStr.equals("3e"))
			return new GetUp();
		
		if (commandStr.equals("3e1"))
			return new ForceGetUp();
		
		if (commandStr.equals("3f"))
			return new LeaveTable();
		
		if (commandStr.equals("3g"))
			return new Invite();
		
		if (commandStr.equals("3h"))
			return new InviteAnswer();
		
		if (commandStr.equals("3i"))
			return new SendTableChatMessage();
		
		if (commandStr.equals("3j"))
			return new StartGame();
		
		if (commandStr.equals("3w"))
			return new UnsubscribeToCustomGames();
		
		if (commandStr.equals("69a"))
			return new ListCommands();
		
		if (commandStr.equals("69b"))
			return new ServerStatus();
		
		return null;
	}	
}
