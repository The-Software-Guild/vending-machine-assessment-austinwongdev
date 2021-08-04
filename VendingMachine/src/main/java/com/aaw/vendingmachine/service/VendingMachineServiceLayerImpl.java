/*
 * @author Austin Wong
 * email: austinwongdev@gmail.com
 * date: Aug 3, 2021
 * purpose: 
 */

package com.aaw.vendingmachine.service;

import com.aaw.vendingmachine.dao.VendingMachineAuditDao;
import com.aaw.vendingmachine.dao.VendingMachineDao;

/**
 *
 * @author Austin Wong
 */
public class VendingMachineServiceLayerImpl 
        implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    
    public VendingMachineServiceLayerImpl(
            VendingMachineDao dao, VendingMachineAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }
}
