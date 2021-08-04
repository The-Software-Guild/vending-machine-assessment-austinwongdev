/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.service;

import com.aaw.vendingmachine.dao.VendingMachinePersistenceException;
import com.aaw.vendingmachine.dto.VendingMachineItem;
import java.util.List;

/**
 *
 * @author Austin Wong
 */
public interface VendingMachineServiceLayer {
    
    void attemptPurchase(VendingMachineItem item) throws
            InsufficientFundsException, NoItemInventoryException;
    
    List<VendingMachineItem> getAllItems() throws 
            VendingMachinePersistenceException;
    
    VendingMachineItem getItem(int itemId);
    
    int addUserMoney(int amountToAdd);
    
    boolean isItemAvailable(VendingMachineItem item);
    
    int compareItemPriceToUserMoney(VendingMachineItem item);
    
}
