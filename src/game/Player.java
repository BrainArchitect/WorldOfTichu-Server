package game;

import java.util.ArrayList;

import mainPackage.Database;

import tablePackage.Table;

import cards.Card;
import cards.CardPattern;
import clientPackage.Client;

public class Player {

	private Client client;
	private Table table;
	private ArrayList<Card> cardsAtHand;
	private ArrayList<CardPattern> cardsCollected;
	private int bankTime;
	private Statistics statistics;
	
	//Represents the tichu said value of all players
	//0-no tichu
	//1-small tichu
	//2-large tichu
	private int tichu;

	public Player(Client c){
		client = c;
		tichu=0;
		cardsAtHand = new ArrayList<Card>();
		cardsCollected = new ArrayList<CardPattern>();
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
		cardsAtHand.removeAll(cardsAtHand);
		tichu=0;
	}



	public ArrayList<CardPattern> getCardsCollected() {
		return cardsCollected;
	}



	public void setCardsCollected(ArrayList<CardPattern> cardsCollected) {
		this.cardsCollected = cardsCollected;
	}
	
	public int getTichu(){
		return tichu;
	}
	
	public void setTichu(int tichuType){
		this.tichu = tichuType;
	}



	public int getBankTime() {
		return bankTime;
	}



	public void setBankTime(int bankTime) {
		this.bankTime = bankTime;
	}



	public Statistics getStatistics() {
		return statistics;
	}



	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	public synchronized void registerStatisctics(Statistics statistics){
		Database.registerStatisctics(statistics);
	}
	
	
	
	
}
