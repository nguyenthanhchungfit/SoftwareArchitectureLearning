/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author chungnt
 */
public class CProduct extends CObject{
    public CProduct(String productId, String productName, float price){
        this.handle = ClientObjectManager.createObject("CProduct");
        this.setProductName(productName);
        this.setProductId(productId);
        this.setPrice(price);
    }
    
    private float price;
    private String productId;
    private String productName;

    public float getPrice() {
        return Float.parseFloat(ClientObjectManager.getObjectAttribute(handle, "price"));
    }

    public void setPrice(Float price) {
        ClientObjectManager.setObjectAttribute(handle, "price", price.toString());
    }

    public String getProductId() {
        return ClientObjectManager.getObjectAttribute(handle, "ProductId");
    }

    public void setProductId(String productId) {
        ClientObjectManager.setObjectAttribute(handle, "ProductId", productId);
    }

    public String getProductName() {
        return ClientObjectManager.getObjectAttribute(handle, "ProductName");
    }

    public void setProductName(String productName) {
        ClientObjectManager.setObjectAttribute(handle, "ProductName", productName);
    }
    
    
}
