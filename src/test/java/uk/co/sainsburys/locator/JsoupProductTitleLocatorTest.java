package uk.co.sainsburys.locator;

import java.io.IOException;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.jsoup.nodes.Document;
import uk.co.sainsburys.TestUtils;

/**
 * Unit test for the product title locator.
 */
public class JsoupProductTitleLocatorTest extends TestCase
{
    private JsoupProductTitleLocator subject;
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public JsoupProductTitleLocatorTest(String testName)
    {
        super(testName);
        
        subject = new JsoupProductTitleLocator();
    }
    
    /**
     * Test a valid product title
     */
    public void testValidProductTitle()
    {
        try {
            Document d = TestUtils.createTestDocumentFromFile(
                    "src/test/resources/uk/co/sainsburys/locator/validProductPage.html");
            
            String located = subject.locate(d);
            
            Assert.assertEquals("Sainsbury's Avocado, Ripe & Ready x2", located);
        } catch (IOException e) {
            Assert.fail("Unable to open file:" + e.getMessage());
        }
    }
}
