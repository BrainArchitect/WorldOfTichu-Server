package commands.contacts;

import clientPackage.Client;
import commands.Command;

public class AddInvitationMultipleRequests extends Command{

	private static boolean enabled=true;
	@Override
	public void execute(Client client, String... params) {
		if (!AddInvitationMultipleRequests.enabled)
			return;
	}

	@Override
	public void setEnabled(boolean enabled) {
		AddInvitationMultipleRequests.enabled=enabled;
		
	}

	@Override
	public boolean isEnabled() {
		return AddInvitationMultipleRequests.enabled;
	}

	@Override
	public String getDescription() {
		return super.getDescription(this);
	}

	@Override
	public String getCode() {
		return "2gR";
	}

}
