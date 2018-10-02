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
public class PrimeNumber implements INumber{
    @Override
    public boolean isNumber(int number) {
        if(number < 2)
            return false;
        if(number == 2)
            return true;
        for(int i = 2; i <= Math.sqrt(number); i++){
            if(number % i == 0)
                return false;
        }
        return true;
    }
}
