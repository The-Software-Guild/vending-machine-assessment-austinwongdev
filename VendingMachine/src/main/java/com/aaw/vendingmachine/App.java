/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine;

import com.aaw.vendingmachine.controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Austin Wong
 */
public class App {

    public static void main(String[] args){
        
//        UserIO myIo = new UserIOConsoleImpl();
//        VendingMachineView myView = new VendingMachineView(myIo);
//        
//        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
//        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
//        VendingMachineServiceLayer myService = 
//                new VendingMachineServiceLayerImpl(myDao, myAuditDao);
//        
//        VendingMachineController myController = 
//                new VendingMachineController(myView, myService);
//        myController.run();

          ApplicationContext ctx = 
                  new ClassPathXmlApplicationContext("applicationContext.xml");
          VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
          controller.run();

    }
}
