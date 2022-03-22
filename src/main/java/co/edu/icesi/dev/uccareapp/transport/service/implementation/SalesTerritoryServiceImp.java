package co.edu.icesi.dev.uccareapp.transport.service.implementation;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesPersonRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesTerritoryRepository;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesTerritoryService;

public class SalesTerritoryServiceImp implements SalesTerritoryService {
	
	private SalesTerritoryRepository<Salesterritory, Integer> salesTerritoryRespository;
	
	public SalesTerritoryServiceImp(SalesTerritoryRepository<Salesterritory, Integer> str) {
		this.salesTerritoryRespository = str;
	}
	@Override
	public void add(Salesterritory salesTerritory) throws InvalidValueException {
		if(salesTerritory.getName().length()<5) {
			throw new InvalidValueException("The lenght of the name must to be al least 5");
		}
	}

	@Override
	public void edit(Salesterritory salesTerritory) throws InvalidValueException {
		if(salesTerritory.getName().length()<5) {
			throw new InvalidValueException("The lenght of the name must to be al least 5");
		}
	}

	@Override
	public Optional<Salesterritory> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Salesterritory> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
