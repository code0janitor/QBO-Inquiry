package org.quick.book.online.app.crud.entities.vendor;

import java.util.List;

import org.quick.book.online.app.crud.factory.DataServiceFactory;
import org.quick.book.online.app.crud.helper.VendorHelper;

import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.Vendor;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to create vendor
 * 1. Using mandatory fields
 * 2. Using all fields
 * 
 * @author dderose
 *
 */
public class VendorCreate {
	
	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			createVendor();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void createVendor() throws Exception {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add vendor with minimum mandatory fields
			Vendor vendor = VendorHelper.getVendorWithMandatoryFields();
			Vendor savedVendor = service.add(vendor);
			LOG.info("Vendor with mandatory fields created: " + savedVendor.getId() + " ::vendor name: " + savedVendor.getDisplayName());
			
			// add vendor with all fields
			vendor = VendorHelper.getVendorWithAllFields(service);
			savedVendor = service.add(vendor);
			LOG.info("Vendor with all fields created: " + savedVendor.getId() + " ::vendor name: " + savedVendor.getDisplayName());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity add:: " + error.getMessage()));			
		}
		
	}
	


}
