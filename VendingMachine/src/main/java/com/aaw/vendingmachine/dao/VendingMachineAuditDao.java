/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.dao;

/**
 *
 * @author Austin Wong
 */
public interface VendingMachineAuditDao {

    public void writeAuditEntry(String entry)
            throws VendingMachinePersistenceException;
}
