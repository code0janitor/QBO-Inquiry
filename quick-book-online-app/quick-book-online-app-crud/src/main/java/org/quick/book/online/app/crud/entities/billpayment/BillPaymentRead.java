package org.quick.book.online.app.crud.entities.billpayment;

import java.text.ParseException;
import java.util.List;

import org.quick.book.online.app.crud.factory.DataServiceFactory;
import org.quick.book.online.app.crud.helper.BillHelper;

import com.intuit.ipp.data.BillPayment;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to read billpayment data using billpayment id
 * Note: We'll create an entity first and then read the same
 * 
 * @author dderose
 *
 */
public class BillPaymentRead {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			getBillPayment();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void getBillPayment() throws FMSException, ParseException {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add billpayment with minimum mandatory fields
			BillPayment account = BillHelper.getBillPaymentFields(service);
			BillPayment savedBillPayment = service.add(account);
			LOG.info("BillPayment created: " + savedBillPayment.getId());
			
			BillPayment billpaymentOut = service.findById(savedBillPayment);
			LOG.info("BillPayment amount: " + billpaymentOut.getTotalAmt());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity findById:: " + error.getMessage()));
		}
		
	}
	
}
