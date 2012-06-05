package commands.contacts;

import clientPackage.Client;
import commands.Command;

public class MultipleAddInvitation extends Command {

	private static boolean enabled=true;
	private static int counter = 0;
	
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

	@Override
	public void increaseCounter() {
		counter++;
	}

	@Override
	public long getCounter() {
		return counter;
	}

}
