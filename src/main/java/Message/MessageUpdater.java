package Message;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import RESTCalls.Put;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by servicedog on 7/8/15.
 *
 *
 * FIX MEDIA
 *
 *
 *
 *
 *
 *
 */
public class MessageUpdater {

    private String ENDPOINT;

    public MessageUpdater(String end)
    {
        ENDPOINT = end;
    }

    public void addAttributes( Map<String, String> map, String gid, String authkey ) {

        if ( !map.isEmpty() ) {
            updateGame( null, null, null, map, null, gid, authkey );
        } else {
            System.out.println("No attributes to add.");
        }


    }
    public void addOptions( Map<String, String> map, String gid, String authkey ) {

        if ( !map.isEmpty() ) {

            updateGame( null, null, null, map, null, gid, authkey );
        } else {
            System.out.println("No options to add.");
        }


    }

    public void execute( String mess, String desc, String mediaID, String gid, String authkey ) {

        updateGame( mess, desc, mediaID, null, null, gid, authkey);
    }

    public void updateGame(String mess, String desc, String mediaID, Map<String, String> attributes, Map<String, String> options, String gid, String authkey)
    {

    	Put put = new Put( ENDPOINT + "games/" + gid, authkey );
    	
        JsonObject json = new JsonObject();

        if ( mess != null ) json.addProperty("text", mess);

        if ( desc != null ) {
            JsonObject meta = new JsonObject();
            meta.addProperty("description", desc);
            json.add("metadata", meta);
        }

        
        if ( mediaID != null ) {
            JsonObject context = new JsonObject();
            context.addProperty( "media", mediaID );
            json.add( "context", context );
        }

        if ( attributes != null ) {

            JsonArray jsar = new JsonArray();

            Iterator<String> i = attributes.keySet().iterator();

            ArrayList<String> arl = new ArrayList<String>();


            while ( i.hasNext() ) {
                arl.add( i.next() );
            }

            for ( String name : arl ) {
                JsonObject j = new JsonObject();
                String value = attributes.get( name );
                j.addProperty("type", "STRING" );
                j.addProperty("name", name );
                j.addProperty("value", value );

                jsar.add( j );
            }

            json.add("attributes", jsar );

        }


        if ( options != null ) {

            JsonArray jsar = new JsonArray();

            ArrayList<String> opts = new ArrayList<String>();
            Iterator<String> it = options.keySet().iterator();
            while ( it.hasNext() ) {
                opts.add( it.next() );
            }

            Collections.sort( opts );

            for ( String name : opts ) {
                JsonObject j = new JsonObject();
                j.addProperty("text", name );

                jsar.add( j );
            }

            json.add( "options", jsar );

        }
        
        put.addJson( json );
        
        String response = put.execute();
    }

}
