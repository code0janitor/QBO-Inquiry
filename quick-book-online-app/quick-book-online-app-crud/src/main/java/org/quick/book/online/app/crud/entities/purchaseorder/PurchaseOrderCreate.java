package org.quick.book.online.app.crud.entities.purchaseorder;

import java.util.List;

import org.quick.book.online.app.crud.factory.DataServiceFactory;
import org.quick.book.online.app.crud.helper.PurchaseHelper;

import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.PurchaseOrder;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to create purchaseorder
 * 
 * @author dderose
 *
 */
public class PurchaseOrderCreate {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			createPurchaseOrder();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void createPurchaseOrder() throws Exception {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add purchaseorder
			PurchaseOrder purchaseorder = PurchaseHelper.getPurchaseOrderFields(service);
			PurchaseOrder savedPurchaseOrder = service.add(purchaseorder);
			LOG.info("PurchaseOrder created: " + savedPurchaseOrder.getId() + " ::purchaseorder doc num: " + savedPurchaseOrder.getDocNumber());
					
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity add:: " + error.getMessage()));			
		}
		
	}
	
}
