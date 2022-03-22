package co.edu.icesi.dev.uccareapp.transport.service.implementation;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesperson;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesPersonRepository;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesPersonService;

@Service
public class SalesPersonServiceImp implements SalesPersonService {
	
	private SalesPersonRepository<Salesperson, Integer> salesPersonRepository;
	
	public SalesPersonServiceImp(SalesPersonRepository<Salesperson, Integer> spr) {
		this.salesPersonRepository = spr;
	}

	@Override
	public void add(Salesperson salesPerson) throws InvalidValueException {
		Optional<Salesperson> person = findById(salesPerson.getBusinessentityid());
		if(person.isEmpty()) {
			if(salesPerson.getSalesquota().compareTo(BigDecimal.ZERO)<0) {
				throw new InvalidValueException();
			}
			if(salesPerson.getCommissionpct().compareTo(BigDecimal.ZERO)<0) {
				throw new InvalidValueException();
			}
			if(salesPerson.getCommissionpct().compareTo(BigDecimal.ONE)>0) {
				throw new InvalidValueException();
			}
		}
		
	}

	@Override
	public void edit(Salesperson salesPerson) throws InvalidValueException{
		if(salesPerson.getSalesquota().compareTo(BigDecimal.ZERO)<0) {
			throw new InvalidValueException();
		}
		if(salesPerson.getCommissionpct().compareTo(BigDecimal.ZERO)<0) {
			throw new InvalidValueException();
		}
		if(salesPerson.getCommissionpct().compareTo(BigDecimal.ONE)>0) {
			throw new InvalidValueException();
		}
	}

	@Override
	public Optional<Salesperson> findById(Integer id) {
	
		return this.salesPersonRepository.findById(id);
	}
	
	@Override
	public Iterable<Salesperson> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
