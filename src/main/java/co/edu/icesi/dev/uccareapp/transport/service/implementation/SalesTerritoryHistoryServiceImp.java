package co.edu.icesi.dev.uccareapp.transport.service.implementation;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritoryhistory;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesTerritoryHistoryRepository;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesTerritoryHistoryService;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesTerritoryService;

public class SalesTerritoryHistoryServiceImp implements SalesTerritoryHistoryService {
	
	private SalesTerritoryHistoryRepository<Salesterritoryhistory, Integer> salesTerritoryHistoryRepository;
	
	public SalesTerritoryHistoryServiceImp(SalesTerritoryHistoryRepository<Salesterritoryhistory, Integer> sthr) {
		salesTerritoryHistoryRepository = sthr;
	}

	@Override
	public void add(Salesterritoryhistory salesTerritoryHistory) throws InvalidValueException {
		if(salesTerritoryHistory.getEnddate().compareTo(Timestamp.valueOf(LocalDateTime.now()))>0) {
			throw new InvalidValueException("The end date have to be lower than the current date");
		}
		
		if(salesTerritoryHistory.getModifieddate().compareTo(salesTerritoryHistory.getEnddate())>=0) {
			throw new InvalidValueException("The moddified data no should be equals or higgier to the end data");
		}
		
		
	}

	@Override
	public void edit(Salesterritoryhistory salesTerritoryHistory) throws InvalidValueException {
		if(salesTerritoryHistory.getEnddate().compareTo(Timestamp.valueOf(LocalDateTime.now()))>0) {
			throw new InvalidValueException("The end date have to be lower than the current date");
		}
		
		if(salesTerritoryHistory.getModifieddate().compareTo(salesTerritoryHistory.getEnddate())>=0) {
			throw new InvalidValueException("The moddified data no should be equals or higgier to the end data");
		}
		
	}

	@Override
	public Optional<Salesterritoryhistory> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Salesterritoryhistory> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
