package uk.co.sainsburys.locator;

import java.io.IOException;
import java.math.BigDecimal;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.jsoup.nodes.Document;
import uk.co.sainsburys.TestUtils;

/**
 * Unit test for the product unit price locator.
 */
public class JsoupProductUnitPriceLocatorTest extends TestCase
{
    private JsoupProductUnitPriceLocator subject;
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public JsoupProductUnitPriceLocatorTest(String testName)
    {
        super(testName);
        
        subject = new JsoupProductUnitPriceLocator();
    }
    
    /**
     * Test a valid unit price
     */
    public void testValidProductUnitPrice()
    {
        try {
            Document d = TestUtils.createTestDocumentFromFile(
                    "src/test/resources/uk/co/sainsburys/locator/validProductPage.html");
            
            BigDecimal located = subject.locate(d);
            
            Assert.assertEquals("1.80", located.toString());
        } catch (IOException e) {
            Assert.fail("Unable to open file:" + e.getMessage());
        }
    }
}
