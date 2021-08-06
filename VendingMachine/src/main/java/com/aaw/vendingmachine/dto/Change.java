/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 *
 * @author Austin Wong
 */
public class Change {

    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;
    private final BigDecimal totalInDollars;
    
    public Change(BigDecimal totalInDollars) {
        this.totalInDollars = totalInDollars;
        CalculateChange();
    }
    
    private void CalculateChange(){
        
        BigDecimal remainingAmount = this.totalInDollars;
        
        quarters = getNumCoinsAsInt(remainingAmount, Coins.QUARTER);
        remainingAmount = getRemainingAmount(remainingAmount, Coins.QUARTER);

        dimes = getNumCoinsAsInt(remainingAmount, Coins.DIME);
        remainingAmount = getRemainingAmount(remainingAmount, Coins.DIME);

        nickels = getNumCoinsAsInt(remainingAmount, Coins.NICKEL);
        remainingAmount = getRemainingAmount(remainingAmount, Coins.NICKEL);

        pennies = getNumCoinsAsInt(remainingAmount, Coins.PENNY);
    }
    
    public BigDecimal getTotalInDollars(){
        return this.totalInDollars;
    }
    
    private int getNumCoinsAsInt(BigDecimal totalAmount, Coins coin){
        return totalAmount.divide(coin.getCents(), 0, RoundingMode.DOWN).intValue();
    }
    
    private BigDecimal getRemainingAmount(BigDecimal totalAmount, Coins coin){
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.quarters;
        hash = 13 * hash + this.dimes;
        hash = 13 * hash + this.nickels;
        hash = 13 * hash + this.pennies;
        hash = 13 * hash + Objects.hashCode(this.totalInDollars);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Change other = (Change) obj;
        if (this.quarters != other.quarters) {
            return false;
        }
        if (this.dimes != other.dimes) {
            return false;
        }
        if (this.nickels != other.nickels) {
            return false;
        }
        if (this.pennies != other.pennies) {
            return false;
        }
        if (!Objects.equals(this.totalInDollars, other.totalInDollars)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        String changeStr = "Quarters: " + quarters;
        changeStr += "\nDimes: " + dimes;
        changeStr += "\nNickels: " + nickels;
        changeStr += "\nPennies: " + pennies;
        changeStr += "\nTotal: " + totalInDollars;
        return changeStr;
    }
    
}
