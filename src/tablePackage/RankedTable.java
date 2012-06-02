package tablePackage;

import clientPackage.Client;

public class RankedTable extends Table{

	
	public RankedTable(String identifier){
		super(identifier);
		players = new Client[4];
	}
	
	public boolean isRankedTable(){
		return true;
	}

	@Override
	public boolean addObserver(Client c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove(Client c) {
		// TODO Auto-generated method stub
		
	}
	
}
