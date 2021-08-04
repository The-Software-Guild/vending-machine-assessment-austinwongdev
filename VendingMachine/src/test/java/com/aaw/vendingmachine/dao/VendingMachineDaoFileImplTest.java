/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaw.vendingmachine.dao;

import com.aaw.vendingmachine.dto.VendingMachineItem;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineDaoFileImplTest {
    
    private VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws IOException {
        String testFile = "testInventory.txt";
        new FileWriter(testFile, true); // append mode
        this.testDao = new VendingMachineDaoFileImpl(testFile);
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddGetItem(){
        
    }
    
    @Test
    public void testAddGetAllItems(){
        
    }
    
    @Test
    public void testIncrementItem(){
        
    }
    
    @Test
    public void testDecrementItem(){
        
    }
    
    @Test
    public void testLoadInventory(){
        
    }
    
    @Test
    public void testSaveInventory(){
        
    }
    
}
