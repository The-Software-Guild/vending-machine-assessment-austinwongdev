/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.controller;

import com.aaw.vendingmachine.dao.VendingMachinePersistenceException;
import com.aaw.vendingmachine.dto.Change;
import com.aaw.vendingmachine.dto.VendingMachineItem;
import com.aaw.vendingmachine.service.InsufficientFundsException;
import com.aaw.vendingmachine.service.NoItemInventoryException;
import com.aaw.vendingmachine.service.VendingMachineServiceLayer;
import com.aaw.vendingmachine.ui.VendingMachineView;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineController {
    
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    
    public VendingMachineController(
            VendingMachineView view, VendingMachineServiceLayer service){
        this.view = view;
        this.service = service;
    }
    
    public void run(){
        
        // Load Inventory
        try{
            service.loadInventory();
        } catch (VendingMachinePersistenceException ex){
            view.displayErrorMessage(ex.getMessage());
            view.displayExitMessage();
            return;
        }

        // Create Mapping from Menu Choices to Item Ids
        Map<Integer, VendingMachineItem> inventoryMap = service.getInventoryMap();
        Map<Integer, VendingMachineItem> menuIdToVendingMachineItem = new TreeMap<>();
        Set<Integer> purchaseMenuIds = new HashSet<>();
        int nextAvailableMenuChoice = 2;
        for (VendingMachineItem item : inventoryMap.values()){
            menuIdToVendingMachineItem.put(nextAvailableMenuChoice, item);
            purchaseMenuIds.add(nextAvailableMenuChoice);
            nextAvailableMenuChoice += 1;
        }
        
        // Display Main Menu and Prompt for Input
        boolean continueShopping = true;
        while (continueShopping){
            int mainMenuSelection = view.displayMainMenuAndGetSelection(menuIdToVendingMachineItem);
            
            // CashInputMenu
            if (mainMenuSelection == 1){
                
            }
            // Attempt Purchase
            else if (purchaseMenuIds.contains(mainMenuSelection)){
                VendingMachineItem itemToPurchase = menuIdToVendingMachineItem.get(mainMenuSelection);
                try{
                    service.attemptPurchase(itemToPurchase);
                }
                catch (InsufficientFundsException | NoItemInventoryException ex){
                    view.displayErrorMessage(ex.getMessage());
                }
            }
            else if (mainMenuSelection == nextAvailableMenuChoice){
                // Dispense Change
                Change changeToDispense = service.dispenseChange();
                view.displayDispensedChange(changeToDispense);

                // Save Inventory
                try{
                    service.saveInventory();
                } catch (VendingMachinePersistenceException ex){
                    view.displayErrorMessage(ex.getMessage());
                }
                
                continueShopping = false;
            }
        }
        view.displayExitMessage();
    }
}
