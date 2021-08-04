/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.dto;

import java.math.BigInteger;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineItem {

    private int itemId;
    private int itemStock;
    private BigInteger itemPrice;
    private String itemName;
    
    public VendingMachineItem(int itemId, int itemStock, BigInteger itemPrice,
            String itemName){
        this.itemId = itemId;
        this.itemStock = itemStock;
        this.itemPrice = itemPrice;
        this.itemName = itemName;
    }
    
    public int getItemId(){
        return itemId;
    }
    
    public int getItemStock(){
        return itemStock;
    }
    
    public void setItemStock(int itemStock){
        this.itemStock = itemStock;
    }
    
    public BigInteger getItemPrice(){
        return itemPrice;
    }
    
    public void setItemPrice(BigInteger itemPrice){
        this.itemPrice = itemPrice;
    }
    
    public String getItemName(){
        return itemName;
    }
    
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    
}
