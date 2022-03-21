package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static org.junit.jupiter.api.Assertions.assertThrows;


import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.NumericInvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.demo.Taller1ShApplication;
import co.edu.icesi.dev.uccareapp.transport.model.person.Businessentity;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesperson;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesPersonRepository;
import co.edu.icesi.dev.uccareapp.transport.service.implementation.SalesPersonServiceImp;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes= Taller1ShApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
class Taller1ShApplicationUnitTests {
	
	
	private SalesPersonServiceImp salesPersonService;
	
	@Mock
	private Businessentity businessEntity;
	@Mock
	private Salesterritory salesTerritory;
	
	@Mock
	private SalesPersonRepository<Salesperson,Integer> salesPersonRepository;

//	@BeforeAll
//	void loadMocks() {
//		doReturn(4321).when(salesTerritory).getTerritoryid();
//		doReturn(1234).when(businessEntity).getBusinessentityid();
//	}
	
	@BeforeEach
	void loadService() {
		salesPersonService = new SalesPersonServiceImp(salesPersonRepository);
	}
	@Test
	void saveSalesPersonTest() {
		Salesperson personAdd = new Salesperson();
		personAdd.setBusinessentityid(1234);
		personAdd.setSalesterritory(salesTerritory);
		personAdd.setSalesquota(new BigDecimal("-0.9999"));
		personAdd.setCommissionpct(new BigDecimal("0.5"));
		
		assertThrows(NumericInvalidValueException.class, ()->{
			salesPersonService.add(personAdd);
		});
		
		personAdd.setSalesquota(new BigDecimal("99999.99999"));
		personAdd.setCommissionpct(new BigDecimal("-0.999"));
		
		assertThrows(NumericInvalidValueException.class, ()->{
			salesPersonService.add(personAdd);
		});
		
		personAdd.setCommissionpct(new BigDecimal("1.001"));
		
		assertThrows(NumericInvalidValueException.class, ()->{
			salesPersonService.add(personAdd);
		});
		
		personAdd.setCommissionpct(new BigDecimal("1"));
		assertDoesNotThrow(()->{
			salesPersonService.add(personAdd);
		});
		personAdd.setCommissionpct(new BigDecimal("0"));
		assertDoesNotThrow(()->{
			salesPersonService.add(personAdd);
		});
	}
	
	@Test
	void editSalesPersonTest() {
		Salesperson personEdit = new Salesperson();
		personEdit.setBusinessentityid(1234);
		personEdit.setSalesterritory(salesTerritory);
		personEdit.setSalesquota(new BigDecimal("-0.9999"));
		personEdit.setCommissionpct(new BigDecimal("0.5"));
		
		assertThrows(NumericInvalidValueException.class, ()->{
			salesPersonService.edit(personEdit);
		});
		
		personEdit.setSalesquota(new BigDecimal("99999.99999"));
		personEdit.setCommissionpct(new BigDecimal("-0.999"));
		
		assertThrows(NumericInvalidValueException.class, ()->{
			salesPersonService.edit(personEdit);
		});
		
		personEdit.setCommissionpct(new BigDecimal("1.001"));
		
		assertThrows(NumericInvalidValueException.class, ()->{
			salesPersonService.edit(personEdit);
		});
		
		personEdit.setCommissionpct(new BigDecimal("1"));
		assertDoesNotThrow(()->{
			salesPersonService.add(personEdit);
		});
		
		personEdit.setCommissionpct(new BigDecimal("0"));
		assertDoesNotThrow(()->{
			salesPersonService.add(personEdit);
		});
	}

}
