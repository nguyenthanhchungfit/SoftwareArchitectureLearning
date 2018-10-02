/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timkiem;

/**
 *
 * @author chungnt
 *
 **/
public class MaximumLevel implements IValLevel{

    @Override
    public boolean isSwapped(int num1, int num2) {
        return num2 >= num1;
    }
    
}
