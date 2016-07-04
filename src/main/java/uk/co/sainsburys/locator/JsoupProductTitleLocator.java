package uk.co.sainsburys.locator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Jsoup product title locator, uses basic dom traversing to locate the text element
 * for the product title.
 * 
 * @author matt
 */
public class JsoupProductTitleLocator implements JsoupDataLocator<String> {
    /**
     * Locate the product title based on a document
     * @param aDocument The document
     * @return The product title
     */
    public String locate(final Document aDocument) {
        Element productTitleElement = aDocument.getElementsByClass("productTitleDescriptionContainer").first();
        
        return productTitleElement.getElementsByTag("h1").first().text();
    }
}
