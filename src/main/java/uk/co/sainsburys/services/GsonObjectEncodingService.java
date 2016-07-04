package uk.co.sainsburys.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Simple service to implement Gson to allow for easy encoding of objects.
 * 
 * This service isn't tested as it is only a simple wrapper for Gson
 * 
 * @author matt
 */
public class GsonObjectEncodingService {
    private Gson delegate;

    public GsonObjectEncodingService() {
        GsonBuilder builder = new GsonBuilder();
        builder.disableHtmlEscaping();
        
        delegate = builder.create();
        
    }
    
    /**
     * Json encode an object.
     * 
     * @param subject The Object
     * @return Json string representation of the object.
     */
    public String encode(final Object subject) {
        return delegate.toJson(subject);
    }
}
