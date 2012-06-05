package commands;

import clientPackage.Client;

public abstract class Command {
	
	
	public abstract void execute(Client client, String... params);
	public  abstract void setEnabled(boolean enabled);
	public abstract boolean isEnabled();

	
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
	
	public abstract String getCode();
}
