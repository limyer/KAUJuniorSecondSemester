package poker;


import java.util.Random;

//완성된 클래스 내가 볼 필요없음
public class DeckofCards {
    private Card[] deck;
    private int currentCard;
    private static final int NUMBER_OF_CARDS = 52;
    private static final Random randomNunbers = new Random();
   //생성자
    public DeckofCards(){
        String[] faces = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] suits = {"S","H","D","C"};
        deck = new Card[NUMBER_OF_CARDS];
        currentCard = 0;
        for(int count = 0; count<deck.length; count++){
            deck[count] = new Card(faces[count%13],suits[count/13]);
        }
    }
    //메서드
    // 셔플
    public void shuffle(){
        currentCard = 0;
        for(int first =0; first<deck.length; first++){
            int second = randomNunbers.nextInt(NUMBER_OF_CARDS);
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
    }
    //딜 카드
    public Card dealCard() {
        if (currentCard < deck.length) {
            return deck[currentCard++];
        } else {
            return null;
        }
    }
}
