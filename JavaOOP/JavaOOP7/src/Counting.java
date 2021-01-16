import java.util.*;

public class Counting{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Map<String, Integer> map = new HashMap<String, Integer>();

		String sentence = "";
		String inputString;
		
		while (input.hasNextLine()) {
			inputString = input.nextLine();
			if (inputString.isEmpty()) {
				break;
			}
			sentence += inputString + "\n";
		}
		
		sentence = sentence.toLowerCase();

		String token = "";
		
		for(int i = 0; i < sentence.length(); i++) {
			if(Character.isLetter(sentence.charAt(i))) {
				token += sentence.charAt(i);
			}
			else{
				if (token != "") {
					Integer count = map.get(token);
					if (count == null) {
						count = 1;
					}
					else {
						count++;
					}
					map.put(token, count);
					token = "";
				}
			}
		}
        List<String> list = new ArrayList<>();
        list.addAll(map.keySet());

        Collections.sort(list, 
        		(o1, o2) -> (map.get(o2).compareTo(map.get(o1))));
        
        System.out.printf("%-12s%6s\n", "´Ü¾î", "È½¼ö");
        
		for(String key : list) {
			System.out.printf("%-12s%8d\n", key, map.get(key));
		}

		input.close();	
	}
	
	
	
	
}