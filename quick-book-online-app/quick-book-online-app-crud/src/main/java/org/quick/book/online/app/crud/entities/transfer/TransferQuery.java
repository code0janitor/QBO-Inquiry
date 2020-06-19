package org.quick.book.online.app.crud.entities.transfer;

import java.util.List;

import org.quick.book.online.app.crud.factory.DataServiceFactory;
import org.quick.book.online.app.crud.helper.TransferHelper;

import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.Transfer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to query transfer data
 * 1. Query all records
 * 2. Query by id, note we'll add the entity first and then query
 * 
 * @author dderose
 *
 */
public class TransferQuery {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
		
	public static void main(String[] args) {
		try {
			queryTransfer();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void queryTransfer() throws FMSException {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// get all transfer
			String sql = "select * from transfer";
			QueryResult queryResult = service.executeQuery(sql);
			int count = queryResult.getEntities().size();
			
			LOG.info("Total number of transfers: " + count);

			// add transfer
			Transfer transfer = TransferHelper.getTransferFields(service);
			Transfer savedTransfer = service.add(transfer);
			LOG.info("Transfer created: " + savedTransfer.getId());

			// get transfer data based on id
			sql = "select * from transfer where id = '" + savedTransfer.getId() + "'"; 
			queryResult = service.executeQuery(sql);
			transfer = (Transfer) queryResult.getEntities().get(0);
			LOG.info("Transfer amount : " + transfer.getAmount());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling executeQuery :: " + error.getMessage()));
		}
		
	}
}
