package Message.MessageRelated;


import com.google.gson.JsonObject;

import RESTCalls.Post;

public class OptionMaker {
	
	private String ENDPOINT;

	public OptionMaker( String end )
	{
		ENDPOINT = end;
	}
	
	public String createOptionForGame( String optionText, String gameID, String authC)
	{
		
		Post post = new Post( ENDPOINT + "options", authC );
		
		JsonObject json = new JsonObject();
		json.addProperty( "game", gameID);
		json.addProperty( "text", optionText );
		
		post.addJson( json );
		
		String response = post.execute();
		
		int index = response.indexOf("{\"val\":\"go-");
        
        if ( index == -1 ) return null;
        
        index = response.indexOf( "go-", index );
        
        return response.substring( 
        		index, 
        		response.indexOf( "\"", index ) 
        		); 
	}

}
