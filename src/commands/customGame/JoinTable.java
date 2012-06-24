package commands.customGame;

import tablePackage.CustomTable;
import tablePackage.CustomTableManager;
import clientPackage.Client;
import commands.Command;

public class JoinTable extends Command {

	private static boolean enabled = true;
	private static int counter = 0;
	
	@Override
	public void execute(Client client, String... params) {
		String tableName= params[1];
		CustomTable table = CustomTableManager.getCustomTable(tableName);
		table.addObserver(client);

	}

	@Override
	public void setEnabled(boolean enabled) {
		JoinTable.enabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return JoinTable.enabled;
	}

	@Override
	public String getCode() {
		return "3c";
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
