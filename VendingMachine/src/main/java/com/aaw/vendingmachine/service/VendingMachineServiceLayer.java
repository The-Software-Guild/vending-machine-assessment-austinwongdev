/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.service;

import com.aaw.vendingmachine.dao.VendingMachinePersistenceException;
import com.aaw.vendingmachine.dto.Change;
import com.aaw.vendingmachine.dto.NegativeChangeException;
import com.aaw.vendingmachine.dto.VendingMachineItem;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Austin Wong
 */
public interface VendingMachineServiceLayer {
    
    void loadInventory() throws VendingMachinePersistenceException;
    
    void saveInventory() throws VendingMachinePersistenceException;
    
    Map<Integer, VendingMachineItem> getInventoryMap();
    
    List<VendingMachineItem> getAllVendingMachineItems();
    
    VendingMachineItem getVendingMachineItem(int vendingMachineItemId);
    
    boolean isVendingMachineItemAvailable(VendingMachineItem vendingMachineItem);
    
    Change addUserChange(String moneyToAddStr) throws NegativeChangeException;
    
    Change getUserChange();
    
    Change dispenseChange() throws NegativeChangeException;
    
    VendingMachineItem attemptPurchase(VendingMachineItem itemToPurchase) throws
            InsufficientFundsException, NoItemInventoryException, NegativeChangeException;
    
    int compareItemPriceToUserChange(VendingMachineItem itemToCompare);
    
}
