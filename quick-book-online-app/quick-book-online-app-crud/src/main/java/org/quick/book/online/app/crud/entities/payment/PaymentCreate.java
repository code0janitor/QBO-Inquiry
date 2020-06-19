package org.quick.book.online.app.crud.entities.payment;

import java.util.List;

import org.quick.book.online.app.crud.factory.DataServiceFactory;
import org.quick.book.online.app.crud.helper.PaymentHelper;

import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.Payment;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to create payment
 * 
 * @author dderose
 *
 */
public class PaymentCreate {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			createPayment();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void createPayment() throws Exception {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add payment with cash fields
			Payment payment = PaymentHelper.getCheckPaymentFields(service);
			Payment savedPayment = service.add(payment);
			LOG.info("Payment with cash fields created: " + savedPayment.getId());
		
			// add payment with all fields
			payment = PaymentHelper.getPaymentFields(service);
			savedPayment = service.add(payment);
			LOG.info("Payment with all fields created: " + savedPayment.getId());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity add:: " + error.getMessage()));			
		}
		
	}
	
}
