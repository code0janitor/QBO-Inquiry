package org.quick.book.online.app.crud.entities.transfer;

import java.util.List;

import org.quick.book.online.app.crud.factory.DataServiceFactory;
import org.quick.book.online.app.crud.helper.TransferHelper;

import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.Transfer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to create transfer
 * 
 * @author dderose
 *
 */
public class TransferCreate {
	
	private static final org.slf4j.Logger LOG = Logger.getLogger();
		
	public static void main(String[] args) {
		try {
			createTransfer();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void createTransfer() throws Exception {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add transfer
			Transfer transfer = TransferHelper.getTransferFields(service);
			Transfer savedTransfer = service.add(transfer);
			LOG.info("Transfer created: " + savedTransfer.getId());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity add:: " + error.getMessage()));			
		}
		
	}


}
