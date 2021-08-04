/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaw.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Austin Wong
 */
public enum Coins {
    
    QUARTER(new BigDecimal(".25")),
    DIME(new BigDecimal(".10")),
    NICKEL(new BigDecimal(".05")),
    PENNY(new BigDecimal(".01"));
    
    private final BigDecimal cents;
    
    private Coins(BigDecimal cents){
        this.cents = cents;
    }
    
    public BigDecimal getCents(){
        return this.cents;
    }
}
