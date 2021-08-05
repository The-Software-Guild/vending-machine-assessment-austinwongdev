/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineItem {

    private int itemId;
    private int itemStock;
    private BigDecimal itemPrice;
    private String itemName;
    
    public VendingMachineItem(int itemId, String itemName, BigDecimal itemPrice,
            int itemStock){
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
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
    
    public BigDecimal getItemPrice(){
        return itemPrice;
    }
    
    public void setItemPrice(BigDecimal itemPrice){
        this.itemPrice = itemPrice;
    }
    
    public String getItemName(){
        return itemName;
    }
    
    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.itemId;
        hash = 61 * hash + this.itemStock;
        hash = 61 * hash + Objects.hashCode(this.itemPrice);
        hash = 61 * hash + Objects.hashCode(this.itemName);
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
        final VendingMachineItem other = (VendingMachineItem) obj;
        if (this.itemId != other.itemId) {
            return false;
        }
        if (this.itemStock != other.itemStock) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemPrice, other.itemPrice)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VendingMachineItem{" + "itemId=" + itemId + ", itemStock=" + itemStock + ", itemPrice=" + itemPrice + ", itemName=" + itemName + '}';
    }
    
}
