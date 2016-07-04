package uk.co.sainsburys.locator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Product description locator, uses basic dom traversing to locate the text element
 * for the product description.
 * 
 * @author matt
 */
public class JsoupProductDescriptionLocator implements JsoupDataLocator<String> {
    /**
     * Locate the product description of a give URL document.
     * 
     * @param aDocument The Jsoup document
     * @return The product description
     */
    public String locate(final Document aDocument) {
        Element productDescriptionElement = aDocument.getElementsByClass("productText").first();
        
        return productDescriptionElement.getElementsByTag("p").first().text();
    }
}
