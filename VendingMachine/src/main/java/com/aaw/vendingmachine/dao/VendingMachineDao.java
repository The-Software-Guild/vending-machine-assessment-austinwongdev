/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.dao;

import com.aaw.vendingmachine.dto.VendingMachineItem;
import java.util.List;

/**
 *
 * @author Austin Wong
 */
public interface VendingMachineDao {

    void addItem(VendingMachineItem item);
    
    VendingMachineItem getItem(int itemId);
    
    int incrementItem(VendingMachineItem item);
    
    int decrementItem(VendingMachineItem item);
    
    List<VendingMachineItem> getAllItems();
    
    void loadInventory() throws VendingMachinePersistenceException;
    
    void saveInventory() throws VendingMachinePersistenceException;
}
