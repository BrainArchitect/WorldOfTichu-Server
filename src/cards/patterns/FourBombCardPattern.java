package cards.patterns;
import cards.*;

import java.util.ArrayList;
import java.util.Collections;


public class FourBombCardPattern extends CardPattern{


	public FourBombCardPattern (ArrayList <Card> c){
		
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
		
		if (other.isFourBombCardPattern()){
			return this.getCard(0).compareTo(other.getCard(0));
		}else if(other.isStraightBombCardPattern()){
			return -1;
		}
		
		//In all other cases THIS CardPattern(Bomb) Wins.
		return 1;
	}
	public boolean isFourBombCardPattern(){
		return true;
	}
}
