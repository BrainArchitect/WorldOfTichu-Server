package tablePackage;

import java.util.ArrayList;

import cards.Card;
import cards.CardPattern;
import cards.CardPatternFactory;
import cards.patterns.*;
import clientPackage.Client;

public class Game {
	
	//private Table table;
	
	private Deck deck;
	private Trick trick;

	private int continueCounter;
	private int exchangeCounter;	
	private int passes;
	
	private boolean wishActivated;
	private int wishNumber;

	
	private int winningSeatNo;
	private boolean dragonPlayed;
	//Seat of player that has the dragon
	private int dragonMasterSeat;
	
	
	private Player[] players;

		
	private int pointsLimit;
	private int minutesLimit;
	
	//Num of players that their cardList is not empty
	private int activePlayers;
	


	
	private boolean endOfHand;
	private boolean endOfGame;
	
	private int sumTeam1Points;
	private int sumTeam2Points;
	
	private static final int[] NEXT_SEAT = new int[]{1, 2, 3, 0};

	private int nextPlayerSeat;
	
	public Game(Player[] players){
		this.players = players;
		deck = new Deck();
		trick = new Trick();

			
		
		
	}	
	
	public void start(){
		reset();
	}


	private void reset(){
		this.passes = 0;
		this.winningSeatNo = -1;
		this.continueCounter = 0;
		this.exchangeCounter = 0;
		this.wishActivated = false;
		this.wishNumber = -1;
		this.dragonPlayed = false;
		this.activePlayers = 4;
		this.endOfHand = false;
		this.endOfGame = false;
		
		
		//Return players Cards to Deck & Reset Players
		for(Player player: players){
			if(player.getCardsAtHand()!=null){
				deck.addCardsToDeck(player.getCardsAtHand());
				for(CardPattern cardPattern: player.getCardsCollected()){
					if(cardPattern!=null){
						deck.addCardsToDeck(cardPattern.getListOfCards());
					}
				}
			}
			player.reset();
		}
		
		//Return trick cards to Deck & reset Trick.
		if(trick.getCardsAtTrick()!=null){
			for(CardPattern cardPattern: trick.getCardsAtTrick()){
				if(cardPattern!=null){
					deck.addCardsToDeck(cardPattern.getListOfCards());
				}
			}
			trick.reset();
		}
		deck.shuffle();
		this.dealCards(8);
	
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
			ArrayList<Card> cards = deck.dealCards(ammount);
			players[i].addCardsAtHand(cards);
		}
	}	
	
	public ArrayList<Card> toCards(int seatNo, ArrayList<String> cards){
		ArrayList<Card> tempList = new ArrayList<Card>();
		
		while (!cards.isEmpty()){
			String tempCard=cards.get(0);
			int  value;
			int color;
			try{
				 value = Integer.parseInt(tempCard.substring(0, 2));
				 color = Integer.parseInt(tempCard.substring(2, 3));
			}catch(NumberFormatException e){
				return null;
			}
			for(Card card: players[seatNo].getCardsAtHand()){
				if(card.getValue()==value && card.getColor()==color){
					cards.remove(tempCard);
					tempList.add(card);
					break;
				}
			}
		}			
	
		//If all cards successfuly parsed
		if(cards.isEmpty()){
			return tempList;
		//Else check Phoenix use
		}else if(cards.size()==1){
			boolean hasPhoenix = false;
			Card phoenixCard = null;
			for(Card card: players[seatNo].getCardsAtHand()){
				if(card.isPhoenix()){
					phoenixCard = card;
					hasPhoenix = true;
					break;
				}
			}
			if(hasPhoenix){
				String tempCard=cards.get(0);
				int  value;
				int color;
				try{
					 value = Integer.parseInt(tempCard.substring(0, 2));
					 color = Integer.parseInt(tempCard.substring(2, 3));
					 if(color==Card.SPECIAL_CARD_COLOR ){
						 if(value>Card.MAHJONG_VALUE && value<Card.DRAGON_VALUE){
							 tempList.add(phoenixCard);
						 }
					 }
				}catch(NumberFormatException e){
					return null;
				}
			}
		}
		return null;
	}
	
	


	public synchronized void tossCardPatern(int seatNo, ArrayList<String> anyCards){
		dragonPlayed = false;
		boolean acceptableToss = false;
		
		
		CardPatternFactory factory = new CardPatternFactory();
		CardPattern cardPattern = factory.createCardPattern(toCards(seatNo, anyCards));

		if(cardPattern==null){
			try{
				throw new Exception();
			}catch(Exception e){
				System.err.println("This is a client hacker exception");
				return;
			}
		}
		
		if(wishActivated){
			if(!isWishCompliant(cardPattern)){
				//if(checkCombinationsForWIsh()){
					
				//}
			}
		}
		
		CardPattern lastTrickCardPattern = trick.getLastPlayedCardPattern();
		if(!(cardPattern.compareTo(lastTrickCardPattern)>0)){
			System.err.println("This is a client hacker or logical exception");
			return;
		}
		

		winningSeatNo = seatNo;
		nextPlayerSeat = NEXT_SEAT[seatNo];
		passes = 0;
		
		if (cardPattern.isOneCardPattern()){
				if(cardPattern.getCard(0).isDragon()){
					dragonPlayed = true;
					dragonMasterSeat = seatNo;
				}else if(cardPattern.getCard(0).isDog()){
					nextPlayerSeat = NEXT_SEAT[NEXT_SEAT[seatNo]];
				}else if(cardPattern.getCard(0).isMahjong() && wishActivated){
					
				}else if(cardPattern.getCard(0).isPhoenix()){
					lastTrickCardPattern = trick.getCardsAtTrick().get(trick.getCardsAtTrick().size()-2);
					if(lastTrickCardPattern.isOneCardPattern()){
						cardPattern.getCard(0).setValue(lastTrickCardPattern.getCard(0).getValue());
						
					}
				}
		}

	}
	
	private boolean isWishCompliant(CardPattern cardPattern) {
		return false;
		
	}

	public synchronized void makeAWish(int seatNo, String value){

	}
	
	public synchronized void bomb(int seatNo){

	}	
	
	public synchronized void incrementPasses(int seatNo){
		passes ++;
		int activePlayers = 0;
		
		if(passes == activePlayers-1){	
			if (dragonPlayed){
				
			}else{

				
			}
		}
		
		int nextPlayerSeat = NEXT_SEAT[seatNo];
		while (players[nextPlayerSeat].getCardsAtHand().isEmpty())
			nextPlayerSeat = NEXT_SEAT[nextPlayerSeat];

	}	
	
	public synchronized void dragon(int luckyOpponentSeat){
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
				if(players[0].getCardsAtHand().isEmpty() && players[2].getCardsAtHand().isEmpty()){
					team1points += 200;
					endOfHand= true;
				}else if(players[1].getCardsAtHand().isEmpty() && players[3].getCardsAtHand().isEmpty()){
					team2points += 200;
					endOfHand= true;
				}
			
				/*
				if(endOfHand){
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
	
