package onepair;

import java.util.Scanner;
import java.util.ArrayList;

public class GameSystem{
	private static GameSystem gamesystem = null;
	private DeckOfCards deck = new DeckOfCards();
	private static final int NUMBER_OF_DEALCARDS = 5;
	private Card[] dealCards;
	ArrayList<String> cardsString = new ArrayList<>();
	private Scanner input=new Scanner(System.in);
	String endInput;
	String printOutput;
	
	public GameSystem() {
		dealCards = new Card[NUMBER_OF_DEALCARDS];
		prepareNextGame();
	}
	
	public static GameSystem getInstance() {
		if(gamesystem == null) {
		}			gamesystem = new GameSystem();

		return gamesystem;
	}
	
	public void startGameLoop() {
		int gameNumber = 1;
		int pairResult = 0;
		
		while(!endInput.equalsIgnoreCase("end")) {
			for (int i = 0; i < NUMBER_OF_DEALCARDS; i++) {
				dealCards[i] = deck.dealCard();
				cardsString.add(dealCards[i].toString());
			}
			pairResult = findPair();
			
			printOutput=String.format("게임 %d: %s", gameNumber++, cardsString.toString());
			if (pairResult != 0) {
				printOutput = printOutput + "\t" + Integer.toString(pairResult) + " pair, Player Wins";
			}
			else {

				printOutput = printOutput +  "\t Dealer Wins";
			}
			
			System.out.println(printOutput);
			
			prepareNextGame();
		}
	}
	
	private int findPair() {
		int pairNum = 0;
		int face1 = 0;
		int face2 = 0;
		for (int i = 0; i < NUMBER_OF_DEALCARDS; i++) {
			face1 = dealCards[i].getFace();
			for (int j = 0; j < i; j++) {
				face2  = dealCards[j].getFace();
				if (face1 == face2 && face1 > pairNum) {
					pairNum = face1;
				}
			}
		}
		
		return pairNum;
	}
	
	private void prepareNextGame() {
		deck.shuffle();
		endInput = input.nextLine();
		cardsString.clear();
	}
	
	
}