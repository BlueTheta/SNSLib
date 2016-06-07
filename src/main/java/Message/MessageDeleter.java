package Message;


import RESTCalls.Delete;

/**
 * Created by servicedog on 7/8/15.
 */
public class MessageDeleter {

    private String ENDPOINT;

    public MessageDeleter(String end)
    {
        ENDPOINT = end;
    }

    public void execute( String gid, String authkey )
    {    	
    	Delete delete = new Delete( ENDPOINT + "games/" + gid, authkey );
    	
    	delete.execute();
    }

}
