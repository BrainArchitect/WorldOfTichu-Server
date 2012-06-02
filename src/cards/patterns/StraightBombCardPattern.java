package cards.patterns;
import cards.*;

import java.util.ArrayList;
import java.util.Collections;



public class StraightBombCardPattern extends CardPattern{
	
	public StraightBombCardPattern (ArrayList <Card> c){
		
		//Sorting the ArrayList from smaller to bigger to facilitate
		//the checks that occur later
		Collections.sort(c);
		
		//cards is a PROTECTED field inherited from the super class
		this.cards =c;
	}
	
	@Override
	public int compareTo(CardPattern other) {
		
		if(other==null){
			return 1;
		}
		
		//Only a StraightBomb can win a Straight Bomb
		if (other.isStraightBombCardPattern() ){
			
			//And the StraightBomb that has more cards wins
			if (other.getNumOfCards()!=this.getNumOfCards()){
				return (this.getNumOfCards()-other.getNumOfCards());
			
			//But if the number of Cards are equal
			}else{
				
				//Then the StraightBomb whose smallest card is bigger 
				//than the other's StraightBomb smallest card wins 
				if (this.getCard(0).compareTo(other.getCard(0))>0){
					return 1;
				}else if (this.getCard(0).compareTo(other.getCard(0))<0){
					return -1;
					
				//In the extreme case that those are equal too, the Color of the StraightBomb 
				//defines the winner (from smaller to bigger:Green,Blue,Red,Black)
				}else{
					if (this.getCard(0).getColor()>other.getCard(0).getColor()){
						return 1;
					}else {
						return -1;
					}
				}
			}
		}
		
		//In all other cases THIS CardPattern(Bomb) Wins.
		return 1;
	}
	
	public boolean isStraightBombCardPattern(){
		return true;
	}

}
