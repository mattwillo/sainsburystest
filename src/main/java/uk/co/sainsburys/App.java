package uk.co.sainsburys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import uk.co.sainsburys.exception.DocumentParseException;
import uk.co.sainsburys.services.JsoupWebscrapingService;
import uk.co.sainsburys.services.GsonObjectEncodingService;
import uk.co.sainsburys.model.Product;

/**
 * Basic app, given more time I would implement a set of options to control
 * logging, document URL location.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Map<String,List<Product>> results = new HashMap();
        
        //Given time I would have allowed this to be passed as params to the commandline
        String url = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
        //These would usually be injected, decided in the interests of simplicity to not
        //implement a DI framework.
        JsoupWebscrapingService delegate = new JsoupWebscrapingService();
        GsonObjectEncodingService encoder = new GsonObjectEncodingService();
        
        BigDecimal total = new BigDecimal("0.00");
        
        List<String> urls = new LinkedList();
        
        try {
            urls = delegate.getProductLinks(url);
        } catch (DocumentParseException e) {
            //total failure, exit
            System.out.println("Unable to get product list: " + e.getMessage());
        }
        
        results.put("results", new ArrayList());
        
        for (String u : urls) {
            try {
                Product p = delegate.getProductInformationFromUrl(u);
            
                results.get("results").add(p); 
                
                total = total.add(p.getUnitPrice());
            } catch (DocumentParseException e) {
                //this isn't reason to exit, we can simply skip the product
                continue;
            }  
        }
        
        Map returnResults = new HashMap();
        returnResults.putAll(results);
        returnResults.put("total", encoder.encode(total));
        
        System.out.println(encoder.encode(returnResults));
    }
}
