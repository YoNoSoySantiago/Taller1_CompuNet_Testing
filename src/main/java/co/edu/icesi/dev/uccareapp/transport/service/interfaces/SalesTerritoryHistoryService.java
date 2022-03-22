package co.edu.icesi.dev.uccareapp.transport.service.interfaces;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritoryhistory;

public interface SalesTerritoryHistoryService {

	public void add(Salesterritoryhistory salesTerritoryHistory) throws InvalidValueException;
	public void edit(Salesterritoryhistory salesTerritoryHistory) throws InvalidValueException;

	public Optional<Salesterritoryhistory> findById(Integer id);
	public Iterable<Salesterritoryhistory> findAll();
}
