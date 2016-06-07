package Message.MessageRelated;

import com.google.gson.JsonObject;

import RESTCalls.Post;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
//import org.imgscalr.Scalr;
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;

import java.io.File;


/**
 * Created by servicedog on 8/31/15.
 */
public class MediaUploader {

    private String ENDPOINT = null;

    public MediaUploader(String end) {

        ENDPOINT = end;

    }

    public String uploadFromLocal( File file, String authC) {

        //File verifile = reduceSize( file );

        File verifile = file;

        if ( verifile == null ) {
            System.out.println( "Error uploading file." );
            return null;
        }


        String contype = "image/";
        if ( verifile.getName().contains(".jpg") ) {
            contype = contype + "jpg";
        } else if ( verifile.getName().contains(".png") ) {
            contype = contype + "png";
        } else if ( verifile.getName().contains(".gif") ) {
            contype = contype + "gif";
        } else {
            System.out.println( "Unsupported file format. (Supported topicplaces formats: JPG, PNG, GIF)" );
            return null;
        }
        
        Post post = new Post( ENDPOINT + "media", authC );
      
        try {
        	FileBody fBody = new FileBody(file, contype);
        	StringBody stringBody = new StringBody("Filename: " +
                    file.getName());
        	MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart("file", fBody);
            reqEntity.addPart("filename", stringBody);
            
            post.setMultipartEntity( reqEntity );
        } catch ( Throwable t ) {
        	t.printStackTrace();
        	System.out.println( "Could not attach Multipart Form." );
        }
        
        String response = post.execute();
        
        return getMediaID( response );
    }

    public String uploadFromURL( String URL, String authC) {

    	Post post = new Post( ENDPOINT + "media", authC );
    	
        JsonObject json = new JsonObject();
        json.addProperty("image_url", URL);

        post.addJson( json );
        
        String response = post.execute();
        
        return getMediaID( response );
    }
    
    private String getMediaID( String response ) {

	    int index = response.indexOf("{\"val\":\"m-");
	    
	    if ( index == -1 ) {
	    	
	    	index = response.indexOf( "{\"file\":\"m-" );
	    	
	    	if ( index == -1 ) return null;
	    }
	    
	    index = response.indexOf( "m-", index );
	    
	    return response.substring( 
	    		index, 
	    		response.indexOf( "\"", index ) 
	    		);
    }
/*
    private File reduceSize( File filename ) {

        try {
            BufferedImage bimg = ImageIO.read( filename );

            double width = bimg.getWidth();
            double height = bimg.getHeight();

            double ratio = width/height;

            if ( width*height > 1000000 ) {

                BufferedImage scaledImg = Scalr.resize( bimg, 1000, (int) (1000/ratio) );
                File outputfile = new File("resized.png");
                ImageIO.write( scaledImg, "png", outputfile );

                return outputfile;

            } else {
                return filename;
            }

        } catch ( Throwable t ) {
            System.out.println( "Error reducing image size." );
            return null;
        }
    }
    */
}
