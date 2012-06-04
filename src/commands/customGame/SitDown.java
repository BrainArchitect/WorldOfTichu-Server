package commands.customGame;

import tablePackage.*;
import clientPackage.Client;
import commands.Command;

public class SitDown extends Command {

	private static boolean enabled = true; 
	
	@Override
	public void execute(Client client, String... params) {
		int sitNo = Integer.parseInt(params[1]);
		Table t = client.getTable();

		if(t!=null && t.isCustomTable()){
			((CustomTable) t).smnSitDown(client, sitNo);
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
	public String getDescription() {
		return super.getDescription(this);
	}

	@Override
	public String getCode() {
		return "3d";
	}
}
