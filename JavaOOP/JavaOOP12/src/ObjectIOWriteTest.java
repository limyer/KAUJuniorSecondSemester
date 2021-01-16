public class ObjectIOWriteTest{
	public static void main(String[] args) {
		ObjectIO application = new ObjectIO();
		
		application.startWriter();
		application.addRecords();
		application.closeWriter();

	}
}