package org.quick.book.online.app.crud.entities.classentity;

import java.util.List;

import org.quick.book.online.app.crud.factory.DataServiceFactory;
import org.quick.book.online.app.crud.helper.ClassHelper;

import com.intuit.ipp.data.Class;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to read class data using class id
 * Note: We'll create an entity first and then read the same
 * 
 * @author dderose
 *
 */
public class ClassRead {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			getClassById();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void getClassById() throws FMSException {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add class
			Class classObj = ClassHelper.getClassFields();
			Class savedClass = service.add(classObj);
			LOG.info("Class created: " + savedClass.getId());
			
			Class classOut = service.findById(savedClass);
			LOG.info("Class name: " + classOut.getFullyQualifiedName());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity findById:: " + error.getMessage()));
		}
		
	}
	
}
