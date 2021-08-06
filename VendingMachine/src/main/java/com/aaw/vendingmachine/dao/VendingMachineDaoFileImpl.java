/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.dao;

import com.aaw.vendingmachine.dto.Change;
import com.aaw.vendingmachine.dto.VendingMachineItem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao{

    private final String INVENTORY_FILE;
    private static final String DELIMITER = "::";
    private Map<Integer, VendingMachineItem> inventoryMap = new TreeMap<>();
    private int nextVendingMachineItemId = 1;
    private Change userChange;
    
    public VendingMachineDaoFileImpl() {
        this.userChange = new Change(new BigDecimal("0.00"));
        this.INVENTORY_FILE = "inventory.txt";
    }
    
    public VendingMachineDaoFileImpl(String inventoryFileName){
        this.userChange = new Change(new BigDecimal("0.00"));
        this.INVENTORY_FILE = inventoryFileName;
    }
    
    @Override
    public VendingMachineItem addVendingMachineItem(
            VendingMachineItem vendingMachineItem){
        return inventoryMap.put(vendingMachineItem.getItemId(), vendingMachineItem);
    }
    
    @Override
    public VendingMachineItem getVendingMachineItem(int vendingMachineItemId){
        return inventoryMap.get(vendingMachineItemId);
    }
    
    @Override
    public int decrementItemStock(VendingMachineItem vendingMachineItem){
        int itemStock = vendingMachineItem.getItemStock();
        itemStock -= 1;
        vendingMachineItem.setItemStock(itemStock);
        return itemStock;
    }
    
    @Override
    public Map<Integer, VendingMachineItem> getInventoryMap(){
        return this.inventoryMap;
    }
    
    @Override
    public List<VendingMachineItem> getAllVendingMachineItems(){
        return new ArrayList(inventoryMap.values());
    }
    
    private String marshallItem(VendingMachineItem vendingMachineItem){
        String vendingMachineItemAsString = 
                vendingMachineItem.getItemName() + DELIMITER;
        vendingMachineItemAsString += 
                vendingMachineItem.getItemPrice().toString() + DELIMITER;
        vendingMachineItemAsString += 
                vendingMachineItem.getItemStock();
        return vendingMachineItemAsString;
    }
    
    private VendingMachineItem unmarshallItem(String vendingMachineItemAsString){
        String[] itemTokens = vendingMachineItemAsString.split(DELIMITER);
        int itemId = this.nextVendingMachineItemId;
        String itemName = itemTokens[0];
        BigDecimal itemPrice = new BigDecimal(itemTokens[1]);
        int itemStock = Integer.parseInt(itemTokens[2]);
        VendingMachineItem vendingMachineItem = 
                new VendingMachineItem(itemId, itemName, itemPrice, itemStock);
        this.nextVendingMachineItemId += 1;
        return vendingMachineItem;
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
            inventoryMap.put(currentItem.getItemId(), currentItem);
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
        
        String vendingMachineItemAsText;
        List<VendingMachineItem> vendingMachineItemList = 
                this.getAllVendingMachineItems();
        for (VendingMachineItem currentItem : vendingMachineItemList){
            vendingMachineItemAsText = marshallItem(currentItem);
            out.println(vendingMachineItemAsText);
            out.flush();
        }
        out.close();
    }
    
    @Override
    public Change setUserChange(BigDecimal newUserChangeTotal){
        Change newUserChange = new Change(newUserChangeTotal);
        this.userChange = newUserChange;
        return this.userChange;
    }
    
    @Override
    public Change getUserChange(){
        return this.userChange;
    }
    
}
