package tablePackage;

import java.util.ArrayList;

import cards.Card;
import cards.CardPattern;
import clientPackage.Client;

public class Player {

	private Client client;
	private Table table;
	private ArrayList<Card> cardsAtHand;
	private ArrayList<CardPattern> cardsCollected;
	private int bankTime;
	
	//Represents the tichu said value of all players
	//0-no tichu
	//1-small tichu
	//2-large tichu
	private int tichu;
	
	
	
	public Player(Client c, Table t){
		client = c;
		table = t;
	}



	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
	}



	public Table getTable() {
		return table;
	}



	public void setTable(Table table) {
		this.table = table;
	}



	public ArrayList<Card> getCardsAtHand() {
		return cardsAtHand;
	}



	public void addCardsAtHand(ArrayList<Card> cardsAtHand) {
		this.cardsAtHand.addAll(cardsAtHand);
	}
	
	public void  reset(){
		cardsAtHand = null;
		tichu=0;
	}



	public ArrayList<CardPattern> getCardsCollected() {
		return cardsCollected;
	}



	public void setCardsCollected(ArrayList<CardPattern> cardsCollected) {
		this.cardsCollected = cardsCollected;
	}



	public int getBankTime() {
		return bankTime;
	}



	public void setBankTime(int bankTime) {
		this.bankTime = bankTime;
	}
	
	
}
