package co.edu.icesi.dev.uccareapp.transport.service.interfaces;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectAlreadyExistException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectDoesNotExistException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesperson;

public interface SalesPersonService {
	
	public void add(Salesperson salesPerson) throws InvalidValueException, ObjectAlreadyExistException;
	public void edit(Salesperson salesPerson) throws InvalidValueException, ObjectDoesNotExistException ;

	public Optional<Salesperson> findById(Integer id);
	public Iterable<Salesperson> findAll();
}
