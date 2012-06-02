package cards.patterns;
import cards.*;

import java.util.ArrayList;
import java.util.Collections;


public class FullHouseCardPattern extends CardPattern{

	public FullHouseCardPattern (ArrayList <Card> c){

		//Sorting the ArrayList from smaller to bigger to facilitate
		//the checks that occur later
		Collections.sort(c);
		
		//cards is a PROTECTED field inherited from the super class
		this.cards =c;
	}

	public int compareTo(CardPattern other) {

		
		if(other==null){
			return 1;
		}

		if (other.isFullHouseCardPattern()){
			return this.cards.get(2).compareTo(other.getCard(2));
		}
		//In all other cases THIS CardPattern Loses.
		return -1;
	}
	public boolean isFullHouseCardPattern(){
		return true;
	}
}
