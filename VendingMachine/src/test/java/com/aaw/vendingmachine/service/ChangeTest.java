/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaw.vendingmachine.service;

import com.aaw.vendingmachine.dto.Change;
import com.aaw.vendingmachine.dto.NegativeChangeException;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Austin Wong
 */
public class ChangeTest {

    @Test
    public void test0Cents() throws NegativeChangeException {
        String amountOfMoneyStr = "0.00";
        BigDecimal amountOfMoney = new BigDecimal(amountOfMoneyStr);
        
        Change change = new Change(amountOfMoney);
        
        assertNotNull(change, "Change object should not be null.");
        
        int expectedQuarters = 0;
        int expectedDimes = 0;
        int expectedNickels = 0;
        int expectedPennies = 0;
        assertEquals(expectedQuarters, change.getQuarters(), amountOfMoneyStr + " cents should have 0 quarters.");
        assertEquals(expectedDimes, change.getDimes(), amountOfMoneyStr + " cents should have 0 dimes.");
        assertEquals(expectedNickels, change.getNickels(), amountOfMoneyStr + " cents should have 0 nickels.");
        assertEquals(expectedPennies, change.getPennies(), amountOfMoneyStr + " cents should have 0 pennies.");
    }
    
    @Test
    public void testNegative100Cents() {
        String amountOfMoneyStr = "-1.00";
        BigDecimal amountOfMoney = new BigDecimal(amountOfMoneyStr);
        
        assertThrows(NegativeChangeException.class, 
                () -> new Change(amountOfMoney), 
                amountOfMoneyStr + " of money should throw NegativeChangeException.");
    }
    
    @Test
    public void test1Cent() throws NegativeChangeException {
        String amountOfMoneyStr = "0.01";
        BigDecimal amountOfMoney = new BigDecimal(amountOfMoneyStr);
        
        Change change = new Change(amountOfMoney);
        
        assertNotNull(change, "Change object should not be null.");
        
        int expectedQuarters = 0;
        int expectedDimes = 0;
        int expectedNickels = 0;
        int expectedPennies = 1;
        assertEquals(expectedQuarters, change.getQuarters(), amountOfMoneyStr + " cents should have 0 quarters.");
        assertEquals(expectedDimes, change.getDimes(), amountOfMoneyStr + " cents should have 0 dimes.");
        assertEquals(expectedNickels, change.getNickels(), amountOfMoneyStr + " cents should have 0 nickels.");
        assertEquals(expectedPennies, change.getPennies(), amountOfMoneyStr + " cents should have 1 pennies.");
    }
    
    @Test
    public void test5Cents() throws NegativeChangeException {
        String amountOfMoneyStr = "0.05";
        BigDecimal amountOfMoney = new BigDecimal(amountOfMoneyStr);
        
        Change change = new Change(amountOfMoney);
        
        assertNotNull(change, "Change object should not be null.");
        
        int expectedQuarters = 0;
        int expectedDimes = 0;
        int expectedNickels = 1;
        int expectedPennies = 0;
        assertEquals(expectedQuarters, change.getQuarters(), amountOfMoneyStr + " cents should have 0 quarters.");
        assertEquals(expectedDimes, change.getDimes(), amountOfMoneyStr + " cents should have 0 dimes.");
        assertEquals(expectedNickels, change.getNickels(), amountOfMoneyStr + " cents should have 1 nickel.");
        assertEquals(expectedPennies, change.getPennies(), amountOfMoneyStr + " cents should have 0 pennies.");
    }
    
    @Test
    public void test10Cents() throws NegativeChangeException {
        String amountOfMoneyStr = "0.10";
        BigDecimal amountOfMoney = new BigDecimal(amountOfMoneyStr);
        
        Change change = new Change(amountOfMoney);
        
        assertNotNull(change, "Change object should not be null.");
        
        int expectedQuarters = 0;
        int expectedDimes = 1;
        int expectedNickels = 0;
        int expectedPennies = 0;
        assertEquals(expectedQuarters, change.getQuarters(), amountOfMoneyStr + " cents should have 0 quarters.");
        assertEquals(expectedDimes, change.getDimes(), amountOfMoneyStr + " cents should have 1 dime.");
        assertEquals(expectedNickels, change.getNickels(), amountOfMoneyStr + " cents should have 0 nickels.");
        assertEquals(expectedPennies, change.getPennies(), amountOfMoneyStr + " cents should have 0 pennies.");
    }
    
    @Test
    public void test25Cents() throws NegativeChangeException {
        String amountOfMoneyStr = "0.25";
        BigDecimal amountOfMoney = new BigDecimal(amountOfMoneyStr);
        
        Change change = new Change(amountOfMoney);
        
        assertNotNull(change, "Change object should not be null.");
        
        int expectedQuarters = 1;
        int expectedDimes = 0;
        int expectedNickels = 0;
        int expectedPennies = 0;
        assertEquals(expectedQuarters, change.getQuarters(), amountOfMoneyStr + " cents should have 1 quarter.");
        assertEquals(expectedDimes, change.getDimes(), amountOfMoneyStr + " cents should have 0 dimes.");
        assertEquals(expectedNickels, change.getNickels(), amountOfMoneyStr + " cents should have 0 nickels.");
        assertEquals(expectedPennies, change.getPennies(), amountOfMoneyStr + " cents should have 0 pennies.");
    }   
    
    @Test
    public void test369Cents() throws NegativeChangeException {
        String amountOfMoneyStr = "3.69";
        BigDecimal amountOfMoney = new BigDecimal(amountOfMoneyStr);
        
        Change change = new Change(amountOfMoney);
        
        assertNotNull(change, "Change object should not be null.");
        
        int expectedQuarters = 14;
        int expectedDimes = 1;
        int expectedNickels = 1;
        int expectedPennies = 4;
        assertEquals(expectedQuarters, change.getQuarters(), amountOfMoneyStr + " cents should have 14 quarters.");
        assertEquals(expectedDimes, change.getDimes(), amountOfMoneyStr + " cents should have 1 dime.");
        assertEquals(expectedNickels, change.getNickels(), amountOfMoneyStr + " cents should have 1 nickel.");
        assertEquals(expectedPennies, change.getPennies(), amountOfMoneyStr + " cents should have 4 pennies.");
    }
}
