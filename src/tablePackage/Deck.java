package tablePackage;

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
		cards.add(new Dragon(16,4));
		cards.add(new Phoenix(15,4));
		cards.add(new Dogs(-1,4));
		cards.add(new Phoenix(1,4));
	}	
	
	/**
	 * This method shuffles the deck in order to change randomly
	 * the initial position of cards.
	 */
	public void shuffle(){
		Collections.shuffle(cards);
		offset = 0;
	}
	
	public ArrayList<Card> next(int limit){
		ArrayList<Card> subList = new ArrayList<Card>();
		for (int i=offset; i<offset + limit; i++)
			subList.add(cards.get(i));
		
		offset += limit;
		return subList;
	}
}