/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.service;

import com.aaw.vendingmachine.dao.VendingMachineDao;
import com.aaw.vendingmachine.dao.VendingMachinePersistenceException;
import com.aaw.vendingmachine.dto.VendingMachineItem;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    public VendingMachineItem onlyItem;
    
    public VendingMachineDaoStubImpl(){
        
        int itemId = 1;
        int itemStock = 5;
        BigInteger itemPrice = new BigInteger("2.25");
        String itemName = "Cheetos";
        
        onlyItem = new VendingMachineItem(itemId, itemStock, itemPrice, itemName);
    }
    
    public VendingMachineDaoStubImpl(VendingMachineItem testItem){
        this.onlyItem = testItem;
    }
    
    @Override
    public VendingMachineItem addItem(VendingMachineItem item){
        if (item.equals(onlyItem)){
            return onlyItem;
        }
        else{
            return null;
        }
    }
    
    @Override
    public VendingMachineItem getItem(int itemId){
        if (itemId == onlyItem.getItemId()){
            return onlyItem;
        }
        else{
            return null;
        }
    }
    
    @Override
    public int decrementItem(VendingMachineItem item){
        int itemStock = item.getItemStock();
        if (itemStock > 0){
            itemStock -= 1;
            item.setItemStock(itemStock);
        }
        return itemStock;
    }
    
    @Override
    public List<VendingMachineItem> getAllItems(){
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
    
}
