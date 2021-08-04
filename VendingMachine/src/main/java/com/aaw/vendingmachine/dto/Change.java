/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Austin Wong
 */
public class Change {

    private final int quarters;
    private final int dimes;
    private final int nickels;
    private final int pennies;
    
    public Change(BigDecimal totalInPennies) throws NegativeChangeException {
        
        if (totalInPennies.compareTo(new BigDecimal("0")) < 0){
            throw new NegativeChangeException(
                    "Cannot create change for a negative number.");
        }
        
        quarters = getNumCoinsAsInt(totalInPennies, Coins.QUARTER);
        totalInPennies = getRemainingPennies(totalInPennies, Coins.QUARTER);

        dimes = getNumCoinsAsInt(totalInPennies, Coins.DIME);
        totalInPennies = getRemainingPennies(totalInPennies, Coins.DIME);

        nickels = getNumCoinsAsInt(totalInPennies, Coins.NICKEL);
        totalInPennies = getRemainingPennies(totalInPennies, Coins.NICKEL);

        pennies = getNumCoinsAsInt(totalInPennies, Coins.PENNY);

    }
    
    private int getNumCoinsAsInt(BigDecimal totalAmount, Coins coin){
        return totalAmount.divide(coin.getCents(), 0, RoundingMode.DOWN).intValue();
    }
    
    private BigDecimal getRemainingPennies(BigDecimal totalAmount, Coins coin){
        return totalAmount.remainder(coin.getCents());
    }
    
    public int getQuarters(){
        return this.quarters;
    }
    
    public int getDimes(){
        return this.dimes;
    }
    
    public int getNickels(){
        return this.nickels;
    }
    
    public int getPennies(){
        return this.pennies;
    }
    
}
