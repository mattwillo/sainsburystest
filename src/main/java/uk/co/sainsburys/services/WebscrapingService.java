/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.sainsburys.services;

import java.util.List;
import uk.co.sainsburys.exception.DocumentParseException;
import uk.co.sainsburys.model.Product;

/**
 *
 * @author matt
 */
public interface WebscrapingService {
    public List<String> getProductLinks(
            final String aDocumentLocation) throws DocumentParseException;
    
    public Product getProductInformationFromUrl(
            final String aDocumentLocation) throws DocumentParseException;
}
