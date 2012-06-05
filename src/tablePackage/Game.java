package tablePackage;

import java.util.ArrayList;

import cards.Card;
import cards.patterns.*;
import clientPackage.Client;

public class Game {
	private Deck deck;

	private int continueCounter;
	private int exchangeCounter;
	
	private int passes;
	private int winningSeatNo;
	private int trickPoints;
	
	private ArrayList<Card>[] cardLists ;
	
	private int wish;
	private boolean dragon;
	
	//Seat of player that has the dragon
	private int dragonMasterSeat;
	
	private int pointsLimit;
	private int minutesLimit;
	
	//Num of players that their cardList is not empty
	private int activePlayers;
	
	//Represents the tichu said value of all players
	//0-no tichu
	//1-small tichu
	//2-large tichu

	
	private boolean endOfHand;
	private boolean endOfGame;
	
	private int sumTeam1Points;
	private int sumTeam2Points;
	
	private static final int[] NEXT_SEAT = new int[]{1, 2, 3, 0};

	private int nextPlayerSeat;
	
	public Game(Table table, Client host, String password){
		cardLists = (ArrayList<Card>[])new ArrayList[4];
		deck = new Deck();
		
	}	
	
	public void start(){
		deck.shuffle();
		this.dealCards(8);			
	}


	private void reset(){
		this.passes = 0;
		this.winningSeatNo = -1;
		this.trickPoints=0;
		this.continueCounter = 0;
		this.exchangeCounter = 0;
		this.wish = 0;
		this.dragon = false;
		this.activePlayers = 4;
		this.endOfHand = false;
		this.endOfGame = false;
	}
	

	public int getPointLimit(){ return pointsLimit; }
	public int getMinutesLimit(){ return minutesLimit; }

	
	/**
	 * This method deals only the first 8 cards(round robin) for 
	 * each player of the game that owns this deck. The dealing of
	 * the cards should be done after the deck has been shuffled.
	 */
	public void dealCards(int ammount){
		for (int i=0; i<4; i++){
			ArrayList<Card> cards = deck.next(ammount);
			cardLists[i].addAll(cards);
		}
	}	
	
	public ArrayList<Card> toCards(int seatNo, ArrayList<String> cards){
		ArrayList<Card> tempList = new ArrayList<Card>();
		for(String tempCard : cards){
			int  value;
			int color;
			try{
				 value = Integer.parseInt(tempCard.substring(0, 2));
				 color = Integer.parseInt(tempCard.substring(2, 3));
			}catch(NumberFormatException e){
				return null;
			}
			for(Card card: cardLists[seatNo]){
				if(card.getValue()==value && card.getColor()==color){
					tempList.add(card);
					break;
				}
			}
			
		}
		
		if(tempList.size()==cards.size()){
			return tempList;
		}
		return null;
	}
	
	


	public synchronized void tossCardPatern(int seatNo, ArrayList<String> anyCards){
		dragon = false;
		CardPatternFactory factory = new CardPatternFactory();
		CardPattern cardPattern = factory.createCardPattern(toCards(seatNo, anyCards));

		if (cardPattern.isOneCardPattern() && cardPattern.getCard(0).isDragon()){
			dragon = true;
			dragonMasterSeat = seatNo;
		}

		winningSeatNo = seatNo;
				
		int currentPlayerSeat = seatNo;	
		nextPlayerSeat = NEXT_SEAT[currentPlayerSeat];
					
		passes = 0;
	}
	
	public synchronized void makeAWish(Client client, String value){

	}
	
	public synchronized void bomb(Client client){

	}	
	
	public synchronized void incrementPasses(int seatNo){
		passes ++;
		int activePlayers = 0;
		
		if(passes == activePlayers-1){	
			if (dragon){
				
			}else{

	
			}
		}
		
		int nextPlayerSeat = NEXT_SEAT[seatNo];
		while (cardLists[nextPlayerSeat].isEmpty())
			nextPlayerSeat = NEXT_SEAT[nextPlayerSeat];

	}	
	
	public synchronized void dragon(int luckyOpponentSeat){
		trickPoints = 0;
		passes = 0;
	}
	
	public synchronized void incrementContinues(){
			this.continueCounter ++;
		if (continueCounter == 4){
			dealCards(6);
			continueCounter = 0;
		}
	}
	
