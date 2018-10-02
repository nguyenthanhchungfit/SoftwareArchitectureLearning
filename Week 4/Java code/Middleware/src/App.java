
import client.CProduct;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chungnt
 */
public class App {
    public static void main(String[] args) {
        CProduct pro = new CProduct("01", "cookie", 20000);
        System.out.println(pro.getProductId());
        System.out.println(pro.getProductName());
        System.out.println(pro.getPrice());
    }
}
