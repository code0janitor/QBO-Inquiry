package org.quick.book.online.app.crud.entities.employee;

import java.util.List;

import org.quick.book.online.app.crud.factory.DataServiceFactory;
import org.quick.book.online.app.crud.helper.EmployeeHelper;

import com.intuit.ipp.data.Employee;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to delete employee data
 * Name-list resources can only be soft deleted meaning, marked as inactive
 * Note: We'll create an entity first and then delete the same
 * 
 * @author dderose
 *
 */
public class EmployeeDelete {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			deleteEmployee();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void deleteEmployee() throws FMSException {
		
		try {
			DataService service = DataServiceFactory.getDataService();
			
			// create employee
			Employee employee = EmployeeHelper.getEmployeeWithMandatoryFields();
			Employee addEmployee = service.add(employee);
			LOG.info("Employee added : " + addEmployee.getId() + " active flag ::: " + addEmployee.isActive());

			// set active flag as false to soft delete
			addEmployee.setActive(false);
			Employee deletedEmployee = service.update(addEmployee);		
			LOG.info("Employee deleted : " + deletedEmployee.getId() + " active flag ::: " + deletedEmployee.isActive());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while deleting entity :: " + error.getMessage()));
		}
		
	}
}
