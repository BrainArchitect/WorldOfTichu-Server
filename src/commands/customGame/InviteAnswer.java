package commands.customGame;

import clientPackage.Client;
import commands.Command;

public class InviteAnswer extends Command {

	private static boolean enabled = true;
	
	@Override
	public void execute(Client client, String... params) {
		// TODO Auto-generated method stub

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
