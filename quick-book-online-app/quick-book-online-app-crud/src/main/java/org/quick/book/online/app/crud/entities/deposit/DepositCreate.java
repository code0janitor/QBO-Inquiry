package org.quick.book.online.app.crud.entities.deposit;

import java.util.List;

import org.quick.book.online.app.crud.factory.DataServiceFactory;
import org.quick.book.online.app.crud.helper.DepositHelper;

import com.intuit.ipp.data.Deposit;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to create deposit
 * 
 * @author dderose
 *
 */
public class DepositCreate {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			createDeposit();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void createDeposit() throws Exception {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add deposit
			Deposit deposit = DepositHelper.getDepositFields(service);
			Deposit savedDeposit = service.add(deposit);
			LOG.info("Deposit created: " + savedDeposit.getId());
						
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity add:: " + error.getMessage()));			
		}
		
	}

}
