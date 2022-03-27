package co.edu.icesi.dev.uccareapp.transport.service.interfaces;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectAlreadyExistException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectDoesNotExistException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salespersonquotahistory;
public interface SalesPersonQuotaHistoryService {
	
	public void add(Salespersonquotahistory salesPersonQuotaHistoryService) throws InvalidValueException, ObjectAlreadyExistException;
	public void edit(Salespersonquotahistory salesPersonQuotaHistoryService) throws InvalidValueException, ObjectDoesNotExistException;

	public Optional<Salespersonquotahistory> findById(Integer id);
	public Iterable<Salespersonquotahistory> findAll();
}
