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
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message){
        super(message);
    }
    
    public InsufficientFundsException(String message, Throwable cause){
        super(message, cause);
    }
}
