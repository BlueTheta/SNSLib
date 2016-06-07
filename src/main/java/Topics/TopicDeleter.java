package Topics;

import RESTCalls.Delete;

/**
 * Created by servicedog on 7/8/15.
 */
public class TopicDeleter {

    private String ENDPOINT;

    public TopicDeleter(String end)
    {
        ENDPOINT = end;
    }

    public void execute( String tid, Boolean isPrivate, String authkey )
    {

    	Delete delete = null;   	
        if ( isPrivate ) {
            delete = new Delete( ENDPOINT + "groups/" + tid, authkey );
        } else {
            delete = new Delete(ENDPOINT + "topics/" + tid, authkey );
        }

        delete.execute();
    }

}
