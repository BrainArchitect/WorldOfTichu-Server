package commands.customGame;

import tablePackage.CustomTable;
import tablePackage.TableManager;
import clientPackage.Client;
import commands.Command;

public class CreateTable extends Command {

	private static boolean enabled = true;
	
	@Override
	public void execute(Client client, String... params) {
		String tableName = params[1];
		if(!tableName.isEmpty()){
			TableManager.addCustomTable(new CustomTable(tableName),client);
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

}
