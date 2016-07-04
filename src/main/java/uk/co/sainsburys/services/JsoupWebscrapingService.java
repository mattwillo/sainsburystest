package uk.co.sainsburys.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import uk.co.sainsburys.model.Product;
import uk.co.sainsburys.locator.JsoupDataLocator;
import uk.co.sainsburys.locator.DocumentSizeLocator;
import uk.co.sainsburys.locator.JsoupProductDescriptionLocator;
import uk.co.sainsburys.locator.JsoupProductTitleLocator;
import uk.co.sainsburys.locator.JsoupProductUnitPriceLocator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.co.sainsburys.exception.DocumentParseException;

/**
 * The concept of this simple service is to allow access to various generic data types
 * based on the web page without exposing any Jsoup specific implementation to the main application.
 * 
 * This service isn't unit tested, given time the document objects returned
 * by parse function would be mocked to enable testing of main service functions 
 * 
 * @author matt
 */
public class JsoupWebscrapingService implements WebscrapingService {
    private static final String PRODUCT_CLASS = "product";
    private JsoupDataLocator<String> productTitleLocator;
    private JsoupDataLocator<String> productDescriptionLocator;
    private JsoupDataLocator<BigDecimal> productUnitPriceLocator;
    private DocumentSizeLocator documentSizeLocator;
    
    public JsoupWebscrapingService() {
        //In the interests of time saving I did not implement a DI framework
        //but usually these locator dependencies would be injected.
        productTitleLocator = new JsoupProductTitleLocator();
        productDescriptionLocator = new JsoupProductDescriptionLocator();
        productUnitPriceLocator = new JsoupProductUnitPriceLocator();
        documentSizeLocator = new DocumentSizeLocator();
    }

    /**
     * Get a list of product links based on a document
     * 
     * @param aDocumentLocation URL for the document
     * @return List of URLS
     * @throws DocumentParseException 
     */
    public List<String> getProductLinks(
            final String aDocumentLocation) throws DocumentParseException {
        List<String> productUrls = new LinkedList();
        
        try {
            Document contentDocument = parseDocumentForUrl(aDocumentLocation);
           
            Elements productElements = contentDocument.getElementsByClass(PRODUCT_CLASS);
        
            for (Element e : productElements) {
                productUrls.add(e.getElementsByTag("a").first().attr("href"));
            }
        } catch (IOException e) {
           System.out.println("Unable to fetch document from URL, skipping");
           throw new DocumentParseException(
                   "IO Exception when parsing document: " + e.getMessage());
        }
        
        return productUrls;
    }
    
    /**
     * Get product information based on a document location
     * 
     * @param aDocumentLocation The URL of a document
     * @return Product representation from the document
     * @throws DocumentParseException 
     */
    public Product getProductInformationFromUrl(
            final String aDocumentLocation) throws DocumentParseException {
        Product p = new Product();
        
        try {
            Document contentDocument = parseDocumentForUrl(aDocumentLocation);
            
            return extractProductInformationFromDocument(contentDocument,
                    aDocumentLocation);
        } catch (IOException e) {
            System.out.println("Unable to fetch document from URL, exiting");
            throw new DocumentParseException(
                    "IO exception when parsing document: " + e.getMessage());
        }
    }
    
    /**
     * Parse a document for a given URL
     * 
     * @param aUrl The URL for the document
     * @return The Jsoup Document representation
     * @throws IOException 
     */
    private Document parseDocumentForUrl(final String aUrl) throws IOException {
        return Jsoup.connect(aUrl).get();
    }
    
    //Had to add aDocumentLocation for the interests of the time limit, I would normally
    //use aDocument.Location prodived by Jsoup Document but it was causing unknown
    //compile time errors.
    private Product extractProductInformationFromDocument(final Document aDocument,
            final String aDocumentLocation) {
        Product p = new Product();
        
        p.setTitle(productTitleLocator.locate(aDocument));
        p.setSize(documentSizeLocator.locate(aDocumentLocation));
        p.setDescription(productDescriptionLocator.locate(aDocument));
        p.setUnitPrice(productUnitPriceLocator.locate(aDocument));
        
        return p;
    }
    
    /**
     * Set title locator
     * @param aLocator the locator
     */
    public void setProductTitleLocator(final JsoupDataLocator<String> aLocator) {
        this.productTitleLocator = aLocator;
    }
    
    /**
     * Set document size locator
     * @param aLocator the locator
     */
    public void setDocumentSizeLocator(final DocumentSizeLocator aLocator) {
        this.documentSizeLocator = aLocator;
    }
    
    /**
     * Set product description locator
     * 
     * @param aLocator the locator
     */
    public void setProductDescriptionLocator(final JsoupDataLocator<String> aLocator) {
        this.productDescriptionLocator = aLocator;
    }

    /**
     * Set product unit price locator
     * 
     * @param aLocator the locator 
     */
    public void setProductUnitPriceLocator(final JsoupDataLocator<BigDecimal> aLocator) {
        this.productUnitPriceLocator = aLocator;
    }
}
