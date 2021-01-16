public class FormattedIOWriteTest{
	public static void main(String[] args) {
		FormattedIO application = new FormattedIO();
		
		application.startWriter();
		application.addRecords();
		application.closeWriter();

	}
}