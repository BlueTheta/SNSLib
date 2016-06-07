package Users;

import com.google.gson.JsonObject;

import RESTCalls.Post;

/**
 * Created by servicedog on 7/8/15.
 */
public class SubscriptionCreator {

    private String ENDPOINT;

    public SubscriptionCreator(String end)
    {
        ENDPOINT = end;
    }

    public String execute( String userID, String userToFollow, String authC )
    {
    	
    	Post post = new Post( ENDPOINT + "subscriptions", authC );
    	        
    	JsonObject json = new JsonObject();
        json.addProperty("follower", userID );
        json.addProperty("followee", userToFollow );
        json.addProperty( "autoAccept", true);

        post.addJson( json );

        String response = post.execute();
        
        int index = response.indexOf( "{\"val\":\"sub-" );
    	
	    if ( index == -1 ) return null;
	    
	    index = response.indexOf( "sub-", index );
	    
	    return response.substring( 
	    		index, 
	    		response.indexOf( "\"", index ) 
	    		);
    }

}
