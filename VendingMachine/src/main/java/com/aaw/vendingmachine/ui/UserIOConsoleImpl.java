/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.ui;

import java.util.Scanner;

/**
 *
 * @author Austin Wong
 */
public class UserIOConsoleImpl implements UserIO {
    
    private final Scanner console = new Scanner(System.in);
    
    /**
     * Takes in a message to display on the console.
     * @param msg - String of information to display to the user
     */
    @Override
    public void print(String msg){
        System.out.println(msg);
    }
    
    /**
     * Takes in a message to display on the console with a small banner on each
     * side of the message.
     * @param msg - String of information to display to the user
     */
    @Override
    public void printWithBanner(String msg){
        System.out.println("=== "  + msg + " ===");
    }
    
    /**
     * Takes in a message to display on the console and then waits for an
     * answer from the user to return.
     * @param prompt - String of information to display to the user
     * @return - The answer to the message as a String
     */
    @Override
    public String readString(String prompt){
        this.print(prompt);
        String answer = console.nextLine();
        this.print("");
        return answer;
    }
    
    /**
     * Takes in a message to display on the console and then waits for the user
     * to input an integer, continually reprompting the user if a non-integer is
     * entered.
     * @param prompt - String of information to display to the user
     * @return - An integer answer to the prompt
     */
    @Override
    public int readInt(String prompt){
        int num = 0;
        while (true){
            try{
                String stringValue = this.readString(prompt);
                num = Integer.parseInt(stringValue);
                break;
            }
            catch (NumberFormatException ex){
                this.print("Input error. Please try again.\n");
            }
        }
        return num;
    }
    
    /**
     * Takes in a message to display on the console and continually prompts the
     * user with that message until the user inputs an integer between min and 
     * max (inclusive).
     * @param prompt - String to display to the user
     * @param min - minimum acceptable value for return
     * @param max - maximum acceptable value for return
     * @return - An integer answer to the prompt within range from min to max
     */
    @Override
    public int readInt(String prompt, int min, int max){
        int num = 0;
        do{
            num = readInt(prompt);
        } while (num < min || num > max);
        return num;
    }
    
}
