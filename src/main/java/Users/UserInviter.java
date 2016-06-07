package Users;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import RESTCalls.Post;

/**
 * Created by servicedog on 7/10/15.
 */
public class UserInviter {

    private String ENDPOINT;

    public UserInviter(String end) {
        ENDPOINT = end;
    }

    public String inviteUserToPID( String userID, String PID, String authC )
    {
    	Post post = new Post( ENDPOINT + "invitations/groups", authC );
        
    	JsonObject json = new JsonObject();
        JsonArray jsar = new JsonArray();

        JsonObject j = new JsonObject();
        j.addProperty("target", PID );
        j.addProperty("recipient", userID );
        jsar.add(j);
        json.add( "invitations", jsar );
        json.addProperty("autoAccept", true);

        post.addJson( json );
        
        String response = post.execute();
        
        int index = response.indexOf( "{\"val\":[\"i-" );
    	
	    if ( index == -1 ) return null;
	    
	    index = response.indexOf( "i-", index );
	    
	    return response.substring( 
	    		index, 
	    		response.indexOf( "\"", index ) 
	    		);
    }
}
