import main.SNSController;

public class Upload {
	
	public Upload() {

		try {
			
			SNSController snsC = new SNSController( "http://tse.topicplaces.com/api/2/" );
			
		} catch ( Throwable t ) {
			t.printStackTrace();
		}	
	}

}
