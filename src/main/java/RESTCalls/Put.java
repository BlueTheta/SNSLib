package RESTCalls;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.JsonObject;

public class Put {

	private CloseableHttpClient httpclient = null;
	private HttpPut httpput = null;
	private CloseableHttpResponse response = null;
	public int status = 500;

	public Put( String endpoint, String authC ) {
		httpclient = HttpClients.createDefault();
	
		httpput = new HttpPut( endpoint );
		
		if ( authC != null ) {
			addHeader("Cookie", authC);
		}
	}
	
	public void addHeader( String key, String value ) {
		httpput.setHeader( key, value );
	}
	
	public void setStringEntity( StringEntity sre ) {
		httpput.setEntity( sre );
	}
	
	public void setMultipartEntity( MultipartEntity me ) {
		//addHeader( "Content-Type", "multipart");
		httpput.setEntity( me );
	}
	
	public void addJson( JsonObject json ) {
		addHeader( "Content-Type", "application/json" );
		try {
        	StringEntity params = new StringEntity( json.toString() );
            setStringEntity( params );
        } catch ( Throwable t ) {
        	t.printStackTrace();
        	System.out.println( "Could not add JSON." );
        }
	}
	
	public String execute() {
		
		InputStream jsonStream = null;
		
		try {
			response = httpclient.execute(httpput);
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
