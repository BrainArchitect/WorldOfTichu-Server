package main;

import java.io.File;
import java.util.Calendar;

import client.Client;

import commands.Command;

public class ExitPoll extends Thread{
	
	private String statToFlush = "";
	
	@Override
	public void run(){
		long interval = 100;
		while (true){
			try {
				if (Calendar.getInstance().get(Calendar.MINUTE) == 0 && Calendar.getInstance().get(Calendar.SECOND) == 0){
					interval = 3600000;
					
					statToFlush = "";
					process(new File("./src/commands/"));
					
					Calendar now = Calendar.getInstance();
					String year = String.valueOf(now.get(Calendar.YEAR)).substring(2, 4);
					String month = String.valueOf(now.get(Calendar.MONTH)+1);
					if (month.length() == 1)
						month = "0" + month;
					String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
					if (day.length() == 1)
						day = "0" + day;
					String hour = String.valueOf(now.get(Calendar.HOUR_OF_DAY));
					if (hour.length() == 1)
						hour = "0" + hour;
					String dateStr = year + month + day + hour;
					
					Database.registerStats(dateStr, "totalclients~" + Client.getClientsSize() + "~" + statToFlush);
				}
				synchronized (this) {
					this.wait(interval);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
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
						long counter = command.getCounter();
						statToFlush += pack.substring(pack.lastIndexOf('.')+1) + "~" + counter + "~";
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
}
