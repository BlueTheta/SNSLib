package RESTCalls;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.JsonObject;

public class Post {

	private CloseableHttpClient httpclient = null;
	public HttpPost httppost = null;
	public CloseableHttpResponse response = null;
	public int status = 500;

	public Post( String endpoint, String authC ) {
		httpclient = HttpClients.createDefault();
	
		httppost = new HttpPost( endpoint );
		
		if ( authC != null ) {
			addHeader("Cookie", authC);
		}
	}
	
	public void addHeader( String key, String value ) {
		httppost.addHeader( key, value );
	}
	
	public void setStringEntity( StringEntity sre ) {
		httppost.setEntity( sre );
	}
	
	public void setMultipartEntity( MultipartEntity me ) {
		httppost.setEntity( me );
	}
	
	
	public void setXWWWFormData( Iterable<? extends NameValuePair> nvp ) {
		try {
			UrlEncodedFormEntity uefe = new UrlEncodedFormEntity( nvp, StandardCharsets.UTF_8  );
			httppost.setEntity( uefe );
		} catch ( Throwable t ) {
			t.printStackTrace();
			System.out.println( "Could not attach NameValuePairList." );
		}
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
			response = httpclient.execute(httppost);
			jsonStream = response.getEntity().getContent();
		} catch ( Throwable t ) {
			t.printStackTrace();
		}

        status = response.getStatusLine().getStatusCode();
		InputStreamReader isr = new InputStreamReader( jsonStream );	
        BufferedReader nr = new BufferedReader( isr );
        
        try { 
        	String full = null;
        	String l = nr.readLine();
        	full = l;
        	while ( l != null ) {
        		l = nr.readLine();
        		full += l;
        	}
        	return full;
        } catch ( Throwable t ) {
        	t.printStackTrace();
            return null;
        }	
	}
}
