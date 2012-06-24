package commands;

import client.Client;

public abstract class Command {
	
	public abstract void increaseCounter();	
	public abstract long getCounter();
	
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
	public abstract void execute(Client client, String... params);
	public  abstract void setEnabled(boolean enabled);
	public abstract boolean isEnabled();
}
