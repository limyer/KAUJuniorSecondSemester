public class ObjectIOReadTest{
	public static void main(String[] args) {
		ObjectIO application = new ObjectIO();
		
		application.startReader();
		application.readRecords();
		application.closeReader();
	}
}