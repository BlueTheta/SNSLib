package Users;

import RESTCalls.Delete;

/**
 * Created by servicedog on 7/8/15.
 */
public class SubscriptionDeleter {

    private String ENDPOINT;

    public SubscriptionDeleter(String end)
    {
        ENDPOINT = end;
    }

    public void execute( String subID, String authkey )
    {
    	
    	Delete delete = new Delete( ENDPOINT + "subscriptions/" + subID, authkey );
        delete.execute();
    }

}
