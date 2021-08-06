/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.service;

import com.aaw.vendingmachine.dao.VendingMachinePersistenceException;
import com.aaw.vendingmachine.dto.Change;
import com.aaw.vendingmachine.dto.VendingMachineItem;
import java.util.Map;

/**
 *
 * @author Austin Wong
 */
public interface VendingMachineServiceLayer {
    
    void loadInventory() throws VendingMachinePersistenceException;
    
    void saveInventory() throws VendingMachinePersistenceException;
    
    Map<Integer, VendingMachineItem> getInventoryMap();
    
    VendingMachineItem getVendingMachineItem(int vendingMachineItemId);
    
    boolean isVendingMachineItemAvailable(VendingMachineItem vendingMachineItem);
    
    Change addUserChange(String moneyToAddStr) throws VendingMachinePersistenceException;
    
    Change getUserChange();
    
    Change dispenseChange() throws VendingMachinePersistenceException;
    
    Change attemptPurchase(VendingMachineItem itemToPurchase) throws
            InsufficientFundsException, NoItemInventoryException,
            VendingMachinePersistenceException;
    
    int compareItemPriceToUserChange(VendingMachineItem itemToCompare);
    
}
