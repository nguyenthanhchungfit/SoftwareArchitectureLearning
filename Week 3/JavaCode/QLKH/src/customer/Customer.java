/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

/**
 *
 * @author chungnt
 */
public class Customer {
    private static CustomerState[] allStates = new CustomerState[]{
        new NormalCustomerState(), new VipCustomerState()
    };
    private CustomerState curState = null;
    private float totalMoney;
    public Customer(){
        curState = allStates[0];
    }
    
    public float purchase(float price){
        totalMoney += price;
        float money = price;
        if(curState instanceof VipCustomerState){
            money *= 0.05;
        }
        changeState();
        return money;
    }
    
    private void changeState(){
        if(totalMoney >= 10000000){
            curState = allStates[1];
        }else{
            curState = allStates[0];
        }
    }
    
    public void reset(){
        totalMoney = 0;
        changeState();
    }
    
    public boolean isReceivedBirthdayGift(){
        return (curState instanceof VipCustomerState);
    }
    
    public boolean isFreeEating(){
        return (curState instanceof VipCustomerState);
    }
}
