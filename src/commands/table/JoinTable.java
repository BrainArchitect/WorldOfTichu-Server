package commands.table;

import table.CustomTable;
import table.CustomTableManager;
import client.Client;
import commands.Command;

public class JoinTable extends Command {

	private static boolean enabled = true;
	private static int counter = 0;
	
	@Override
	//NEEDS FIX!!!!! MUST RUN FOR RANKED TABLES ALSO!!!!!
	public void execute(Client client, String... params) {
		String tableName= params[1];
		if(client.getTable()!=null){
			CustomTable table = CustomTableManager.getCustomTable(tableName);
			if(table!=null){
				table.addObserver(client);
			}
		}
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
