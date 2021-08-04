/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 4, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.dao;

/**
 *
 * @author Austin Wong
 */
public class VendingMachinePersistenceException extends Exception {
    
    public VendingMachinePersistenceException(String message){
        super(message);
    }
    
    public VendingMachinePersistenceException(String message, Throwable cause){
        super(message, cause);
    }
}
