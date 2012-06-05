package commands.contacts;

import clientPackage.Client;
import commands.Command;

public class MultipleAddInvitation extends Command{

	private static boolean enabled=true;
	@Override
	public void execute(Client client, String... params) {

	}

	@Override
	public void setEnabled(boolean enabled) {
		MultipleAddInvitation.enabled=enabled;
		
	}

	@Override
	public boolean isEnabled() {
		return MultipleAddInvitation.enabled;
	}

	@Override
	public String getCode() {
		return "2g";
	}

}
