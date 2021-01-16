package onepair;
public class Card{
	private int face;
	private String suit;
	
	public Card(int cardFace, String cardSuit) {
		face = cardFace;
		suit = cardSuit;
	}
	
	public String toString() {
		return suit + Integer.toString(face);
	}
	
	public int getFace() {
		return face;
	}
}