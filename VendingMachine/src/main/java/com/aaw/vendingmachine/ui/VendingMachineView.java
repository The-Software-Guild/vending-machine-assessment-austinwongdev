/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.ui;

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
}
