/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaw.vendingmachine.dao;

import com.aaw.vendingmachine.dto.VendingMachineItem;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineDaoFileImplTest {
    
    private VendingMachineDao testDaoWithoutData;
    private final String TEST_FILE_WITHOUT_DATA = "testInventoryWithoutData.txt";
    
    @BeforeEach
    public void setUp() throws IOException {
        new FileWriter(TEST_FILE_WITHOUT_DATA);
        this.testDaoWithoutData = new VendingMachineDaoFileImpl(TEST_FILE_WITHOUT_DATA);
    }

    @Test
    public void testAddGetItem(){
        int itemId = 1;
        int itemStock = 5;
        BigDecimal itemPrice = new BigDecimal("3.00");
        String itemName = "Cheetos";
        VendingMachineItem item = 
                new VendingMachineItem(itemId, itemName, itemPrice, itemStock);
        
        testDaoWithoutData.addItem(item);
        VendingMachineItem returnedItem = testDaoWithoutData.getItem(itemId);
        assertNotNull(returnedItem, "Returned item should not be null.");
        assertEquals(item, returnedItem, "Returned item should be Cheetos.");
    }
    
    @Test
    public void testAddGetAllItems(){
        int item1Id = 1;
        int item1Stock = 5;
        BigDecimal item1Price = new BigDecimal("3.00");
        String item1Name = "Cheetos";
        VendingMachineItem item1 = 
                new VendingMachineItem(item1Id, item1Name, item1Price, item1Stock);
        
        int item2Id = 2;
        int item2Stock = 3;
        BigDecimal item2Price = new BigDecimal("1.50");
        String item2Name = "Gummy Bears";
        VendingMachineItem item2 = 
                new VendingMachineItem(item2Id, item2Name, item2Price, item2Stock);
        
        List<VendingMachineItem> expectedList = new ArrayList<>();
        expectedList.add(item1);
        expectedList.add(item2);
        
        testDaoWithoutData.addItem(item1);
        testDaoWithoutData.addItem(item2);
        List<VendingMachineItem> allItems = testDaoWithoutData.getAllItems();
        
        assertNotNull(allItems, "Returned list should not be null.");
        assertEquals(expectedList, allItems);
    }
    
    @Test
    public void testGetAllItemsEmpty(){
        
        List<VendingMachineItem> emptyList = new ArrayList<>();
        
        List<VendingMachineItem> allItems = testDaoWithoutData.getAllItems();
        
        assertNotNull(allItems, "Returned list should not be null.");
        assertEquals(emptyList, allItems, "Returned list should be empty.");
    }
    
    @Test
    public void testDecrementItem(){
        int item1Id = 1;
        int item1Stock = 5;
        BigDecimal item1Price = new BigDecimal("3.00");
        String item1Name = "Cheetos";
        VendingMachineItem item1 = 
                new VendingMachineItem(item1Id, item1Name, item1Price, item1Stock);
        
        int expectedItem1Stock = item1Stock - 1;
        
        testDaoWithoutData.addItem(item1);
        item1Stock = testDaoWithoutData.decrementItem(item1);
        
        assertEquals(expectedItem1Stock, item1Stock, "Cheetos should have a stock of 4.");
    }
    
    @Test
    public void testSaveLoadInventory() throws VendingMachinePersistenceException {
        
        int item1Id = 1;
        int item1Stock = 2;
        BigDecimal item1Price = new BigDecimal("5.00");
        String item1Name = "Lays";
        VendingMachineItem item1 = 
                new VendingMachineItem(item1Id, item1Name, item1Price, item1Stock);
        
        int item2Id = 2;
        int item2Stock = 8;
        BigDecimal item2Price = new BigDecimal("2.00");
        String item2Name = "Coca-cola";
        VendingMachineItem item2 = 
                new VendingMachineItem(item1Id, item1Name, item1Price, item1Stock);
        
        testDaoWithoutData.addItem(item1);
        testDaoWithoutData.addItem(item2);
        List<VendingMachineItem> expectedItems = testDaoWithoutData.getAllItems();
        
        testDaoWithoutData.saveInventory();
        testDaoWithoutData = new VendingMachineDaoFileImpl(TEST_FILE_WITHOUT_DATA);
        List<VendingMachineItem> returnedItemsBeforeLoading = 
                testDaoWithoutData.getAllItems();
        testDaoWithoutData.loadInventory();
        List<VendingMachineItem> returnedItems = testDaoWithoutData.getAllItems();
        
        assertNotEquals(expectedItems, returnedItemsBeforeLoading, 
                "Inventory should be reset to be empty.");
        assertNotNull(returnedItems, "Loaded inventory should not be null.");
        assertEquals(expectedItems, returnedItems, 
                "Loaded inventory does not match saved inventory.");
    }    
}
