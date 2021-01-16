public class FormattedIOReadTest{
	public static void main(String[] args) {
		FormattedIO application = new FormattedIO();
		
		application.startReader();
		application.readRecords();
		application.closeReader();
	}
}