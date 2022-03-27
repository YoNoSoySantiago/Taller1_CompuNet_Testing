package co.edu.icesi.dev.uccareapp.transport.service.implementation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectAlreadyExistException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectDoesNotExistException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salespersonquotahistory;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesPersonQuotaHistoryRepository;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesPersonQuotaHistoryService;
@Service
public class SalesPersonQuotaHistoryServiceImp implements SalesPersonQuotaHistoryService {
	
	private SalesPersonQuotaHistoryRepository salesPersonQuotaHistoryRepository;
	
	@Autowired
	public SalesPersonQuotaHistoryServiceImp(SalesPersonQuotaHistoryRepository spqhr) {
		this.salesPersonQuotaHistoryRepository = spqhr;
	}

	@Override
	public void add(Salespersonquotahistory salesPersonQuotaHistory) throws InvalidValueException, ObjectAlreadyExistException {
		if(
			salesPersonQuotaHistory.getId()==null||
			salesPersonQuotaHistory.getModifieddate()==null||
			salesPersonQuotaHistory.getSalesquota() == null) {
				
				throw new NullPointerException("Values empties or null");
		}
		Optional<Salespersonquotahistory> quotaHistory = findById(salesPersonQuotaHistory.getId());
		if(quotaHistory.isEmpty()) {
			if(salesPersonQuotaHistory.getModifieddate().compareTo(Timestamp.valueOf(LocalDateTime.now()))>0) {
				throw new InvalidValueException("The quota date must to be lower than the current date");
			}
			if(salesPersonQuotaHistory.getSalesquota().compareTo(BigDecimal.ZERO)<0) {
				throw new InvalidValueException("The sales quota must to be a positive number");
			}
		}else {
			throw new ObjectAlreadyExistException("This id already exist");
		}
	}

	@Override
	public void edit(Salespersonquotahistory salesPersonQuotaHistory) throws InvalidValueException, ObjectDoesNotExistException {
		if(
				salesPersonQuotaHistory.getId()==null||
				salesPersonQuotaHistory.getModifieddate()==null||
				salesPersonQuotaHistory.getSalesquota() == null) {
					
					throw new NullPointerException("Values empties or null");
			}
		Optional<Salespersonquotahistory> quotaHistory = findById(salesPersonQuotaHistory.getId());
		if(!quotaHistory.isEmpty()) {
			if(salesPersonQuotaHistory.getModifieddate().compareTo(Timestamp.valueOf(LocalDateTime.now()))>0) {
				throw new InvalidValueException("The quota date must to be lower than the current date");
			}
			if(salesPersonQuotaHistory.getSalesquota().compareTo(BigDecimal.ZERO)<0) {
				throw new InvalidValueException("The sales quota must to be a positive number");
			}
		}else {
			throw new ObjectDoesNotExistException("This id does not exist");
		}
	}

	@Override
	public Optional<Salespersonquotahistory> findById(Integer id) {

		return this.salesPersonQuotaHistoryRepository.findById(id);
	}

	@Override
	public Iterable<Salespersonquotahistory> findAll() {
		
		return this.salesPersonQuotaHistoryRepository.findAll();
	}

}
