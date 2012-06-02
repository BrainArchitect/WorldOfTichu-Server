package commands.customGame;

import tablePackage.CustomTable;
import tablePackage.TableManager;
import clientPackage.Client;
import commands.Command;

public class JoinTable implements Command {

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
	public String getDescription() {
		String name = this.getClass().getName();
		name = name.substring(name.lastIndexOf('.')+1);
		String finalName = "";
		for (int i=0; i<name.length(); i++){
			if (Character.isUpperCase( name.charAt(i) ))
				finalName += " ";
			
			finalName += name.charAt(i);
		}
		return finalName;
	}

	@Override
	public String getCode() {
		return "3c";
	}

}
