package co.edu.icesi.dev.uccareapp.transport.service.interfaces;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.NumericInvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salespersonquotahistory;

public interface SalesPeronQuotaHistoryService {
	public void add(Salespersonquotahistory salesPerson) throws NumericInvalidValueException;
	public void edit(Salespersonquotahistory salesPerson) throws NumericInvalidValueException;
	

	public Optional<Salespersonquotahistory> findById(Integer id);
	public Iterable<Salespersonquotahistory> findAll();
}
