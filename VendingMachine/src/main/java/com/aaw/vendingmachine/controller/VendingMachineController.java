/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.controller;

import com.aaw.vendingmachine.service.VendingMachineServiceLayer;
import com.aaw.vendingmachine.ui.VendingMachineView;

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
        
    }
}
