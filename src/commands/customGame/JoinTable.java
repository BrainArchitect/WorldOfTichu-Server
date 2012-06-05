package commands.customGame;

import tablePackage.CustomTable;
import tablePackage.TableManager;
import clientPackage.Client;
import commands.Command;

public class JoinTable extends Command {

	private static boolean enabled = true;
	
	@Override
	public void execute(Client client, String... params) {
		String tableName= params[1];
		CustomTable table = TableManager.getCustomTable(tableName);
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

}
