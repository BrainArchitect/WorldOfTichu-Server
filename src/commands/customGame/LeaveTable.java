package commands.customGame;

import tablePackage.CustomTable;
import clientPackage.Client;
import commands.Command;

public class LeaveTable extends Command {

	private static boolean enabled = true;
	private static int counter = 0;
	
	@Override
	public void execute(Client client, String... params) {
		CustomTable t = client.getTable();
		if(t!=null){
			 t.remove(client);
		}
		

	}

	@Override
	public boolean isEnabled() {
		return LeaveTable.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		LeaveTable.enabled = enabled;
	}

	@Override
	public String getCode() {
		return "3f";
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
