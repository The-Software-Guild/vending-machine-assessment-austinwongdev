/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 4, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.dto;

/**
 *
 * @author Austin Wong
 */
public class NegativeChangeException extends Exception {

    public NegativeChangeException(String msg){
        super(msg);
    }
    
    public NegativeChangeException(String msg, Throwable cause){
        super(msg, cause);
    }
    
}
