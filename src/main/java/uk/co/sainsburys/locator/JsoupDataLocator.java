package uk.co.sainsburys.locator;

import org.jsoup.nodes.Document;

/**
 * Contract for Jsoup Data Locator classes
 * @author matt
 */
public interface JsoupDataLocator<T> {
    /**
     * Locate T based on a document.
     * @param aDocument the Jsoup document
     * @return T representation of the located content
     */
    public T locate(final Document aDocument);
}
