/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.ui;

import com.aaw.vendingmachine.dto.Change;
import com.aaw.vendingmachine.dto.VendingMachineItem;
import java.util.Map;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineView {

    private UserIO io;
    private String menuPrompt = "Please select from the above choices.";
    
    public VendingMachineView(UserIO io){
        this.io = io;
    }
    
    public int displayMainMenuAndGetSelection(
            Map<Integer, VendingMachineItem> menuIdToVendingMachineItemId, 
            Change balance){
        
        this.displayAccountBalance(balance);
        io.print("");
        io.printWithBanner("MAIN MENU");
        io.print("1 - Insert cash");
        
        for (int menuId : menuIdToVendingMachineItemId.keySet()){
            VendingMachineItem vendingMachineItem = menuIdToVendingMachineItemId.get(menuId);
            io.print(menuId +
                    " - " +
                    vendingMachineItem.getItemName() + 
                    " ($" + 
                    vendingMachineItem.getItemPrice().toString() +
                    ")");
        }
        int lastSelection = menuIdToVendingMachineItemId.size() + 2;
        io.print(lastSelection +
                " - " +
                "Dispense Change and Exit");
        io.print("");
        return io.readInt(menuPrompt, 1, lastSelection);
    }
    
    public String displayInputCashPromptAndGetAmount(){
        String amountStr = io.readMoney(
                "Enter total cash to input or 0 to return: ");
        return amountStr;
    }
    
    public void displayAccountBalance(Change balance){
        io.printWithBanner("ACCOUNT BALANCE");
        io.print("$" + balance.getTotalInDollars().toString());
    }
    
    public void displayPurchaseSuccessMessage(VendingMachineItem purchasedItem){
        io.print("Enjoy your " + purchasedItem.getItemName());
        io.print("");
    }
    
    public void displayDispensedChangeAndWait(Change changeToDispense){
        io.printWithBanner("DISPENSING CHANGE");
        io.print(changeToDispense.toString());
        io.print("");
        io.pressEnterToContinue();
    }
    
    public void displayErrorMessageAndWait(String errorMsg){
        io.printWithBanner("ERROR");
        io.print(errorMsg);
        io.print("");
        io.pressEnterToContinue();
    }
    
    public void displayExitMessage(){
        io.print("Exiting...");
        io.print("");
    }
}
