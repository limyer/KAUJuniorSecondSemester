package Bank;

public class ATM {
		
	public static void main(String[] args) {
		ATMSystem sys = ATMSystem.getInstance(); //ATM기기, 인스턴스 하나만 존재
		sys.atmMainLoop(); // 메인루프
	}
}
