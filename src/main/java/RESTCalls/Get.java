package RESTCalls;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class Get {

	private CloseableHttpClient httpclient = null;
	private HttpGet httpget = null;
	private CloseableHttpResponse response = null;
	public int status = 500;

	public Get( String endpoint, String authC ) {
		httpclient = HttpClients.createDefault();
	
		httpget = new HttpGet( endpoint );
		
		if ( authC != null ) {
			addHeader("Cookie", authC);
		}
	}
	
	public void addHeader( String key, String value ) {
		httpget.setHeader( key, value );
	}
	
	public String execute() {
		
		InputStream jsonStream = null;
		
		try {
			response = httpclient.execute(httpget);
			jsonStream = response.getEntity().getContent();
		} catch ( Throwable t ) {
			t.printStackTrace();
		}

        status = response.getStatusLine().getStatusCode();
		InputStreamReader isr = new InputStreamReader( jsonStream );	
        BufferedReader nr = new BufferedReader( isr );
        
        try { 
        	String l = nr.readLine();
        	return l;
        } catch ( Throwable t ) {
        	t.printStackTrace();
            return null;
        }
		
	}

	
}
