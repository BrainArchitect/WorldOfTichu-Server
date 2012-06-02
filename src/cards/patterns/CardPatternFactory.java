package cards.patterns;
import cards.*;

import java.util.ArrayList;
import java.util.Collections;


public class CardPatternFactory {

	/** This method is called during the game
	 * when we want to create an object of any Card Pattern.
	 * This method is a factory method as specified by the
	 * SIMPLE FACTORY Design Pattern.
	 * 
	 * @param An ArrayList of Cards
	 * 
	 * @return 
	 * If the Cards in the ArrayList are composing 
	 * a valid CardPattern, then an object of the specific 
	 * CARD PATTERN is returned.
	 * 
	 * Otherwise, the combination of cards is invalid 
	 * and the method returns NULL. 
	 */
	public CardPattern createCardPattern(ArrayList <Card> cards){
		
		if(cards==null || cards.isEmpty()){
			return null;
		}

		//Sorting the Cards in order to make easier 
		//the Card Pattern Recognition Process
		Collections.sort(cards);		
		
		//If the List has only one card it is sure that it is a OneCardPattern
		if (cards.size()==1){
			OneCardPattern one = new OneCardPattern(cards);
			return one;
			
		//If the List has 2 cards 
		}else if (cards.size()==2){
			//And the cards are equal we have a OnePairCardPattern
			if (cards.get(0).equals(cards.get(1))){
				OnePairCardPattern two = new OnePairCardPattern(cards);
				
				return two;
			}
			
		//If the List has 3 cards
		}else if (cards.size()==3){
			//And the cards are equal we have a ThreeCardPattern
			if (cards.get(0).equals(cards.get(1)) && cards.get(1).equals(cards.get(2))){
				ThreeCardPattern three = new ThreeCardPattern(cards);
				return three;
			}
			
		//If the List has 4 cards
		}else if (cards.size()==4){
			//And the cards are equal
			if ((cards.get(0).equals(cards.get(1))) && (cards.get(0).equals(cards.get(1)) 
					&& (cards.get(2).equals(cards.get(3))))){
				
				//And the cards do not contain a Phoenix
				if (!cards.get(0).isPhoenix() && !cards.get(1).isPhoenix() &&
					!cards.get(2).isPhoenix() && !cards.get(3).isPhoenix()){
						
					//Then it is a FourBomb CardPattern
					FourBombCardPattern fourBomb = new FourBombCardPattern(cards);
					return fourBomb;
				}
			}
			
		//If the List has 5 cards
		}else if(cards.size()==5){
			//And it contains two pairs and the remainder card matches in either pair
			if (cards.get(0).equals(cards.get(1)) && cards.get(3).equals(cards.get(4)) 
				&& (cards.get(2).equals(cards.get(1)) || cards.get(2).equals(cards.get(3)))){
				
				//Then it is a FullHouse CardPattern
				FullHouseCardPattern fullHouse = new FullHouseCardPattern(cards);
				return fullHouse;
			}
		}
		
		
		//Until Now we have checked for all the CardPatterns 
		//except for Straight (and StraightBomb) and SuccessivePairs
		
		
		
		//Checking for successive pairs card pattern
		//This kind of pattern has at least 4 cards
		if (cards.size()>=4){
			
			//Because in this pattern cards come in pairs the total number of cards must be an even number
			if (cards.size()%2==0){
				
				//We are assuming that the cards are indeed composing 
				//a successive pairs card pattern until proven otherwise.
				boolean flag = true;
				
				//Split the Cards to pairs and for each pair...
				for (int i=0;i<cards.size();i=i+2){
					
					//Is the 2 cards a pair actually?
					flag = cards.get(i).equals(cards.get(i+1));
					
					//if not (it is not a successive pairs card pattern) break
					if(!flag){
						break;
					}
					
					//If it is not the last pair
					if((i+2)==cards.size()){
						//Check if the successive card of the pair is also successive in value. If not then break
						if(!((cards.get(i+2).getValue()-cards.get(i+1).getValue())==1)){
							flag = false;
							break;
						}
					}
				}
				
				//If the assumption was correct return the SuccessivePairsCardPattern.
				if(flag){
					SuccessivePairsCardPattern successivePairs = new SuccessivePairsCardPattern(cards);
					return successivePairs;
				}
			}
		}//End of checking for SuccessivePairsCardPattern
			
			
		//Checking for Straight (or StraightBomb) card pattern
		//This kind of pattern has at least 5 cards
		if (cards.size()>=5){
			
			//For each card...
			for (int i=0;i<cards.size()-1;i++){
				//Check if the successive card is also successive in value. If not then no pattern found
				if(!(cards.get(i).compareTo(cards.get(i+1))==-1)){
					return null;
				}
			}
			
			
			//At this point we are sure that we have a STRAIGHT but is it a bomb straight?
			
			
			//We are assuming that the cards are indeed composing 
			//a Straight Bomb card pattern until proven otherwise.
			boolean flag =true;
			
			
			//If there is a special card (phoenix) in the cards it is no bomb straight
			for (int i=0;i<cards.size();i++){
				if(cards.get(i).isPhoenix()){
					flag=false;
					break;
				}
			}
			
			if(!flag){
				StraightCardPattern straight = new StraightCardPattern(cards);
				return straight;
			}
			
			//For each card...
			for (int i=0;i<cards.size()-1;i++){
				//Check if the current and the successive card are of the same color. If not it is no bomb straight
				if(cards.get(i).getColor()!=cards.get(i+1).getColor()){
					flag=false;
					break;
				}
	
			}
			
			//If the assumption was correct return the StraightbombCardPattern.
			if(flag){
				StraightBombCardPattern straightBomb=new StraightBombCardPattern(cards);
				return straightBomb;
				
			//Otherwise it is a simple straight
			}else{
				StraightCardPattern straight = new StraightCardPattern(cards);
				return straight;
			}
		
		}//End of checking for Straight(Bomb)CardPattern
		
		//At this point we are sure that the cards do not match any card pattern
						
		return null;
		
	}
	
}

