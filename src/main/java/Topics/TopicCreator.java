package Topics;

import com.google.gson.JsonObject;

import RESTCalls.Post;

/**
 * Created by servicedog on 7/8/15.
 */
public class TopicCreator {

    private String ENDPOINT;

    public TopicCreator(String end) {
        ENDPOINT = end;
    }

    public String createTopic( String title, boolean priv, String authC )
    {
    	
    	Post post = null;
    	
    	if ( priv ) {
            post = new Post(ENDPOINT + "groups", authC );
        } else {
            post = new Post(ENDPOINT + "topics", authC );
        }
    	
    	// Create the groups array for the groups property.
        JsonObject json = new JsonObject();
        json.addProperty("name", title );
        json.addProperty( "title", title );
        json.addProperty( "description", "" );
        json.addProperty( "read_only", false );

        post.addJson( json );
        
        String response = post.execute();

        String beginning = null;
        if ( priv ) {
        	beginning = "grp-";
        
        } else {
            beginning = "t-";
        }
        
    	int index = response.indexOf( "{\"val\":\"" + beginning );
	    	
	    if ( index == -1 ) return null;
	    
	    index = response.indexOf( beginning, index );
	    
	    return response.substring( 
	    		index, 
	    		response.indexOf( "\"", index ) 
	    		);
    }
}
