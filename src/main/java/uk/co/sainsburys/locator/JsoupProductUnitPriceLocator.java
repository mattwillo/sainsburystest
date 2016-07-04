package uk.co.sainsburys.locator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Jsoup unit price locator, uses simple dom traversing to locate the text
 * representation of unit price.
 * 
 * @author matt
 */
public class JsoupProductUnitPriceLocator implements JsoupDataLocator<BigDecimal>{
    public BigDecimal locate(final Document aDocument) {
        Element productUnitPriceElement = aDocument.getElementsByClass("pricePerUnit").first();
        
        // I hate this, but was running out of time so decided to match a formatted
        // number within the specific element node
        Pattern p = Pattern.compile("\\d+\\.\\d+");
        Matcher m = p.matcher(productUnitPriceElement.text());
        
        if (m.find()) {
            return new BigDecimal(m.group(0));
        } else {
            // This is a decision made in the interests of simplicity, it could
            // be argued that a system like this should fail if it can't find
            // a price. At the very least failure would be logged.
            System.out.println("Unable to locate price: setting to zero");
            return new BigDecimal(0.00);
        }
    }
}
