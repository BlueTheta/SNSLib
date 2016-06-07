package RESTCalls;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class Delete {

	private CloseableHttpClient httpclient = null;
	private HttpDelete httpdelete = null;
	
	public int status = 500;

	public Delete( String endpoint, String authC ) {
		httpclient = HttpClients.createDefault();
	
		httpdelete = new HttpDelete( endpoint );
		
		if ( authC != null ) {
			addHeader("Cookie", authC);
		}
	}
	
	public void addHeader( String key, String value ) {
		httpdelete.setHeader( key, value );
	}
	
	public void execute() {
		
		try {
			httpclient.execute(httpdelete);
		} catch ( Throwable t ) {
			t.printStackTrace();
		}
	}

	
}
