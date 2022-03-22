package co.edu.icesi.dev.uccareapp.transport.service.interfaces;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;

public interface SalesTerritoryService {
	
	public void add(Salesterritory salesTerritory) throws InvalidValueException;
	public void edit(Salesterritory salesTerritory) throws InvalidValueException;

	public Optional<Salesterritory> findById(Integer id);
	public Iterable<Salesterritory> findAll();
}
