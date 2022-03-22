package co.edu.icesi.dev.uccareapp.transport.service.implementation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salespersonquotahistory;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesPersonQuotaHistoryRepository;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesPersonQuotaHistoryService;

public class SalesPersonQuotaHistoryServiceImp implements SalesPersonQuotaHistoryService {
	
	private SalesPersonQuotaHistoryRepository<Salespersonquotahistory,Integer> salesPersonQuotaHistoryRepository;
	
	public SalesPersonQuotaHistoryServiceImp(SalesPersonQuotaHistoryRepository<Salespersonquotahistory, Integer> spqhr) {
		this.salesPersonQuotaHistoryRepository = spqhr;
	}

	@Override
	public void add(Salespersonquotahistory salesPersonQuotaHistory) throws InvalidValueException {
		if(salesPersonQuotaHistory.getModifieddate().compareTo(Timestamp.valueOf(LocalDateTime.now()))>0) {
			throw new InvalidValueException("The quota date must to be lower than the current date");
		}
		if(salesPersonQuotaHistory.getSalesquota().compareTo(BigDecimal.ZERO)<0) {
			throw new InvalidValueException("The sales quota must to be a positive number");
		}
	}

	@Override
	public void edit(Salespersonquotahistory salesPersonQuotaHistory) throws InvalidValueException {
		if(salesPersonQuotaHistory.getModifieddate().compareTo(Timestamp.valueOf(LocalDateTime.now()))>0) {
			throw new InvalidValueException("The quota date must to be lower than the current date");
		}
		if(salesPersonQuotaHistory.getSalesquota().compareTo(BigDecimal.ZERO)<0) {
			throw new InvalidValueException("The sales quota must to be a positive number");
		}
	}

	@Override
	public Optional<Salespersonquotahistory> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Salespersonquotahistory> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
