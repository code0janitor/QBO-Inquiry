package org.quick.book.online.app.crud.entities.item;

import java.util.List;

import org.quick.book.online.app.crud.factory.DataServiceFactory;
import org.quick.book.online.app.crud.helper.ItemHelper;

import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.Item;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to create item
 * 
 * @author dderose
 *
 */
public class ItemCreate {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			createItem();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void createItem() throws Exception {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add item
			Item item = ItemHelper.getItemFields(service);
			Item savedItem = service.add(item);
			LOG.info("Item created: " + savedItem.getId());
						
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity add:: " + error.getMessage()));			
		}
		
	}

}
