public class Calendar{
	public static void main( String[] args) {
		int thisYear = 2020;
		int thisMonth = 9;
		int lastDate = 30;
		int startDay = 3;
	
		String week[] = new String[] {
				"일", "월", "화", "수", "목", "금", "토"
		};
		
		System.out.println("\t\t    " + thisYear + "년 " + thisMonth + "월");
		
		for(int i=0; i<week.length; i++) {
			System.out.print(week[i] + "\t");
		}
		System.out.println();
		
        for(int i=1; i<startDay; i++) {
        	System.out.print("\t");
        }
		
        for(int i=1; i<=lastDate; i++) {
        	System.out.print(i + "\t");
        	if (startDay%7 == 0) System.out.println();
        	startDay++;
        }
		

		
	}
}