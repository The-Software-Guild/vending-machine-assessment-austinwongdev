/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.dao;

import com.aaw.vendingmachine.dto.Change;
import com.aaw.vendingmachine.dto.NegativeChangeException;
import com.aaw.vendingmachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Austin Wong
 */
public interface VendingMachineDao {

    VendingMachineItem addVendingMachineItem(VendingMachineItem item);
    
    VendingMachineItem getVendingMachineItem(int itemId);
    
    int decrementItemStock(VendingMachineItem item);
    
    Map<Integer, VendingMachineItem> getInventoryMap();
    
    List<VendingMachineItem> getAllVendingMachineItems();
    
    void loadInventory() throws VendingMachinePersistenceException;
    
    void saveInventory() throws VendingMachinePersistenceException;
    
    Change setUserChange(BigDecimal totalInDollars) throws NegativeChangeException;
    
    Change getUserChange();
    
}
