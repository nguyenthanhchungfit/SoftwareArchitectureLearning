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
public class Searching {

    private INumber numberType;
    private IValLevel valLevel;
    private boolean isLastPosition;

    public Searching() {
        numberType = new DefaultNumber();
        valLevel = new MaximumLevel();
        isLastPosition = true;
    }

    public Searching(INumber numberType, IValLevel valLevel, boolean isLastPosition) {
        this.numberType = numberType;
        this.valLevel = valLevel;
        this.isLastPosition = isLastPosition;
    }

    public void setNumberType(INumber numberType) {
        this.numberType = numberType;
    }

    public void setValLevel(IValLevel valLevel) {
        this.valLevel = valLevel;
    }

    public void setIsLastPosition(boolean isLastPosition) {
        this.isLastPosition = isLastPosition;
    }

    public int search(int[] arr) {
        int pos = -1;
        if (arr != null && arr.length > 0) {
            int flagNumber = arr[0];
            if (numberType.isNumber(flagNumber)) {
                pos = 0;
            }
            for (int i = 1; i < arr.length; i++) {
                if (numberType.isNumber(arr[i])) {
                    if (pos < 0) {
                        pos = i;
                        flagNumber = arr[pos];
                    } else {
                        if (valLevel.isSwapped(flagNumber, arr[i])) {
                            if (flagNumber == arr[i]) {
                                if (isLastPosition) {
                                    pos = i;
                                    flagNumber = arr[i];
                                }
                            }else{
                                pos = i;
                                flagNumber = arr[i];
                            }
                        }
                    }
                }
            }
        }
        return pos;
    }
}
