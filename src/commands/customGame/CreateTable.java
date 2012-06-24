package commands.customGame;

import tablePackage.CustomTable;
import tablePackage.CustomTableManager;
import clientPackage.Client;
import commands.Command;

public class CreateTable extends Command {

	private static boolean enabled = true;
	private static int counter = 0;
	
	@Override
	public void execute(Client client, String... params) {
		String tableName = params[1];
		if(!tableName.isEmpty()){
			CustomTableManager.addCustomTable(new CustomTable(tableName),client);
		}

	}

	@Override
	public void setEnabled(boolean enabled) {
		CreateTable.enabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return CreateTable.enabled;
	}


	@Override
	public String getCode() {
		return "3b";
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
