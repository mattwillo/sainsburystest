package uk.co.sainsburys.model;

import java.math.BigDecimal;

/**
 * Model to represent the information associated with a product scraped from the page.
 * This class has not been unit tested as it only exposes basic access functionality.
 * 
 * @author matt
 */
public class Product {
    private String title;
    private String size;
    private BigDecimal unitPrice;
    private String description;
    
    /**
     * Set title.
     * @param aTitle Title 
     */
    public void setTitle(final String aTitle) {
        this.title = aTitle;
    }
    
    /**
     * Get title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Set the page size.
     * @param aSize A string representing the size in KB
     */
    public void setSize(final String aSize) {
        this.size = aSize;
    }
    
    /**
     * Get page size.
     * @return String representation of the size in KB
     */
    public String getSize() {
        return size;
    }
    
    /**
     * Set the product unit price.
     * @param aUnitPrice 
     */
    public void setUnitPrice(final BigDecimal aUnitPrice) {
        this.unitPrice = aUnitPrice;
    }
    
    /**
     * Get the unit price.
     * @return The unit price
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    
    /**
     * Set the product description.
     * @param aDescription The description
     */
    public void setDescription(final String aDescription) {
        this.description = aDescription;
    }
    
    /**
     * Get the product description.
     * @return The description
     */
    public String getDescription() {
        return description;
    }
}