package co.edu.icesi.dev.uccareapp.transport.service.interfaces;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesperson;


public interface SalesPersonService {
	
	public void add(Salesperson salesPerson) throws InvalidValueException;
	public void edit(Salesperson salesPerson) throws InvalidValueException;

	public Optional<Salesperson> findById(Integer id);
	public Iterable<Salesperson> findAll();
}
