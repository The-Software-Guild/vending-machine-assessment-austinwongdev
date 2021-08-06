/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaw.vendingmachine.service;

import com.aaw.vendingmachine.dao.VendingMachineAuditDao;
import com.aaw.vendingmachine.dao.VendingMachineDao;
import com.aaw.vendingmachine.dto.Change;
import com.aaw.vendingmachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineServiceLayerImplTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }

    @Test
    public void testGetVendingMachineItemInvalidItem(){
        int invalidItemId = 150;
        
        VendingMachineItem returnedItem = service.getVendingMachineItem(invalidItemId);
        
        assertNull(returnedItem, "VendingMachineItem should be null.");
        
    }
    
    @Test
    public void testGetVendingMachineItemValidItem(){
        int item1Id = 1;
        int item1Stock = 5;
        BigDecimal item1Price = new BigDecimal("2.25");
        String item1Name = "Cheetos";
        VendingMachineItem expectedItem = 
                new VendingMachineItem(item1Id, item1Name, item1Price, item1Stock);
        
        VendingMachineItem returnedItem = service.getVendingMachineItem(item1Id);
        
        assertNotNull(returnedItem, "VendingMachineItem should not be null.");
        assertEquals(expectedItem, returnedItem, "Returned item should be Cheetos.");
    }
    
    @Test
    public void testGetAllVendingMachineItems(){
        int item1Id = 1;
        int item1Stock = 5;
        BigDecimal item1Price = new BigDecimal("2.25");
        String item1Name = "Cheetos";
        VendingMachineItem item1 = 
                new VendingMachineItem(item1Id, item1Name, item1Price, item1Stock);
        List<VendingMachineItem> expectedItems = new ArrayList<>();
        expectedItems.add(item1);
        
        List<VendingMachineItem> returnedItems = service.getAllVendingMachineItems();
        
        assertNotNull(returnedItems, 
                "List of vending machine items should not be null.");
        assertEquals(expectedItems, returnedItems, 
                "List of vending machine items should consist of Cheetos.");
    }
    
    @Test
    public void testIsVendingMachineItemAvailableTrue(){
        VendingMachineItem item1 = service.getVendingMachineItem(1);
        
        boolean isItemAvailable = service.isVendingMachineItemAvailable(item1);
        
        assertTrue(isItemAvailable, "Cheetos should be available.");
    }
    
    @Test
    public void testIsVendingMachineItemAvailableFalse(){
        VendingMachineItem item1 = service.getVendingMachineItem(1);
        item1.setItemStock(0);
        
        boolean isItemAvailable = service.isVendingMachineItemAvailable(item1);
        
        assertFalse(isItemAvailable, "Cheetos should not be available.");
    }
    
    @Test
    public void testAddUserChange0Cents(){
        Change userChange = service.getUserChange();
        String moneyToAdd = "0.00";
        
        Change updatedUserChange = service.addUserChange(moneyToAdd);
        
        assertNotNull(updatedUserChange, "UserMoney should not be null.");
        assertEquals(userChange, updatedUserChange,
                "UserMoney should not have changed by adding 0 cents.");
    }
    
    @Test
    public void testAddGetUserChangeTwice185Cents(){
        Change userChange = service.getUserChange();
        String moneyToAdd = "1.85";
        String expectedTotal = "3.70";
        Change expectedUserChange = new Change(new BigDecimal(expectedTotal));
        
        service.addUserChange(moneyToAdd);
        service.addUserChange(moneyToAdd);
        Change updatedUserChange = service.getUserChange();
        
        assertNotNull(updatedUserChange, "UserChange should not be null.");
        assertEquals(expectedUserChange, updatedUserChange,
                "UserChange should equal 370 cents.");
    }
    
    @Test
    public void testDispenseChange(){
        String moneyToAdd = "1.50";
        Change expectedChangeToDispense = new Change(new BigDecimal(moneyToAdd));
        Change expectedChangeRemaining = new Change(new BigDecimal("0.00"));
        
        service.addUserChange(moneyToAdd);
        Change userChangeToDispense = service.dispenseChange();
        Change remainingChange = service.getUserChange();
        
        assertNotNull(userChangeToDispense, "DispenseChange should return a non-null Change object.");
        assertEquals(expectedChangeToDispense, userChangeToDispense,
                "1.50 should have been dispensed.");
        assertEquals(expectedChangeRemaining, remainingChange,
                "There should be 0.00 remaining.");
    }
    
    @Test
    public void testAttemptPurchaseOutOfStock(){
        VendingMachineItem outOfStockItem = service.getVendingMachineItem(1);
        outOfStockItem.setItemStock(0);
        
        assertThrows(NoItemInventoryException.class,
                () -> service.attemptPurchase(outOfStockItem),
                "Should throw NoItemInventoryException.");
    }
    
    @Test
    public void testAttemptPurchaseInStock() throws InsufficientFundsException, NoItemInventoryException{
        VendingMachineItem itemToPurchase = service.getVendingMachineItem(1);
        String moneyToAdd = "2.50";
        service.addUserChange(moneyToAdd);
        Change expectedChangeRemaining = new Change(new BigDecimal("0.25"));
        
        VendingMachineItem purchasedItem = service.attemptPurchase(itemToPurchase);
        Change remainingChange = service.getUserChange();
        
        assertNotNull(purchasedItem, "Should have returned a VendingMachineItem.");
        assertEquals(itemToPurchase, purchasedItem, "Purchased item should be Cheetos.");
        assertEquals(expectedChangeRemaining, remainingChange,
                "User should have 25 cents remaining.");
    }
    
    @Test
    public void testCompareItemPriceToUserMoneyExactAmount() {
        VendingMachineItem itemToPurchase = service.getVendingMachineItem(1);
        service.addUserChange(itemToPurchase.getItemPrice().toString());
        int expectedResult = 0;
        
        int result = service.compareItemPriceToUserChange(itemToPurchase);
        
        assertEquals(expectedResult, result, "Items should be same price.");
    }
    
    @Test
    public void testCompareItemPriceToUserMoneyInsufficientFunds(){
        VendingMachineItem itemToPurchase = service.getVendingMachineItem(1);
        service.addUserChange("1.00");
        int expectedResult = 1;
        
        int result = service.compareItemPriceToUserChange(itemToPurchase);
        
        assertEquals(expectedResult, result, 
                "Item price should be greater than user funds.");
    }
    
    @Test
    public void testCompareItemPriceToUserMoneyNeedsChange() {
        VendingMachineItem itemToPurchase = service.getVendingMachineItem(1);
        service.addUserChange("3.00");
        int expectedResult = -1;
        
        int result = service.compareItemPriceToUserChange(itemToPurchase);
        
        assertEquals(expectedResult, result, 
                "Item price should be less than user funds.");
    }
}
