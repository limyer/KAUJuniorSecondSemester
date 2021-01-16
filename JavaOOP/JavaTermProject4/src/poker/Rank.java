package poker;



//정렬된 상태에서 생성
public class Rank {
	private Card[] hands;
	private int score;
	//숫자 순으로 정렬을 해야하나. 
	private String[] name= {
			"High Card",
			"One Pair",
			"Two Pairs",
			"Triple",
			"Straight",
			"Back Straight",
			"Moutain",
			"Flush",
			"Straight Flush",
			"Full House",
			"Four Card",
			"Back Straight Flush",
			"Royal Straight Flush"
	};
	private int[] result=new int[3];
	
	public Rank(Card[] Hands) {
			hands=Hands;
			pokeRule();
			/*
			if(IsRoyalStraightFlush())
				this.score=12;
			else if(IsBackStraightFlush())
				this.score=11;
			else if(IsStraightFlush())
				this.score=10;
			else if(IsFourCard())
				this.score=9;
			else if(IsFullHouse())
				this.score=8;
			else if(IsFlush())
				this.score=7;
			else if(IsMountain())
				this.score=6;
			else if(IsBackStraight())
				this.score=5;
			else if(IsStraight())
				this.score=4;
			else if(IsTrips())
				this.score=3;
			else if(IsTwoPair())
				this.score=2;
			else if(IsPair()) {
				this.score = 1;

			}
			else {
				result[1] = hands[0].getFaceNum();
				result[2] = hands[0].getSuitNum();
				this.score = 0;
			}*/
		result[0]=this.score;
	}
	
	public int[] getResult() {
		return result;
	}
	
	public String toString() {
		return name[this.score];
	}

	public int getScore() {
		return score;
	}
/*
	public boolean IsRoyalStraightFlush() {
		if(!IsStraightFlush())
			return false;
		if(hands[0].getFaceNum()!=14)
			return false;
		return true;
	}
	public boolean IsBackStraightFlush() {
		if(!IsFlush())
			return false;
		if(hands[0].getFaceNum()!=14)
			return false;
		for(int i=1;i<hands.length-1;i++) {
			int j=i+1;
			if((hands[i].getFaceNum())-1!=hands[j].getFaceNum())
				return false;
		}
		return true;
	}
	public boolean IsStraightFlush() {
		if(IsStraight()&&IsFlush())
			return true;
		else return false;
	}
	public boolean IsFourCard(){
		int count=0;
		for(int i=0;i<hands.length-1;i++) {
			for(int j=i+1;j<hands.length;j++) {
				if(hands[i]==hands[j])
					count++;
			}
		}
		if(count<6)
			return false;

		else return true;
	}
	public boolean IsFullHouse(){
		int count=0;
		for(int i=0;i<hands.length-1;i++) {
			for(int j=i+1;j<hands.length;j++) {
				if(hands[i]==hands[j])
					count++;
			}
		}
		if(count<4)
			return false;
		else return true;
	}
	public boolean IsFlush() {
		for(int i=0;i<hands.length-1;i++) {
			int j=i+1;
			if(hands[i].getSuitNum()!=hands[j].getSuitNum())
				return false;
		}
		return true;
	}
	public boolean IsMountain() {
		if(IsStraight()&&hands[0].getFaceNum()==0)
			return true;
		else return false;
	}
	public boolean IsBackStraight() {
		if(hands[0].getFaceNum()!=0)
			return false;
		for(int i=1;i<hands.length-1;i++) {
			int j=i+1;
			if((hands[i].getFaceNum())-1!=hands[j].getFaceNum())
				return false;
		}
		return true;
	}
	public boolean IsStraight() {
		for(int i=0;i<hands.length-1;i++) {
			int j=i+1;
			if((hands[i].getFaceNum())-1!=hands[j].getFaceNum())
				return false;
		}
		return true;
	}
	public boolean IsTrips() {
		int count=0;
		for(int i=0;i<hands.length-1;i++) {
			for(int j=i+1;j<hands.length;j++) {
				if(hands[i].getFaceNum()==hands[j].getFaceNum())
					count++;
			}
		}
		if(count<3)
			return false;
		else 
			return true;
	}
	public boolean IsTwoPair() {
		int count=0;
		for(int i=0;i<hands.length-1;i++) {
			for(int j=i+1;j<hands.length;j++)
				if(hands[i].getFaceNum()==hands[j].getFaceNum())
					count++;
		}
		if(count<2)
			return false;
		else return true;
	}
	public boolean IsPair() {
		for(int i=0;i<hands.length-1;i++) {
			for(int j=i+1;j<hands.length;j++){
				if(hands[i].getFaceNum()==hands[j].getFaceNum()) {
					result[1] = hands[i].getFaceNum();
					result[2] = hands[i].getSuitNum();
					return true;
				}
			}
		}
		return false;
	}
*/

