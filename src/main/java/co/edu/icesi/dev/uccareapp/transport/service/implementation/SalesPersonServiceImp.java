package co.edu.icesi.dev.uccareapp.transport.service.implementation;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectAlreadyExistException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectDoesNotExistException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesperson;
import co.edu.icesi.dev.uccareapp.transport.repository.BusinessentityRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesPersonRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesTerritoryRepository;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesPersonService;

@Service
public class SalesPersonServiceImp implements SalesPersonService {
	
	private SalesPersonRepository salesPersonRepository;
	private BusinessentityRepository businessentityRepository;
	private SalesTerritoryRepository salesTerritoryRepository;
	
	@Autowired
	public SalesPersonServiceImp(SalesPersonRepository spr,
			BusinessentityRepository br,
			SalesTerritoryRepository str) {
		this.salesPersonRepository = spr;
		this.businessentityRepository = br;
		this.salesTerritoryRepository = str;
	}

	@Override
	public void add(Salesperson salesPerson) throws InvalidValueException, ObjectAlreadyExistException {
		if(salesPerson.getBusinessentityid()==null||
		salesPerson.getSalesterritory()==null||
		salesPerson.getSalesquota() == null ||
		salesPerson.getCommissionpct() == null) {
			throw new NullPointerException("Empty values or nulls");
		}
		
		Optional<Salesperson> person = findById(salesPerson.getBusinessentityid());
		if(person.isEmpty()) {
			if(salesPerson.getSalesquota().compareTo(BigDecimal.ZERO)<0) {
				throw new InvalidValueException("sales quota must be a positive number");
			}
			if(salesPerson.getCommissionpct().compareTo(BigDecimal.ZERO)<0) {
				throw new InvalidValueException("commission must to be between 0 and 1");
			}
			if(salesPerson.getCommissionpct().compareTo(BigDecimal.ONE)>0) {
				throw new InvalidValueException("commission must to be between 0 and 1");
			}
		}else {
			throw new ObjectAlreadyExistException("this id already exist");
		}
		
	}

	@Override
	public void edit(Salesperson salesPerson) throws InvalidValueException, ObjectDoesNotExistException{
		if(salesPerson.getBusinessentityid()==null||
		salesPerson.getSalesterritory()==null||
		salesPerson.getSalesquota() == null ||
		salesPerson.getCommissionpct() == null) {
			throw new NullPointerException("Empty values or nulls");
		}
		Optional<Salesperson> person = findById(1234);
		System.out.println(person);
		if(!person.isEmpty()) {
			if(salesPerson.getSalesquota().compareTo(BigDecimal.ZERO)<0) {
				throw new InvalidValueException("sales quota must be a positive number");
			}
			if(salesPerson.getCommissionpct().compareTo(BigDecimal.ZERO)<0) {
				throw new InvalidValueException("commission must to be between 0 and 1");
			}
			if(salesPerson.getCommissionpct().compareTo(BigDecimal.ONE)>0) {
				throw new InvalidValueException("commission must to be between 0 and 1");
			}
		}else {
			throw new ObjectDoesNotExistException("this id does not exist");
		}
	}

	@Override
	public Optional<Salesperson> findById(Integer id) {
		return this.salesPersonRepository.findById(id);
	}
	
	@Override
	public Iterable<Salesperson> findAll() {
		return this.salesPersonRepository.findAll();
	}
}
