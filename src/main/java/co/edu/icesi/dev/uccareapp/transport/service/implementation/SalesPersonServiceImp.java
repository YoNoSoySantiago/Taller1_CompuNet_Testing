package co.edu.icesi.dev.uccareapp.transport.service.implementation;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.NumericInvalidValueException;
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
	public Optional<Salesperson> findById(Integer id) {
	
		return this.salesPersonRepository.findById(id);
	}

	@Override
	public void add(Salesperson salesPerson) throws NumericInvalidValueException {
		Optional<Salesperson> person = findById(salesPerson.getBusinessentityid());
		if(person.isEmpty()) {
			if(salesPerson.getSalesquota().compareTo(BigDecimal.ZERO)<0) {
				throw new NumericInvalidValueException();
			}
			if(salesPerson.getCommissionpct().compareTo(BigDecimal.ZERO)<0) {
				throw new NumericInvalidValueException();
			}
			if(salesPerson.getCommissionpct().compareTo(BigDecimal.ONE)>0) {
				throw new NumericInvalidValueException();
			}
		}
		
	}

	@Override
	public void edit(Salesperson salesPerson) throws NumericInvalidValueException{
		if(salesPerson.getSalesquota().compareTo(BigDecimal.ZERO)<0) {
			throw new NumericInvalidValueException();
		}
		if(salesPerson.getCommissionpct().compareTo(BigDecimal.ZERO)<0) {
			throw new NumericInvalidValueException();
		}
		if(salesPerson.getCommissionpct().compareTo(BigDecimal.ONE)>0) {
			throw new NumericInvalidValueException();
		}
	}

	@Override
	public Iterable<Salesperson> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
