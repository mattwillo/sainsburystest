/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.sainsburys;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author matt
 */
public class TestUtils {
    public static Document createTestDocumentFromFile(final String fileName) throws IOException {
        File input = new File(fileName);
        
        Document doc = Jsoup.parse(input, "UTF-8", "www.sainsburys.co.uk");
        
        return doc;
    }
}
