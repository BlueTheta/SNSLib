package Users;

import RESTCalls.Get;

/**
 * Created by servicedog on 7/11/15.
 */
public class UserRetriever {

    private String ENDPOINT;

    public UserRetriever(String end) {

        ENDPOINT = end;

    }

    public String getUserFromIDorEmail( String ID ) {

        String s = getJSON( ID );

        int ind2 = s.indexOf("{\"val\":\"u-");

        if ( ind2 == -1 ) {
            return null;
        }

        ind2 = ind2 + 8;

        String userID = "";
        while (s.charAt(ind2) != '\"')
        {
            userID += s.charAt(ind2);
            ind2++;
        }

        if ( userID.equals( "" ) ) {
            return null;
        } else {
            return userID;
        }
    }

    public String getUsernameFromID( String id ) {
        String s = getJSON( id );

        int ind2 = s.indexOf("\"username\":\"");
        if ( ind2 == -1 ) {
            System.out.println( "Invalid ID" );
            return null;
        }
        ind2 = s.indexOf(":\"", ind2 ) + 2;

        String username = s.substring( ind2, s.indexOf( "\"", ind2) );

        return username;
    }

    public String getJSON( String identifier ) {

    	Get get = new Get( ENDPOINT + "users/" + identifier, null );
    
    	return get.execute();
    }



}


