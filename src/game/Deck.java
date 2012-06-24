package game;

import java.util.ArrayList;
import java.util.Collections;

import cards.*;

public class Deck {

	private ArrayList <Card> cards;	        
	private int offset;
	
	/** Constructor method
	 *  Creates a new Deck of Tichu Cards
	 */
	public Deck(){		
		cards = new ArrayList<Card>();
	
		// Insert all normal cards and add them to the List of Cards
		for(int i=2; i<=14; i++){
			for(int j=0; j<4; j++){
				cards.add(new Card(i,j));
			}
		}
		
		// Insert special cards to deck.
		cards.add(new Card(16,4));//Dragon
		cards.add(new Card(15,4));//Phoenix
		cards.add(new Card(-1,4));//Dogs
		cards.add(new Card(1,4));//Mahjong
	}	
	
	/**
	 * This method shuffles the deck in order to change randomly
	 * the initial position of cards.
	 */
	public void shuffle(){
		Collections.shuffle(cards);
		offset = 0;
	}
	
	public ArrayList<Card> dealCards(int limit){
		ArrayList<Card> subList = new ArrayList<Card>();
		for (int i=offset; i<offset + limit; i++)
			subList.add(cards.remove(i));
		
		offset += limit;
		return subList;
	}
	
	public void addCardsToDeck(ArrayList<Card> cards){
		this.cards.addAll(cards);
	}
}