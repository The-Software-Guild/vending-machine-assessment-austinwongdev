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
    
    private Map<Integer, VendingMachineItem> menuIdToVendingMachineItem = 
            new TreeMap<>();
    private Set<Integer> itemMenuIds = new HashSet<>();
    private int exitOption;
    
    public VendingMachineController(
            VendingMachineView view, VendingMachineServiceLayer service){
        this.view = view;
        this.service = service;
    }
    
    public void run(){
        
        // Load Inventory
        if (!loadInventory()){
            return;
        }

        // Create Map from Menu Choices to Item Ids
        createMenuChoices();
        
        // Display Main Menu and Prompt for Input
        boolean continueShopping = true;
        Change balance = service.getUserChange();
        while (continueShopping){
            
            int mainMenuSelection = 
                    view.displayMainMenuAndGetSelection(
                            menuIdToVendingMachineItem, balance);
            
            // CashInputMenu
            if (mainMenuSelection == 1){
                balance = inputCash();
            }
            
            // Attempt Purchase
            else if (itemMenuIds.contains(mainMenuSelection)){
                VendingMachineItem itemToPurchase = menuIdToVendingMachineItem.get(mainMenuSelection);
                try{
                    service.attemptPurchase(itemToPurchase);
                }
                catch (InsufficientFundsException | NoItemInventoryException ex){
                    view.displayErrorMessage(ex.getMessage());
                }
            }
            
            // Exit
            else if (mainMenuSelection == exitOption){
                
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
    
    private Change inputCash(){
        String cashToInput = view.displayInputCashPromptAndGetAmount();
        return service.addUserChange(cashToInput);
    }
    
    private boolean loadInventory(){
        try{
            service.loadInventory();
        } catch (VendingMachinePersistenceException ex){
            view.displayErrorMessage(ex.getMessage());
            view.displayExitMessage();
            return false;
        }
        return true;
    }
    
    private void createMenuChoices(){
        Map<Integer, VendingMachineItem> inventoryMap = service.getInventoryMap();
        
        int nextAvailableMenuChoice = 2;
        for (VendingMachineItem item : inventoryMap.values()){
            menuIdToVendingMachineItem.put(nextAvailableMenuChoice, item);
            itemMenuIds.add(nextAvailableMenuChoice);
            nextAvailableMenuChoice += 1;
        }
        
        exitOption = nextAvailableMenuChoice;
    }
}
