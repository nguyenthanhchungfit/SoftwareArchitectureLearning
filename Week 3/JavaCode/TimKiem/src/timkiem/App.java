/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timkiem;

/**
 *
 * @author chungnt
 */
public class App {
    public static void main(String[] args) {
        int[] arr = {10, -4, -2, 5, 7, 120, -24, 12, 31, 10};
        Searching searchA = new Searching(new PrimeNumber(), new MaximumLevel(), true);
        int posA = searchA.search(arr);
        if(posA >= 0){
          System.out.println(String.format("Vị trí cuối cùng của SNT lớn nhất trong mảng: arr[%d] = %d", posA, arr[posA]));  
        }else{
          System.out.println(String.format("Vị trí cuối cùng của SNT lớn nhất trong mảng: %d", posA));  
        }
        
        
        Searching searchB = new Searching(new PrimeNumber(), new MinimumLevel(), true);
        int posB = searchB.search(arr);
        if(posB >= 0){
          System.out.println(String.format("Vị trí cuối cùng của SNT nhỏ nhất trong mảng: arr[%d] = %d", posB, arr[posB]));  
        }else{
          System.out.println(String.format("Vị trí cuối cùng của SNT nhỏ nhất trong mảng: %d", posB));  
        }
        
        Searching searchC = new Searching(new NegativeNumber(), new MaximumLevel(), false);
        int posC = searchC.search(arr);
        if(posC >= 0){
          System.out.println(String.format("Vị trí đầu tiên của âm lớn nhất trong mảng: arr[%d] = %d", posC, arr[posC]));  
        }else{
          System.out.println(String.format("Vị trí đầu tiên của âm lớn nhất trong mảng: %d", posC));  
        }
    }
}
