package co.edu.icesi.dev.uccareapp.transport.service.implementation;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectDoesNotExistException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritoryhistory;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesTerritoryHistoryRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesTerritoryRepository;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesTerritoryHistoryService;
@Service
public class SalesTerritoryHistoryServiceImp implements SalesTerritoryHistoryService {
	
	private SalesTerritoryHistoryRepository salesTerritoryHistoryRepository;
	private SalesTerritoryRepository salesTerritoryRepository;
	@Autowired
	public SalesTerritoryHistoryServiceImp(SalesTerritoryHistoryRepository sthr,SalesTerritoryRepository str) {
		salesTerritoryHistoryRepository = sthr;
		salesTerritoryRepository = str;
	}

	@Override
	public void add(Salesterritoryhistory salesTerritoryHistory) throws InvalidValueException, ObjectDoesNotExistException {
		if(
			salesTerritoryHistory.getId()==null||
			salesTerritoryHistory.getSalesterritory()==null||
			salesTerritoryHistory.getEnddate()==null||
			salesTerritoryHistory.getModifieddate()==null){
			
				throw new NullPointerException("Values empties or null");
		}
		if(salesTerritoryHistory.getSalesterritory().getName()==null || salesTerritoryHistory.getSalesterritory().getCountryregioncode()==null) {
			throw new NullPointerException("Values empties or null");
		}
		Optional<Salesterritoryhistory> opTerriHistory =  this.salesTerritoryHistoryRepository.findById(salesTerritoryHistory.getId());
		Optional<Salesterritory> opTerritory = this.salesTerritoryRepository.findById(salesTerritoryHistory.getSalesterritory().getTerritoryid());
		if(opTerritory.isEmpty()) {
			throw new ObjectDoesNotExistException("The territory does not exist");
		}
		
		if(opTerriHistory.isEmpty()) {
			if(salesTerritoryHistory.getEnddate().compareTo(Timestamp.valueOf(LocalDateTime.now()))>0) {
				throw new InvalidValueException("The end date have to be lower than the current date");
			}
			
			if(salesTerritoryHistory.getModifieddate().compareTo(salesTerritoryHistory.getEnddate())>=0) {
				throw new InvalidValueException("The moddified data no should be equals or higgier to the end data");
			}
		}else {
			
		}
		
	}

	@Override
	public void edit(Salesterritoryhistory salesTerritoryHistory) throws InvalidValueException, ObjectDoesNotExistException {
		if(
				salesTerritoryHistory.getId()==null||
				salesTerritoryHistory.getSalesterritory()==null||
				salesTerritoryHistory.getEnddate()==null||
				salesTerritoryHistory.getModifieddate()==null){
				
					throw new NullPointerException("Values empties or null");
		}
		Optional<Salesterritoryhistory> opTerriHistory =  this.salesTerritoryHistoryRepository.findById(salesTerritoryHistory.getId());
		Optional<Salesterritory> opTerritory = this.salesTerritoryRepository.findById(salesTerritoryHistory.getSalesterritory().getTerritoryid());
		if(opTerritory.isEmpty()) {
			throw new ObjectDoesNotExistException("The territory does not exist");
		}
		
		if(!opTerriHistory.isEmpty()) {
			if(salesTerritoryHistory.getEnddate().compareTo(Timestamp.valueOf(LocalDateTime.now()))>0) {
				throw new InvalidValueException("The end date have to be lower than the current date");
			}
			
			if(salesTerritoryHistory.getModifieddate().compareTo(salesTerritoryHistory.getEnddate())>=0) {
				throw new InvalidValueException("The moddified data no should be equals or higgier to the end data");
			}
		}else {
			throw new ObjectDoesNotExistException("This id does not exist");
		}
	}

	@Override
	public Optional<Salesterritoryhistory> findById(Integer id) {
		
		return this.salesTerritoryHistoryRepository.findById(id);
	}

	@Override
	public Iterable<Salesterritoryhistory> findAll() {
		
		return this.salesTerritoryHistoryRepository.findAll();
	}


}
