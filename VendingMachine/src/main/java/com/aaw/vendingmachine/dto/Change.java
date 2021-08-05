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
    private BigDecimal totalInDollars;
    
    public Change(BigDecimal totalInDollars) throws NegativeChangeException {
        setTotalInDollars(totalInDollars);
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
    
    public final void setTotalInDollars(BigDecimal totalInDollars) 
            throws NegativeChangeException{
        if (totalInDollars.compareTo(new BigDecimal("0")) < 0){
            throw new NegativeChangeException(
                    "Cannot create change for a negative number.");
        }
        this.totalInDollars = totalInDollars;
        CalculateChange();
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
        hash = 23 * hash + this.quarters;
        hash = 23 * hash + this.dimes;
        hash = 23 * hash + this.nickels;
        hash = 23 * hash + this.pennies;
        hash = 23 * hash + Objects.hashCode(this.totalInDollars);
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
        return "Change{" + "quarters=" + quarters + ", dimes=" + dimes + ", nickels=" + nickels + ", pennies=" + pennies + ", totalInDollars=" + totalInDollars + '}';
    }
    
}
