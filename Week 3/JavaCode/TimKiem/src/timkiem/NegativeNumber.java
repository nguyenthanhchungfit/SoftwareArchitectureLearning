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
public class NegativeNumber implements INumber {

    @Override
    public boolean isNumber(int number) {
        return number < 0;
    }  
}