	void pokeRule(){
		int score = 0;
	    result[1] = hands[0].getFaceNum();
	    result[2] = hands[0].getSuitNum();
		score = onepair();// 1 == onepair 2 == twopair or triple  8 == fourcard 9 == FULLHOUSE
		if(score==0){
			score = plash(score);
			if(score==0){
				score = straight(score);
			}
		}
		else if(score==2){// twopair 판단
			score = twoPair();
		}
		this.score = score;
		result[0]=this.score;
	}
	 int onepair(){
	      int onepair = 0;
	      int i = 0;
	      // onepair 판단
	      int temp = hands[0].getFaceNum();
	      int tempSuit = hands[0].getSuitNum();
	      while(i<4) {
	         if(temp == hands[i+1].getFaceNum()) {
	            result[1] = temp;
	            result[2] = tempSuit;
	            onepair ++;
	         }else{
	            temp = hands[i+1].getFaceNum();
	            tempSuit = hands[i+1].getSuitNum();
	         }i++;
	      }
	      if(onepair==3){
	         if(hands[1].getFaceNum() ==hands[2].getFaceNum() && hands[2].getFaceNum() == hands[3].getFaceNum()) {
	            if(hands[0].getFaceNum() == hands[2].getFaceNum()){
	               result[1] = hands[0].getFaceNum();
	               result[2] = hands[0].getSuitNum();
	            }else {
	               result[1] = hands[1].getFaceNum();
	               result[2] = hands[1].getSuitNum();
	            }onepair = 9; // fourcard
	         }else {
	            if(hands[0].getFaceNum() == hands[2].getFaceNum()){
	               result[1] = hands[0].getFaceNum();
	               result[2] = hands[0].getSuitNum();
	            }else {
	               result[1] = hands[2].getFaceNum();
	               result[2] = hands[2].getSuitNum();
	            }onepair = 8; // fullhouse
	         }
	      }
	      return onepair;
	   }
	   int twoPair(){
	      int twopair = 2;
	      int i = 0;
	      int same = 0;
	      int temp;
	      while (i<3){
	         temp = hands[i].getFaceNum();
	         for(int j= i ;j<5;j++){
	            if(temp == hands[j].getFaceNum()){
	               same++;
	            }
	         }if(same==3){
	            break;
	         }same = 0;
	         i++;
	      }if(hands[0].getFaceNum()==hands[1].getFaceNum()){
	         result[1] = hands[0].getFaceNum();
	         result[2] = hands[0].getSuitNum();
	      }else{
	         result[1] = hands[1].getFaceNum();
	         result[2] = hands[1].getSuitNum();
	      }
	      if(same >= 3){
	         int j =0;
	         while(j<3){
	            if(hands[j].getFaceNum()==hands[2].getFaceNum()){
	               result[1] = hands[j].getFaceNum();
	               result[2] = hands[j].getSuitNum();
	               break;
	            }j++;
	         }
	         twopair = same; // triple
	      }
	      return twopair;
	   }

	   int plash(int score){
	      int plash = 7;
	      int temp = hands[0].getSuitNum();
	      int i = 0;
	      while(i<5){
	         if(temp != hands[i].getSuitNum()){
	            plash = score; // 플러시가 아님
	            break;
	         }i++;
	      }// 스트레이트 플러시 판단.
	      if(plash== 7){
	         plash = straight(score);
	         if(plash==4){
	            plash = 10;// 스플
	         }else if(plash == 5){
	            plash = 11;// 백스플
	         }else if(plash == 6){
	            plash = 12;// 로스플
	         }
	         result[1] = hands[0].getFaceNum();
	         result[2] = hands[0].getSuitNum();
	      }
	      return plash;
	   }

	   int straight(int score){
	      int straight = 4;
	      int i = 0;
	      //스트레이트가 아닌경우 빠져 나옴
	      while(i<4){
	         if((hands[i].getFaceNum())-1 != hands[i+1].getFaceNum()){
	            //System.out.println( ((hands[i].getFaceNum())-1) + " not same "+ hands[i+1].getFaceNum());
	            straight = score;
	            break;
	         }i++;
	      }if(hands[0].getFaceNum()==14 && straight == 4){
	         int j = 1;
	         // 백스트레이트 판단
	         while(j<4){
	            if(hands[j].getFaceNum()-1 != hands[j+1].getFaceNum()) {
	               straight = score;
	               break;
	            }j++;
	            straight = score;
	         }
	         if(hands[0].getFaceNum()-1 != hands[1].getFaceNum()){
	            straight = 5;
	            result[1] = hands[0].getFaceNum();
	            result[2] = hands[0].getSuitNum();
	         }
	      }
	      return straight;
	   }
}
