/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.service;

/**
 *
 * @author Austin Wong
 */
public class NoItemInventoryException extends Exception {

    public NoItemInventoryException(String message){
        super(message);
    }
    
    public NoItemInventoryException(String message, Throwable cause){
        super(message, cause);
    }
}
