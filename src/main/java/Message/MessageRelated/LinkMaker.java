package Message.MessageRelated;


import com.google.gson.JsonObject;

import RESTCalls.Put;

public class LinkMaker {

	private String ENDPOINT;

	public LinkMaker(String end)
	{
		ENDPOINT = end;
	}
	
	public void createLinkForGame( String url, String GID, String authC)
	{
		Put put = new Put( ENDPOINT + "games/" + GID, authC );
		
		JsonObject json = new JsonObject();
		JsonObject context = new JsonObject();
		context.addProperty( "id", url);
		context.addProperty( "description", "Link to " + url );
		context.addProperty( "site_name", url.replaceAll( "http://", "" ) );
		context.addProperty( "title", "LINK" );
		context.addProperty( "url", url );
		json.add( "context",context );
		
		put.addJson( json );

		put.execute();
	}

}
