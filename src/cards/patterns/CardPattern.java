package cards.patterns;
import cards.*;

import java.util.ArrayList;


public abstract class CardPattern implements Comparable<CardPattern>{

	protected ArrayList <Card> cards;
	
	
	public int getNumOfCards(){
		return cards.size();
	}
	
	public Card getCard(int index){
		return cards.get(index);
	}
	
	public ArrayList<Card> getListOfCards(){
		return cards;
	}
	
	/** This method compares the current card pattern with another
	 * and returns which is the Winning pattern.
	 * 
	 * This method works correctly only for the Card patterns 
	 * that have up to three cards. 
	 * 
	 * FOR ALL THE OTHER CARD PATTERNS the "compareTo" METHOD 
	 * MUST BE IMPLEMENTED (OVERRIDED) IN THE SUBCLASSES. 
	 * 
	 * Returns >0 if THIS is the Winning pattern
	 * Returns <=0 if THIS is the Losing pattern
	 */
	public int compareTo(CardPattern other){

		if(other==null){
			return 1;
		}
		
		if (this.getNumOfCards()==other.getNumOfCards()){
			return this.getCard(0).compareTo(other.getCard(0));
		}
		
		return -1;
	}
	
	public boolean isOneCardPattern(){
		return false;
	}
	public boolean isOnePairCardPattern(){
		return false;
	}
	public boolean isThreeCardPattern(){
		return false;
	}
	public boolean isFourBombCardPattern(){
		return false;
	}
	public boolean isSuccessivePairsCardPattern(){
		return false;
	}
	public boolean isStraightCardPattern(){
		return false;
	}
	public boolean isStraightBombCardPattern(){
		return false;
	}
	public boolean isFullHouseCardPattern(){
		return false;
	}
	
	
	
}
