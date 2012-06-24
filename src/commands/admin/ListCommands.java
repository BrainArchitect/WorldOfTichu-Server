package commands.admin;

import java.io.File;

import client.Client;

import commands.Command;

public class ListCommands extends Command {

	private static boolean enabled = true;
	private static int counter = 0;
	private Client client;
	
	public void execute(Client client, String... args){
		this.client = client;
		String nam = "./src/commands/";
	    File aFile = new File(nam);
	    process(aFile);
	}

	private void process(File aFile) {
		if(aFile.isFile()){
			String name = aFile.getName();
			if (name.substring(name.length()-4).equals("java")){
				try {
					if (!name.equals("Command.java") && !name.equals("CommandFactory.java")){
						String pack = aFile.getPath().replaceAll("\\\\", ".");
						pack = pack.substring(6, pack.lastIndexOf('.'));
						Class<?> cl = Class.forName(pack);
						Command command = (Command) cl.newInstance();
						String description = command.getDescription();
						String code = command.getCode();
						boolean enabled = command.isEnabled();
						client.send("69aR~" + code + "~" + description + "~" + enabled + "~\n");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else if (aFile.isDirectory()) {
			File[] listOfFiles = aFile.listFiles();
			if(listOfFiles!=null) {
				for (int i = 0; i < listOfFiles.length; i++)
					process(listOfFiles[i]);
			}
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		ListCommands.enabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return ListCommands.enabled;
	}

	@Override
	public String getCode() {
		return "69a";
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
