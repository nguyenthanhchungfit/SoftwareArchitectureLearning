/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author chungnt
 */
public class SProduct extends SObject {

    protected String productId;
    protected String productName;
    protected Float price;

    @Override
    public boolean setAttribute(String attribute, String newValue) {
        switch (attribute) {
            case "productId":
            case "ProductID":
            case "ProductId":
                productId = newValue;
                return true;
            case "productName":
            case "ProductName":
                productName = newValue;
                return true;
            case "price":
            case "Price":
                price = Float.parseFloat(newValue);
                return true;
        }
        return false;
    }

    @Override
    public String getAttribute(String attribute) {
        switch (attribute) {
            case "productId":
            case "ProductID":
            case "ProductId":
                return productId;
            case "productName":
            case "ProductName":
                return productName;
            case "price":
            case "Price":
                return price.toString();
            default:
                return "";
        }
    }

}
