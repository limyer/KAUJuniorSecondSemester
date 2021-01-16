package poker;


import java.util.*;

public class PokerSystem {
    private DeckofCards myDeckOfCards = new DeckofCards();
    private List<Player> playerList = new ArrayList<Player>();
    private int check = 0;
    private int round = 1;
    private int numberOfPlayers = 5;
    private PokerGUI gui;
    Scanner scan = new Scanner(System.in);
    
    //생성자에서 GUI 실행
    public PokerSystem(){
    	gui = new PokerGUI(this);
    }
    public void resetPlayers() {
    	playerList.clear();
    }
    
    public List<Player> getPlayers(){
    	return playerList;
    }
   
    public void setNumberOfPlayer(){
    	this.numberOfPlayers = gui.getNumberOfPlayers();
    }

    public void setPlayerNames(List<String> names){
    	setNumberOfPlayer();
        for(int i = 0; i < numberOfPlayers; i++){
            playerList.add(new Player(names.get(i)));
        }
    }
    
    public void setEachPlayerCards(){
        myDeckOfCards = new DeckofCards();
        myDeckOfCards.shuffle();
        for(int num = 0; num < numberOfPlayers ; num++){
            Card[] playerCards = new Card[5];
            for (int i = 0; i < 5; i++) {
                playerCards[i] = myDeckOfCards.dealCard();
            }
            playerList.get(num).setPlayerCards(playerCards);
       }
    }
    
    public void sortPlayers() {
    	playerList.sort(new Comparator<Player>() {
    	       @Override
    	       public int compare(Player arg0, Player arg1) {
    	           arg0.sort();
    	           arg1.sort();
    	           int[] score2 = arg0.getPlayerRank().getResult();
    	           int[] score3 = arg1.getPlayerRank().getResult();

    	           if(score2[0]== score3[0]){
    	               if(score2[1]== score3[1]){
                           if(score2[2]== score3[2]){
                               return 0;
                           }else if(score2[2]< score3[2]){
                               return 1;
                           }else return -1;
                       }else if(score2[1]< score3[1]){
    	                   return 1;
                       }else return -1;
                   }else if(score2[0] < score3[0]){
    	               return 1;
                   }
    	           else return -1;
    	       }
    	});
    }
    
    public void resetAllPlayerCardStates() {
    	Iterator<Player> itr = this.playerList.iterator();
    	while(itr.hasNext()) {
    		itr.next().resetCardState();
    	}
    }
    
    public String getFirstPlayerName() {
    	return this.playerList.get(0).getPlayerName();
    }
    
    public Boolean checkAllPlayerReveal() {
    	Iterator<Player> itr = this.playerList.iterator();
    	
    	while(itr.hasNext()) {
    		if(!itr.next().getAllReveal()) return false;
    	}
    	return true;
    }
}
