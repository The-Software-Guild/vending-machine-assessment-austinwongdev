/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine;

import com.aaw.vendingmachine.controller.VendingMachineController;
import com.aaw.vendingmachine.dao.VendingMachineAuditDao;
import com.aaw.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.aaw.vendingmachine.dao.VendingMachineDao;
import com.aaw.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.aaw.vendingmachine.service.VendingMachineServiceLayer;
import com.aaw.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.aaw.vendingmachine.ui.UserIO;
import com.aaw.vendingmachine.ui.UserIOConsoleImpl;
import com.aaw.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Austin Wong
 */
public class App {

    public static void main(String[] args){
        
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        VendingMachineServiceLayer myService = 
                new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        
        VendingMachineController myController = 
                new VendingMachineController(myView, myService);
        myController.run();
    }
}
