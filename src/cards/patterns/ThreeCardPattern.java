package cards.patterns;

import cards.*;

import java.util.ArrayList;
import java.util.Collections;


public class ThreeCardPattern extends CardPattern{

	public ThreeCardPattern (ArrayList <Card> c){
		
		//Sorting the ArrayList from smaller to bigger to facilitate
		//the checks that occur later
		Collections.sort(c);
		
		//cards is a PROTECTED field inherited from the super class
		this.cards =c;
	}

	public boolean isThreeCardPattern(){
		return true;
	}
}
