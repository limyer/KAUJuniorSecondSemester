package poker;

// 카드 저장
public class Card {
    private String face;
    private String suit;
    private int facenum=0;//Ace는 14로 처리 2~14 우선순위
    private int suitnum;//스페이스 다이아 하트 클로버 순 3,2,1,0 우선순위
    private Boolean isReveal = false; //뒤집혀 있는지 확인
    
    public Card(String cardFace, String cardSuit){
        face = cardFace;
        suit = cardSuit;
        // 스트링으로 점수 계산
        switch(face) {
        case "A":
        	this.facenum = 14;
        	break;
        case "2":
        	this.facenum=2;
        	break;
        case "3":
        	this.facenum=3;
        	break;
        case "4":
        	this.facenum=4;
        	break;
        case "5":
        	this.facenum=5;
        	break;
        case "6":
        	this.facenum=6;
        	break;
        case "7":
        	this.facenum=7;
        	break;
        case "8":
        	this.facenum=8;
        	break;
        case "9":
        	this.facenum=9;
        	break;
        case "10":
        	this.facenum=10;
        	break;
        case "J":
        	this.facenum=11;
        	break;
        case "Q":
        	this.facenum=12;
        	break;
        case "K":
        	this.facenum=13;
        	break;
        }
        switch(suit) {
        case "C":
        	this.suitnum=0;
        	break;
        case "H":
        	this.suitnum=1;
        	break;
        case "D":
        	this.suitnum=2;
        	break;
        case "S":
        	this.suitnum=3;
        	break;
        }
    }
    public String toString(){
        return face + suit;
    }

    public String getFace() {
        return face;
    }

    public String getSuit(){
        return suit;
    }
    public int getFaceNum() {
    	return facenum;
    }
    public int getSuitNum() {
    	return suitnum;
    }
    public Boolean getIsReveal() {
    	return isReveal;
    }
    public void setIsReveal(Boolean fold) {
    	isReveal = fold;
    }
 
}
