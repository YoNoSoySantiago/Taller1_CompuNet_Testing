package co.edu.icesi.dev.uccareapp.transport.service.interfaces;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salespersonquotahistory;

public interface SalesPersonQuotaHistoryService {
	
	public void add(Salespersonquotahistory salesPersonQuotaHistoryService) throws InvalidValueException;
	public void edit(Salespersonquotahistory salesPersonQuotaHistoryService) throws InvalidValueException;

	public Optional<Salespersonquotahistory> findById(Integer id);
	public Iterable<Salespersonquotahistory> findAll();
}
