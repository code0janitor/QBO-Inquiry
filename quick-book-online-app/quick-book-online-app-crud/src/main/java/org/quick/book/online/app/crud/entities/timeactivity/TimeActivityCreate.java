package org.quick.book.online.app.crud.entities.timeactivity;

import java.util.List;

import org.quick.book.online.app.crud.factory.DataServiceFactory;
import org.quick.book.online.app.crud.helper.TimeActivityHelper;

import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.TimeActivity;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to create time activity
 * 
 * @author dderose
 *
 */
public class TimeActivityCreate {
	
	private static final org.slf4j.Logger LOG = Logger.getLogger();
		
	public static void main(String[] args) {
		try {
			createTimeActivity();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void createTimeActivity() throws Exception {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add time activity
			TimeActivity timeActivityIn = TimeActivityHelper.getTimeActivityFields(service);
			TimeActivity timeActivityOut = service.add(timeActivityIn);
			LOG.info("TimeActivity created: " + timeActivityOut.getId() + " ::TimeActivity starttime: " + timeActivityOut.getStartTime() + "::: " + timeActivityOut.getEndTime());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity add:: " + error.getMessage()));			
		}
		
	}
	

}
