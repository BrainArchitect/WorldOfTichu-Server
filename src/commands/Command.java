package commands;

import clientPackage.Client;

public interface Command {
	public void execute(Client client, String... params);
	public void setEnabled(boolean enabled);
	public boolean isEnabled();
	public String getDescription();
	public String getCode();
}
