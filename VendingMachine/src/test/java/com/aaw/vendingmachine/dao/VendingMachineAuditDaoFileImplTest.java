/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaw.vendingmachine.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineAuditDaoFileImplTest {
    
    private final String TEST_INVENTORY_AUDIT_FILE = "testInventoryAudit.txt";
    private VendingMachineAuditDao testAuditDao;
    
    public VendingMachineAuditDaoFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws IOException {
        new FileWriter(TEST_INVENTORY_AUDIT_FILE);
        this.testAuditDao = new VendingMachineAuditDaoFileImpl(TEST_INVENTORY_AUDIT_FILE);
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testWriteAuditEntryAppend() throws FileNotFoundException, VendingMachinePersistenceException{
        String auditEntry1 = "Cheetos purchased";
        String auditEntry2 = "Gummy Bears purchased";
        String delimiter = " - ";
        
        testAuditDao.writeAuditEntry(auditEntry1);
        testAuditDao.writeAuditEntry(auditEntry2);
        
        Scanner scanner;
        scanner = new Scanner(new BufferedReader(new FileReader(TEST_INVENTORY_AUDIT_FILE)));
        
        String loadedEntry1 = scanner.nextLine().split(delimiter)[1];
        
        assertEquals(auditEntry1, loadedEntry1, 
                "First line of Audit Entry should record a Cheetos purchase.");
        
        String loadedEntry2 = scanner.nextLine().split(delimiter)[1];
        assertEquals(auditEntry2, loadedEntry2, 
                "Second line of Audit Entry should record a Gummy Bear purchase.");
        
    }
    
}
