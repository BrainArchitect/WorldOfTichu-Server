package game;

import java.util.ArrayList;

import mainPackage.Database;

import tablePackage.CustomTable;

import cards.Card;
import cards.CardPattern;
import clientPackage.Client;

public class Player {

	private Client client;
	private Game game;
	private ArrayList<Card> cardsAtHand;
	private ArrayList<CardPattern> cardsCollected;
	private int bankTime;
	private Statistics statistics;
	
	private boolean continueDealing;
	
	//Represents the tichu said value of all players
	//0-no tichu
	//1-small tichu
	//2-large tichu
	private int tichu;

	public Player(Client c){
		client = c;
		reset();
	}	
	
	public void  reset(){
		cardsAtHand = new ArrayList<Card>();
		cardsCollected = new ArrayList<CardPattern>();
		tichu=0;
		continueDealing = false;
	}
	
	public void addCardsAtHand(ArrayList<Card> cardsAtHand) {this.cardsAtHand.addAll(cardsAtHand);}
	public void addCardsCollected(ArrayList<CardPattern> cardsCollected) {this.cardsCollected.addAll(cardsCollected);}

	
	public synchronized void registerStatisctics(Statistics statistics){
		Database.registerStatisctics(statistics);
	}
	
	
	//Getters
	//========================================================================================================
	public Client getClient() {return client;}

	public ArrayList<Card> getCardsAtHand(){return cardsAtHand;}
	public ArrayList<CardPattern> getCardsCollected() {return cardsCollected;}
	public int getTichu(){return tichu;}
	public int getBankTime() {return bankTime;}
	public boolean getContinueDealing() {return continueDealing;}
	public Game getGame() {	return game;}
	public Statistics getStatistics() {	return statistics;}

	//Setters
	//========================================================================================================
	public void setClient(Client client) {this.client = client;}
	public void setTichu(int tichuType){this.tichu = tichuType;}
	public void setBankTime(int bankTime) {this.bankTime = bankTime;}
	public void setContinueDealing(boolean continueDealing) {this.continueDealing = continueDealing;}
	public void setGame(Game game) {this.game = game;}
	public void setStatistics(Statistics statistics) {this.statistics = statistics;}
	
}
