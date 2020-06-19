package org.quick.book.online.app.crud.entities.purchaseorder;

import java.text.ParseException;
import java.util.List;

import org.quick.book.online.app.crud.factory.DataServiceFactory;
import org.quick.book.online.app.crud.helper.PurchaseHelper;

import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.PurchaseOrder;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to read purchaseorder data using purchaseorder id
 * Note: We'll create an entity first and then read the same
 * 
 * @author dderose
 *
 */
public class PurchaseOrderRead {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			getPurchaseOrder();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void getPurchaseOrder() throws FMSException, ParseException {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add purchaseorder
			PurchaseOrder purchaseorder = PurchaseHelper.getPurchaseOrderFields(service);
			PurchaseOrder savedPurchaseOrder = service.add(purchaseorder);
			LOG.info("PurchaseOrder created: " + savedPurchaseOrder.getId() + " ::purchaseorder doc num: " + savedPurchaseOrder.getDocNumber());
			
			PurchaseOrder purchaseorderOut = service.findById(savedPurchaseOrder);
			LOG.info("PurchaseOrder amount: " + purchaseorderOut.getTotalAmt());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity findById:: " + error.getMessage()));
		}
		
	}
	
}
