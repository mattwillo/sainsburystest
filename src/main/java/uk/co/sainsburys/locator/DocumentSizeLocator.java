package uk.co.sainsburys.locator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Document size locator, implements a simple method to find the contentLength
 * of a document, without accessing any assets to establish their size.
 * 
 * @author matt
 */
public class DocumentSizeLocator {
    /**
     * Locate URL documents size
     * @param aUrl The URL for the document
     * @return A string representation of the size in KB
     */
    public String locate(final String aUrl) {
        int contentLength = 0;
        Double sizeInK = 0.0;
        try {
            HttpURLConnection content = (HttpURLConnection) new URL(aUrl).openConnection();
            contentLength = content.getContentLength();            
        } catch (MalformedURLException mue) {
            System.out.println("Unable to find size of document on url: " +
                    aUrl + " - unknown protocol, setting price to 0");
        } catch (IOException ie) {
            //Decision made to continue processing with a content length of 0,
            //log the event.
            System.out.println("Unable to read price on URL: " +
                    aUrl + " - IO Error, setting price to 0");
        } 
        
        if (contentLength > 0) {
            sizeInK = new Double(contentLength / 1024);
        }
        
        return sizeInK.toString() + "kb";
    }
}
