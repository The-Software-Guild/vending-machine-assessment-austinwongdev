/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.service;

import com.aaw.vendingmachine.dao.VendingMachineDao;
import com.aaw.vendingmachine.dao.VendingMachinePersistenceException;
import com.aaw.vendingmachine.dto.Change;
import com.aaw.vendingmachine.dto.NegativeChangeException;
import com.aaw.vendingmachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    private VendingMachineItem onlyItem;
    private Change userChange;
    
    public VendingMachineDaoStubImpl() throws NegativeChangeException{
        
        int itemId = 1;
        int itemStock = 5;
        BigDecimal itemPrice = new BigDecimal("2.25");
        String itemName = "Cheetos";
        
        onlyItem = new VendingMachineItem(itemId, itemName, itemPrice, itemStock);
        
        userChange = new Change(new BigDecimal("0.00"));
    }
    
    public VendingMachineDaoStubImpl(VendingMachineItem testItem){
        this.onlyItem = testItem;
    }
    
    @Override
    public VendingMachineItem addVendingMachineItem(VendingMachineItem item){
        if (item.equals(onlyItem)){
            return onlyItem;
        }
        else{
            return null;
        }
    }
    
    @Override
    public VendingMachineItem getVendingMachineItem(int itemId){
        if (itemId == onlyItem.getItemId()){
            return onlyItem;
        }
        else{
            return null;
        }
    }
    
    @Override
    public int decrementItemStock(VendingMachineItem item){
        int itemStock = item.getItemStock();
        if (itemStock > 0){
            itemStock -= 1;
            item.setItemStock(itemStock);
        }
        return itemStock;
    }
    
    @Override
    public Map<Integer, VendingMachineItem> getInventoryMap(){
        Map<Integer, VendingMachineItem> inventoryMap = new TreeMap<>();
        inventoryMap.put(onlyItem.getItemId(), onlyItem);
        return inventoryMap;
    }
    
    @Override
    public List<VendingMachineItem> getAllVendingMachineItems(){
        List<VendingMachineItem> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }
    
    @Override
    public void loadInventory() throws VendingMachinePersistenceException{
        //do nothing
    }
    
    @Override
    public void saveInventory() throws VendingMachinePersistenceException{
        //do nothing
    }
    
    @Override
    public Change setUserChange(BigDecimal newUserChangeTotal) throws NegativeChangeException{
        Change newUserChange = new Change(newUserChangeTotal);
        this.userChange = newUserChange;
        return this.userChange;
    }
    
    @Override
    public Change getUserChange(){
        return this.userChange;
    }
    
}
