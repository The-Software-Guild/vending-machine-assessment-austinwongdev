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
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Austin Wong
 */
public interface VendingMachineServiceLayer {
    
    List<VendingMachineItem> getAllItems() throws 
            VendingMachinePersistenceException;
    
    VendingMachineItem getItem(int itemId);
    
    boolean isItemAvailable(VendingMachineItem item);
    
    BigDecimal addUserMoney(String moneyToAdd);
    
    Change getChange(BigDecimal totalInPennies);
    
    void attemptPurchase(VendingMachineItem item) throws
            InsufficientFundsException, NoItemInventoryException;
    
    int compareItemPriceToUserMoney(VendingMachineItem item);
    
}
