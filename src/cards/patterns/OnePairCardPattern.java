package cards.patterns;
import cards.*;

import java.util.ArrayList;
import java.util.Collections;


public class OnePairCardPattern extends CardPattern{

	public OnePairCardPattern (ArrayList <Card> c){
		//Sorting the ArrayList from smaller to bigger to facilitate
		//the checks that occur later
		Collections.sort(c);
		this.cards =c;
	}


	public boolean isOnePairCardPattern(){
		return true;
	}
}
