package Message;
import RESTCalls.Post;

import com.google.gson.JsonObject;


/**
 * Created by servicedog on 7/8/15.
 */
public class MessagePoster {

    private String ENDPOINT;

    public MessagePoster(String end)
    {
        ENDPOINT = end;
    }

    public String execute( String mess, String desc, String mediaID, String authkey, String tid )
    {
    	
        Post post = new Post( ENDPOINT + "games", authkey );
        
        // Create JSON Request
        JsonObject json = new JsonObject();
        json.addProperty( "topic", tid );
        json.addProperty("text", mess);
        
        JsonObject meta = new JsonObject();
        meta.addProperty("description", desc);
        json.add("metadata", meta);

        JsonObject context = new JsonObject();
        context.addProperty( "media", mediaID );
        json.add( "context", context );
        
        // Add request to POST call
        post.addJson( json );
        
        // Execute call
        String response = post.execute();
        
        int index = response.indexOf("{\"val\":\"g-");
        
        if ( index == -1 ) return null;
        
        index = response.indexOf( "g-", index );
        
        return response.substring( 
        		index, 
        		response.indexOf( "\"", index ) 
        		); 
            
    }

}
