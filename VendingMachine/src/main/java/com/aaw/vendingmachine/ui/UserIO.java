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
public interface UserIO {

    void print(String msg);
    
    void printWithBanner(String msg);
    
    String readString(String prompt);
    
    int readInt(String prompt);
    
    int readInt(String prompt, int min, int max);
}