	public synchronized void incrementExchanges(){
		this.exchangeCounter ++;
		
		if (exchangeCounter == 4){

		}


		exchangeCounter = 0;
		
	}
	
	public synchronized void exchangeCard(Client client, int receiverSeat, String card){

	}
	
	
	public synchronized int calculatePoints(ArrayList<Card> cards){
		
		int points=0;		
		for(Card card : cards){
			points+=card.getPoints();
		}
		return points;
	}	
	
	public synchronized void checkforFinish(){
		
			int team1points=0;
			int team2points=0;
			//If the Hand is finished with 1-2
			if(activePlayers==2){
				if(cardLists[0].isEmpty() && cardLists[2].isEmpty()){
					team1points += 200;
					endOfHand= true;
				}else if(cardLists[1].isEmpty() && cardLists[3].isEmpty()){
					team2points += 200;
					endOfHand= true;
				}
				
				/*if(endOfHand){
					int sign[]= new int[4];
					for(int i=0;i<4;i++){
						if(tichuMastersSeat[0]==i){
							sign[i]=1;
						}else{
							sign[i]=-1;
						}
					}
					
					team1points += sign[0]*tableClients.get(0).getTichuType()*100;
					team1points += sign[1]*tableClients.get(1).getTichuType()*100;
					team2points += sign[2]*tableClients.get(2).getTichuType()*100;
					team2points += sign[3]*tableClients.get(3).getTichuType()*100;
					
					for(TableClient tc: tableClients){
						tc.getClient().endOfHand(team1points, team2points);
						reset();
					}
					
					sumTeam1Points += team1points;
					sumTeam2Points += team1points;
					
					if(sumTeam1Points>=pointsLimit || sumTeam2Points>=pointsLimit){
						endOfGame=true;
					}
					
					//if time expired 
					//endOfGame=true;
					
					if(endOfGame){
						for(TableClient tc: tableClients){
							tc.getClient().endOfGame(sumTeam1Points, sumTeam2Points);
						}
					}
				}


			//If the Hand is finished normally
			}else if(activePlayers==1){
				int lastPlayerSeat=-1;
				endOfHand =true;
				
				for(int seatNo=0;seatNo<4;seatNo++){
					if(!tableClients.get(seatNo).isFinished()){
						lastPlayerSeat=seatNo;
						break;
					}
				}

				if(dragon){
					tableClients.get(lastPlayerSeat).addToTrickPoints(trickPoints);
					trickPoints=0;
				}else{
					tableClients.get(tichuMastersSeat[3]).addToTrickPoints(trickPoints);
					trickPoints=0;
				}
				
				tableClients.get(tichuMastersSeat[0]).addToTrickPoints(tableClients.get(lastPlayerSeat).getTrickPoints());
				tableClients.get(lastPlayerSeat).resetTrickPoints();
				
				if(lastPlayerSeat==0 || lastPlayerSeat==1){
					team2points += calculatePoints(tableClients.get(lastPlayerSeat).getCards()); 
				}else if(lastPlayerSeat==2 || lastPlayerSeat==3){
					team1points += calculatePoints(tableClients.get(lastPlayerSeat).getCards()); 
				}

				
				if(endOfHand){
					int sign;
					for(int i=0;i<4;i++){
						if(tichuMastersSeat[0]==i){
							sign=1;
						}else{
							sign=-1;
						}
						tableClients.get(i).addToTrickPoints(sign*tableClients.get(i).getTichuType()*100);
					}
					
					team1points = team1points + tableClients.get(0).getTrickPoints() + tableClients.get(1).getTrickPoints();
					team2points = team2points + tableClients.get(2).getTrickPoints() + tableClients.get(3).getTrickPoints();
					
				
					for(TableClient tc: tableClients){
						tc.getClient().endOfHand(team1points, team2points);
						reset();
					}
					
					sumTeam1Points += team1points;
					sumTeam2Points += team1points;
					
					if(sumTeam1Points>=pointsLimit || sumTeam2Points>=pointsLimit){
						endOfGame=true;
					}
					
					//if time expired 
					//endOfGame=true;
					
					if(endOfGame){
						for(TableClient tc: tableClients){
							tc.getClient().endOfGame(sumTeam1Points, sumTeam2Points);
						}
						endOfGame=false;
					}
				}*/
		
			}
	}
	
}