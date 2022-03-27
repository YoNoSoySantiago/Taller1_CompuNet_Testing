package co.edu.icesi.dev.uccareapp.transport.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectDoesNotExistException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.repository.CountryRegionRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesTerritoryRepository;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesTerritoryService;
@Service
public class SalesTerritoryServiceImp implements SalesTerritoryService {
	
	private SalesTerritoryRepository salesTerritoryRespository;
	CountryRegionRepository countryRegionRegpository;
	@Autowired
	public SalesTerritoryServiceImp(SalesTerritoryRepository str,CountryRegionRepository crr) {
		this.salesTerritoryRespository = str;
		this.countryRegionRegpository = crr;
	}
	@Override
	public void add(Salesterritory salesTerritory) throws InvalidValueException, ObjectDoesNotExistException {
		if(
			salesTerritory.getName()==null ||
			salesTerritory.getCountryregioncode()==null
			) {
			throw new NullPointerException("Values empties or null");
		}
		Optional<Countryregion> countryCode = this.countryRegionRegpository.findById(salesTerritory.getCountryregioncode());
		if(!countryCode.isEmpty()) {
			if(salesTerritory.getName().length()<5) {
				throw new InvalidValueException("The lenght of the name must to be al least 5");
			}
		}else {
			throw new ObjectDoesNotExistException("This region code, does not exist");
		}
		
	}

	@Override
	public void edit(Salesterritory salesTerritory) throws InvalidValueException, ObjectDoesNotExistException {
		if(
				salesTerritory.getName()==null ||
				salesTerritory.getCountryregioncode()==null
				) {
				throw new NullPointerException("Values empties or null");
			}
		Optional<Countryregion> countryCode = this.countryRegionRegpository.findById(salesTerritory.getCountryregioncode());
		if(!countryCode.isEmpty()) {
			if(salesTerritory.getName().length()<5) {
				throw new InvalidValueException("The lenght of the name must to be al least 5");
			}
		}else {
			throw new ObjectDoesNotExistException("This region code, does not exist");
		}
	}

	@Override
	public Optional<Salesterritory> findById(Integer id) {
		return this.salesTerritoryRespository.findById(id);
	}

	@Override
	public Iterable<Salesterritory> findAll() {
		return this.salesTerritoryRespository.findAll();
	}

}
