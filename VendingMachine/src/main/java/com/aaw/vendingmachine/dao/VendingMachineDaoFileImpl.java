/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.dao;

import com.aaw.vendingmachine.dto.VendingMachineItem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao{

    private final String INVENTORY_FILE;
    private static final String DELIMITER = "::";
    private Map<Integer, VendingMachineItem> vendingMachine = new HashMap<>();
    private int nextId = 1;
    
    public VendingMachineDaoFileImpl(){
        this.INVENTORY_FILE = "inventory.txt";
    }
    
    public VendingMachineDaoFileImpl(String fileName){
        this.INVENTORY_FILE = fileName;
    }
    
    @Override
    public VendingMachineItem addItem(VendingMachineItem item){
        return vendingMachine.put(item.getItemId(), item);
    }
    
    @Override
    public VendingMachineItem getItem(int itemId){
        return vendingMachine.get(itemId);
    }
    
    @Override
    public int decrementItem(VendingMachineItem item){
        int itemStock = item.getItemStock();
        itemStock -= 1;
        item.setItemStock(itemStock);
        return itemStock;
    }
    
    @Override
    public List<VendingMachineItem> getAllItems(){
        return new ArrayList(vendingMachine.values());
    }
    
    private String marshallItem(VendingMachineItem item){
        String itemAsString = item.getItemName() + DELIMITER;
        itemAsString += item.getItemPrice().toString() + DELIMITER;
        itemAsString += item.getItemStock();
        return itemAsString;
    }
    
    private VendingMachineItem unmarshallItem(String itemAsText){
        String[] itemTokens = itemAsText.split(DELIMITER);
        int itemId = this.nextId;
        String itemName = itemTokens[0];
        BigDecimal itemPrice = new BigDecimal(itemTokens[1]);
        int itemStock = Integer.parseInt(itemTokens[2]);
        VendingMachineItem item = 
                new VendingMachineItem(itemId, itemName, itemPrice, itemStock);
        this.nextId += 1;
        return item;
    }
    
    
    @Override
    public void loadInventory() throws VendingMachinePersistenceException{
        Scanner scanner;
        
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        }
        catch (FileNotFoundException e){
            throw new VendingMachinePersistenceException(
                    "Could not load vending machine inventory data into memory.", e);
        }
        
        String currentLine;
        VendingMachineItem currentItem;
        while (scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            vendingMachine.put(currentItem.getItemId(), currentItem);
        }
        
        scanner.close();
    }
    
    @Override
    public void saveInventory() throws VendingMachinePersistenceException{
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e){
            throw new VendingMachinePersistenceException(
                    "Could not save vending machine inventory data.", e);
        }
        
        String itemAsText;
        List<VendingMachineItem> itemList = this.getAllItems();
        for (VendingMachineItem currentItem : itemList){
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }
    
}
