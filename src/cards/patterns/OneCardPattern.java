package cards.patterns;
import cards.*;

import java.util.ArrayList;

public class OneCardPattern extends CardPattern{
	
	/**Constructor method 
	 * Given an ArrayList of Card(s), it creates a OneCardPattern
	 */
	public OneCardPattern (ArrayList <Card> c){
		
		//cards is a PROTECTED field inherited from the super class
		this.cards = c;
	}
	
	
	public boolean isOneCardPattern(){
		return true;
	}


}
