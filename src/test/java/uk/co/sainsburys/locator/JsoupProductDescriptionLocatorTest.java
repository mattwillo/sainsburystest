package uk.co.sainsburys.locator;

import java.io.IOException;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.jsoup.nodes.Document;
import uk.co.sainsburys.TestUtils;

/**
 * Unit test for the product description locator.
 */
public class JsoupProductDescriptionLocatorTest extends TestCase
{
    private JsoupProductDescriptionLocator subject;
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public JsoupProductDescriptionLocatorTest(String testName)
    {
        super(testName);
        
        subject = new JsoupProductDescriptionLocator();
    }
    
    /**
     * Test a valid description 
     */
    public void testValidProductDescription()
    {
        try {
            Document d = TestUtils.createTestDocumentFromFile(
                    "src/test/resources/uk/co/sainsburys/locator/validProductPage.html");
            
            String located = subject.locate(d);
            
            Assert.assertEquals("Avocados", located);
        } catch (IOException e) {
            Assert.fail("Unable to open file:" + e.getMessage());
        }
    }
}
