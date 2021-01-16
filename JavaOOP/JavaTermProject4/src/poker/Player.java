package poker;

import java.util.*;

public class Player {
    private Card[] playerCards;
    private String playerName;
    //정렬 위해 선언
	private int[] faces;
	private int[] suits;
	//
	private Rank playerRank;
	private Boolean allReveal = false;
	
    public Player(String playerName){
        this.playerName = playerName;
        this.playerCards = new Card[5];
    }

    public void setPlayerCards(Card[] playerCards) {
        this.playerCards = playerCards;
        //여기다 rank 추가
        faces=new int[playerCards.length];
        suits=new int[playerCards.length];
    }
    //정렬
	public void sort() {
		for(int i =0; i<playerCards.length; i++){
			for (int j= i+1; j<playerCards.length; j++){
				if(playerCards[i].getFaceNum() < playerCards[j].getFaceNum()) {
					Card[] tempCard = new Card[1];
					tempCard[0] = playerCards[i];
					playerCards[i] = playerCards[j];
					playerCards[j] = tempCard[0];
				}
				if ((playerCards[i].getFaceNum() == playerCards[j].getFaceNum())
						&& (playerCards[i].getSuitNum() < playerCards[j].getSuitNum())) {
					Card[] tempCard = new Card[1];
					tempCard[0] = playerCards[i];
					playerCards[i] = playerCards[j];
					playerCards[j] = tempCard[0];
				}
			}
		}
        for(int i=0;i<playerCards.length;i++) {
            faces[i]=playerCards[i].getFaceNum();
            suits[i]=playerCards[i].getSuitNum();
        }
	}

	// 이름 가져오기
    public String getPlayerName(){
        return playerName;
    }

    //스트링 표현
    public String getCards(){
        String hidden;
        hidden = playerCards[0] +", "+playerCards[1] +", " +playerCards[2] +", " +playerCards[3] +", " +playerCards[4];
        return hidden;
    }
    //추가 내 핸드 리턴
    public Card[] getPlayerCards() {
    	return playerCards;
    }

    //손패 세팅//
    // 플레이어의 랭크 세팅 과정임 이과정은 패가 다 뒤집어 지면 이루워 져야함.
    public void setPlayerRank() {
        // 정렬 후 rank를 판단 하여야함 따라서 정렬후 코드가 실행 되도록함. 그리고 RANK에 배열을 추가해서 패를 보여주도록 코딩해야할듯
        sort();
    	playerRank=new Rank(playerCards);
    }

    public Rank getPlayerRank() {
    	return playerRank;
    }
    
    public Boolean getAllReveal() {
    	return allReveal;
    }
    
    public void setAllReveal(Boolean fold) {
    	allReveal = fold;
    }
    
    public void resetCardState() {
    	for(int i = 0; i < playerCards.length; i++) {
    		playerCards[i].setIsReveal(false);
    	}
    	allReveal = false;
    }
}
