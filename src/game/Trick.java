package game;

import java.util.ArrayList;

import cards.CardPattern;

public class Trick {

	private ArrayList<CardPattern> cardsAtTrick;
	
	public void addToTrick(CardPattern cardPattern){
		cardsAtTrick.add(cardPattern);
	}
	
	public CardPattern getLastPlayedCardPattern(){
		if(cardsAtTrick.size()>0){
			return cardsAtTrick.get(cardsAtTrick.size()-1);
		}
		return null;
	}
	
	
	public void reset(){
		cardsAtTrick = new ArrayList<CardPattern>();
	}
	
	public ArrayList<CardPattern> getCardsAtTrick(){
		return cardsAtTrick;
	}
}
