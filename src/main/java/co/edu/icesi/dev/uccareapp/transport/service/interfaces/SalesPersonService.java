package co.edu.icesi.dev.uccareapp.transport.service.interfaces;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.NumericInvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesperson;


public interface SalesPersonService {
	
	public void add(Salesperson salesPerson) throws NumericInvalidValueException;
	public void edit(Salesperson salesPerson) throws NumericInvalidValueException;
	

	public Optional<Salesperson> findById(Integer id);
	public Iterable<Salesperson> findAll();
}
