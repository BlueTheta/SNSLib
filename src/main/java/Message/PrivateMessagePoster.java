package Message;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import RESTCalls.Post;

/**
 * Created by servicedog on 7/8/15.
 */
public class PrivateMessagePoster {

    private String ENDPOINT;

    public PrivateMessagePoster(String end)
    {
        ENDPOINT = end;
    }

    public String execute( String mess, String desc, String mediaID, String authkey, String tid )
    {

    	Post post = new Post( ENDPOINT + "games", authkey );
    	
    	// Create JSON request
        JsonObject json = new JsonObject();

        JsonArray grou = new JsonArray();
        grou.add(new JsonPrimitive(tid));
        json.add("groups", grou);

        json.addProperty("text", mess );

        JsonObject meta = new JsonObject();
        meta.addProperty("description", desc);
        json.add("metadata", meta);

        JsonObject context = new JsonObject();
        context.addProperty( "media", mediaID );
        json.add( "context", context );

        // Add JSON entity to post
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
