package Message.MessageRelated;

import Message.MessageRetriever;
import Topics.TopicRetriever;

public class MediaRetriever {

	private String ENDPOINT;

	public MediaRetriever(String end)
	{
		ENDPOINT = end;
	}

	public String getGameMedia(String GID, String authkey) {

		MessageRetriever gret = new MessageRetriever( ENDPOINT );
		return gret.getMediaFromJSON(gret.getMessageJSON(GID, false, authkey));

	}

	public String getPrivateGameMedia( String GID, String authkey ) {

		MessageRetriever gret = new MessageRetriever( ENDPOINT );
		return gret.getMediaFromJSON(gret.getMessageJSON(GID, true, authkey));

	}

	public String getTopicMedia( String TID, Boolean priv, String authkey ) {

		TopicRetriever tRet = new TopicRetriever( ENDPOINT );
		return tRet.getMediaFromJSON( tRet.getTopicInfoJSON( TID, priv, authkey ) );
	}
}
