package org.quick.book.online.app.crud.entities.department;

import java.util.List;

import org.quick.book.online.app.crud.factory.DataServiceFactory;
import org.quick.book.online.app.crud.helper.DepartmentHelper;

import com.intuit.ipp.data.Department;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to create department
 * 
 * @author dderose
 *
 */
public class DepartmentCreate {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			createDepartment();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void createDepartment() throws Exception {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add department
			Department department = DepartmentHelper.getDepartmentFields();
			Department savedDepartment = service.add(department);
			LOG.info("Department created: " + savedDepartment.getId());
						
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity add:: " + error.getMessage()));			
		}
		
	}
}
