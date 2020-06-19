package org.quick.book.online.app.crud.entities.term;

import java.util.List;

import org.quick.book.online.app.crud.factory.DataServiceFactory;
import org.quick.book.online.app.crud.helper.TermHelper;

import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.Term;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to create term
 * 
 * @author dderose
 *
 */
public class TermCreate {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			createTerm();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void createTerm() throws Exception {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add term
			Term term = TermHelper.getTermFields();
			Term savedTerm = service.add(term);
			LOG.info("Term created: " + savedTerm.getId());
						
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity add:: " + error.getMessage()));			
		}
		
	}

}
