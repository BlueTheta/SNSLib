package Topics;

import com.google.gson.JsonObject;

import RESTCalls.Put;

/**
 * Created by servicedog on 7/8/15.
 */
public class TopicUpdater {

    private String ENDPOINT;

    public TopicUpdater(String end)
    {
        ENDPOINT = end;
    }

    public void execute( String mess, String desc, String mediaID, Boolean priv, String Tid, String authkey ) {

        updateTopic(mess, desc, mediaID, priv, Tid, authkey);
    }

    public void updateTopic(String mess, String desc, String mediaID, Boolean priv, String tid, String authkey)
    {
    	Put put = null;
    	
        if ( priv ) {
            put = new Put(ENDPOINT + "groups/" + tid, authkey );
        } else {
            put = new Put(ENDPOINT + "topics/" + tid, authkey );
        }
        
        JsonObject json = new JsonObject();

        if ( mess != null ) {
            json.addProperty( "name", mess );
        }

        if ( desc != null ) {
            json.addProperty("description", desc );
        }

        put.addJson( json );
        put.execute();
    }
}
