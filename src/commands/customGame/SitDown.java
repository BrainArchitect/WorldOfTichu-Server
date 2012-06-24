package commands.customGame;

import tablePackage.*;
import clientPackage.Client;
import commands.Command;

public class SitDown extends Command {

	private static boolean enabled = true;
	private static int counter = 0;
	
	@Override
	public void execute(Client client, String... params) {
		int sitNo = Integer.parseInt(params[1]);
		CustomTable t = client.getTable();

		if(t!=null){
			 t.smnSitDown(client, sitNo);
		}
	}

	@Override
	public boolean isEnabled() {
		return SitDown.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		SitDown.enabled = enabled;
	}


	@Override
	public String getCode() {
		return "3d";
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
