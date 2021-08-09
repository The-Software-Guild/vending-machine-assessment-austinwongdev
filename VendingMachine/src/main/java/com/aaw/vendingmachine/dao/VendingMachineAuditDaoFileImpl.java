/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao {

    private final String AUDIT_FILE;
    
    public VendingMachineAuditDaoFileImpl(){
        this.AUDIT_FILE = "inventoryAudit.txt";
    }
    
    public VendingMachineAuditDaoFileImpl(String auditFile){
        this.AUDIT_FILE = auditFile;
    }
    
    @Override
    public void writeAuditEntry(String entry)
            throws VendingMachinePersistenceException{
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e){
            throw new VendingMachinePersistenceException("Could not save vending machine audit data.", e);
        }
        
        out.println(LocalDateTime.now() + " - " + entry);
        out.flush();
        out.close();
    }
}