package Users;

import com.google.gson.JsonObject;

import RESTCalls.Post;

/**
 * Created by servicedog on 7/10/15.
 */
public class UserCreator {

    private String ENDPOINT;

    public UserCreator( String end ) {
        ENDPOINT = end;
    }

    public String createUser( String name, String username, String email, String password )
    {
    	Post post = new Post( ENDPOINT + "users", null );

        // Create the message object.
        JsonObject json = new JsonObject();
        json.addProperty("name", name );
        json.addProperty("username", username );
        json.addProperty( "email", email );
        json.addProperty( "password", password );
        json.addProperty( "password2", password );
        json.addProperty( "isHidden", false );
        
        post.addJson( json );
        
        String response = post.execute();
        
        int index = response.indexOf( "{\"val\":\"u-" );
    	
	    if ( index == -1 ) return null;
	    
	    index = response.indexOf( "u-", index );
	    
	    return response.substring( 
	    		index, 
	    		response.indexOf( "\"", index ) 
	    		);
    }
}
