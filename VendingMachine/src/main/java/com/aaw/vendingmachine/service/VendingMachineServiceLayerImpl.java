/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.service;

import com.aaw.vendingmachine.dao.VendingMachineAuditDao;
import com.aaw.vendingmachine.dao.VendingMachineDao;
import com.aaw.vendingmachine.dao.VendingMachinePersistenceException;
import com.aaw.vendingmachine.dto.Change;
import com.aaw.vendingmachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineServiceLayerImpl 
        implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    
    public VendingMachineServiceLayerImpl(
            VendingMachineDao dao, VendingMachineAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public void loadInventory() throws VendingMachinePersistenceException{
        try{
            dao.loadInventory();
        } catch (VendingMachinePersistenceException ex){
            auditDao.writeAuditEntry(ex.getMessage());
            throw new VendingMachinePersistenceException(ex.getMessage());
        }
        auditDao.writeAuditEntry("Inventory Loaded");
    }
    
    @Override
    public void saveInventory() throws VendingMachinePersistenceException{
        try{
            dao.saveInventory();
        } catch (VendingMachinePersistenceException ex){
            auditDao.writeAuditEntry(ex.getMessage());
            throw new VendingMachinePersistenceException(ex.getMessage());
        }
        auditDao.writeAuditEntry("Inventory Saved");
    }
    
    @Override
    public Map<Integer, VendingMachineItem> getInventoryMap(){
        return dao.getInventoryMap();
    }
    
    @Override
    public List<VendingMachineItem> getAllVendingMachineItems(){
        return dao.getAllVendingMachineItems();
    }
    
    @Override
    public VendingMachineItem getVendingMachineItem(int vendingMachineItemId){
        return dao.getVendingMachineItem(vendingMachineItemId);
    }
    
    @Override
    public boolean isVendingMachineItemAvailable(
            VendingMachineItem vendingMachineItem){
        return (vendingMachineItem.getItemStock()>0);
    }
    
    @Override
    public Change addUserChange(String moneyToAddStr) throws VendingMachinePersistenceException{
        BigDecimal moneyToAdd = new BigDecimal(moneyToAddStr);
        moneyToAdd.setScale(SCALE, ROUNDING_MODE);
        Change currentChange = dao.getUserChange();
        BigDecimal newTotal = currentChange.getTotalInDollars().add(moneyToAdd);
        Change newChange = dao.setUserChange(newTotal);
        auditDao.writeAuditEntry("$" + moneyToAddStr + " inserted.");
        return newChange;
    }
    
    @Override
    public Change getUserChange(){
        return dao.getUserChange();
    }
    
    @Override
    public Change dispenseChange() throws VendingMachinePersistenceException{
        Change changeToDispense = dao.getUserChange();
        dao.setUserChange(new BigDecimal("0.00"));
        auditDao.writeAuditEntry("$" + changeToDispense.getTotalInDollars().toString() + " dispensed.");
        return changeToDispense;
    }
    
    @Override
    public Change attemptPurchase(VendingMachineItem itemToPurchase)
            throws InsufficientFundsException, NoItemInventoryException,
            VendingMachinePersistenceException{
        
        if (!this.isVendingMachineItemAvailable(itemToPurchase)){
            auditDao.writeAuditEntry("Out of stock item selected.");
            throw new NoItemInventoryException("Item unavailable for purchase.");
        }
        
        int itemPriceComparedToUserChange = this.compareItemPriceToUserChange(itemToPurchase);
        
        switch (itemPriceComparedToUserChange){
            
            // Not enough funds to purchase item
            case 1:
                auditDao.writeAuditEntry("Insufficient funds for purchase.");
                throw new InsufficientFundsException("Insufficient funds.");
            
            // Purchase item for exact amount
            case 0:
                auditDao.writeAuditEntry(itemToPurchase.getItemName() + " purchased.");
                dao.setUserChange(new BigDecimal("0.00"));
                dao.decrementItemStock(itemToPurchase);
                break;
                
            // Purchase item and update change remaining
            case -1:
                auditDao.writeAuditEntry(itemToPurchase.getItemName() + " purchased.");
                BigDecimal currentUserTotalMoney = this.getUserChange().getTotalInDollars();
                BigDecimal newUserTotalMoney = currentUserTotalMoney.subtract(itemToPurchase.getItemPrice());
                dao.setUserChange(newUserTotalMoney);
                dao.decrementItemStock(itemToPurchase);
                break;
                
            default:
                return null;
        }
        return dao.getUserChange();
    }
    
    @Override
    public int compareItemPriceToUserChange(VendingMachineItem itemToCompare){
        BigDecimal userChangeAsBigDecimal = this.getUserChange().getTotalInDollars();
        return itemToCompare.getItemPrice().compareTo(userChangeAsBigDecimal);
    }
}
