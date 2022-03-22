package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static org.junit.jupiter.api.Assertions.assertThrows;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.demo.Taller1ShApplication;
import co.edu.icesi.dev.uccareapp.transport.model.person.Businessentity;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesperson;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salespersonquotahistory;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritoryhistory;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesPersonQuotaHistoryRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesPersonRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesTerritoryHistoryRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesTerritoryRepository;
import co.edu.icesi.dev.uccareapp.transport.service.implementation.SalesPersonQuotaHistoryServiceImp;
import co.edu.icesi.dev.uccareapp.transport.service.implementation.SalesPersonServiceImp;
import co.edu.icesi.dev.uccareapp.transport.service.implementation.SalesTerritoryHistoryServiceImp;
import co.edu.icesi.dev.uccareapp.transport.service.implementation.SalesTerritoryServiceImp;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesPersonQuotaHistoryService;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesPersonService;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesTerritoryHistoryService;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesTerritoryService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes= Taller1ShApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
class Taller1ShApplicationUnitTests {
	
	
	private SalesPersonService salesPersonService;
	private SalesPersonQuotaHistoryService salesPersonQuotaHistoryService;
	private SalesTerritoryService salesTerritoryService;
	private SalesTerritoryHistoryService salesTerritoryHistoryService;
	
	@Mock
	private Businessentity businessEntity;
	@Mock
	private Salesterritory salesTerritory;
	
	@Mock
	private SalesPersonRepository<Salesperson,Integer> salesPersonRepository;
	@Mock
	private SalesPersonQuotaHistoryRepository<Salespersonquotahistory,Integer> salesPersonQuotaHistoryRepository;
	@Mock
	private SalesTerritoryRepository<Salesterritory,Integer> salesTerritoryRepository;
	@Mock
	private SalesTerritoryHistoryRepository<Salesterritoryhistory, Integer> salesTerritoryHistoryRepository;
	
//	@BeforeAll
//	void loadMocks() {
//		doReturn(4321).when(salesTerritory).getTerritoryid();
//		doReturn(1234).when(businessEntity).getBusinessentityid();
//	}
	
	@BeforeEach
	void loadService() {
		salesPersonService = new SalesPersonServiceImp(salesPersonRepository);
		salesPersonQuotaHistoryService = new SalesPersonQuotaHistoryServiceImp(salesPersonQuotaHistoryRepository);
		salesTerritoryService = new SalesTerritoryServiceImp(salesTerritoryRepository);
		salesTerritoryHistoryService = new SalesTerritoryHistoryServiceImp(salesTerritoryHistoryRepository);
	}
	@Test
	void saveSalesPersonTest() {
		Salesperson personAdd = new Salesperson();
		personAdd.setBusinessentityid(1234);
		personAdd.setSalesterritory(salesTerritory);
		personAdd.setSalesquota(new BigDecimal("-0.9999"));
		personAdd.setCommissionpct(new BigDecimal("0.5"));
		
		assertThrows(InvalidValueException.class, ()->{
			salesPersonService.add(personAdd);
		});
		
		personAdd.setSalesquota(new BigDecimal("99999.99999"));
		personAdd.setCommissionpct(new BigDecimal("-0.999"));
		
		assertThrows(InvalidValueException.class, ()->{
			salesPersonService.add(personAdd);
		});
		
		personAdd.setCommissionpct(new BigDecimal("1.001"));
		
		assertThrows(InvalidValueException.class, ()->{
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
		
		assertThrows(InvalidValueException.class, ()->{
			salesPersonService.edit(personEdit);
		});
		
		personEdit.setSalesquota(new BigDecimal("99999.99999"));
		personEdit.setCommissionpct(new BigDecimal("-0.999"));
		
		assertThrows(InvalidValueException.class, ()->{
			salesPersonService.edit(personEdit);
		});
		
		personEdit.setCommissionpct(new BigDecimal("1.001"));
		
		assertThrows(InvalidValueException.class, ()->{
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
	
	@Test
	void saveSalesQuotaHistoryTest() {
		Salespersonquotahistory salesQuotaAdd = new Salespersonquotahistory();
		salesQuotaAdd.setId(1234);
		salesQuotaAdd.setModifieddate(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
		salesQuotaAdd.setSalesquota(BigDecimal.ZERO);
		assertThrows(InvalidValueException.class, ()->{
			salesPersonQuotaHistoryService.add(salesQuotaAdd);
		});
		salesQuotaAdd.setModifieddate(Timestamp.valueOf(LocalDateTime.now()));
		salesQuotaAdd.setSalesquota(new BigDecimal("-0.99"));
		assertThrows(InvalidValueException.class, ()->{
			salesPersonQuotaHistoryService.add(salesQuotaAdd);
		});
		
		salesQuotaAdd.setSalesquota(new BigDecimal("9999.9999"));
		assertDoesNotThrow(()->{
			salesPersonQuotaHistoryService.add(salesQuotaAdd);
		});
		
		salesQuotaAdd.setSalesquota(BigDecimal.ZERO);
		assertDoesNotThrow(()->{
			salesPersonQuotaHistoryService.add(salesQuotaAdd);
		});
	}
	
	@Test
	void editSalesQuotaHistoryTest() {
		Salespersonquotahistory salesQuotaAdd = new Salespersonquotahistory();
		salesQuotaAdd.setId(1234);
		salesQuotaAdd.setModifieddate(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
		salesQuotaAdd.setSalesquota(BigDecimal.ZERO);
		assertThrows(InvalidValueException.class, ()->{
			salesPersonQuotaHistoryService.edit(salesQuotaAdd);
		});
		salesQuotaAdd.setModifieddate(Timestamp.valueOf(LocalDateTime.now()));
		salesQuotaAdd.setSalesquota(new BigDecimal("-0.99"));
		assertThrows(InvalidValueException.class, ()->{
			salesPersonQuotaHistoryService.edit(salesQuotaAdd);
		});
		
		salesQuotaAdd.setSalesquota(new BigDecimal("9999.9999"));
		assertDoesNotThrow(()->{
			salesPersonQuotaHistoryService.edit(salesQuotaAdd);
		});
		
		salesQuotaAdd.setSalesquota(BigDecimal.ZERO);
		assertDoesNotThrow(()->{
			salesPersonQuotaHistoryService.edit(salesQuotaAdd);
		});
	}
	
	@Test
	void addSalesTerritoryTest() {
		Salesterritory salesTerritoryAdd = new Salesterritory();
		salesTerritoryAdd.setName("TRSH");
		salesTerritoryAdd.setCountryregioncode("COL");
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryService.add(salesTerritoryAdd);
		});
		
		salesTerritoryAdd.setName("TR-SH");
		assertDoesNotThrow(()->{
			salesTerritoryService.add(salesTerritoryAdd);
		});
		
		salesTerritoryAdd.setName("TR-SHS");
		assertDoesNotThrow(()->{
			salesTerritoryService.add(salesTerritoryAdd);
		});
	}
	
	@Test
	void editSalesTerritoryTest() {
		Salesterritory salesTerritoryEdit = new Salesterritory();
		salesTerritoryEdit.setName("TRSH");
		salesTerritoryEdit.setCountryregioncode("COL");
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryService.edit(salesTerritoryEdit);
		});
		
		salesTerritoryEdit.setName("TR-SH");
		assertDoesNotThrow(()->{
			salesTerritoryService.edit(salesTerritoryEdit);
		});
		
		salesTerritoryEdit.setName("TR-SHS");
		assertDoesNotThrow(()->{
			salesTerritoryService.edit(salesTerritoryEdit);
		});
	}
	
	@Test
	void addSalesTerritoryHistoryTest() {
		Salesterritoryhistory salesTerritoryHistoryAdd = new Salesterritoryhistory();
		salesTerritoryHistoryAdd.setId(1234);
		salesTerritoryHistoryAdd.setSalesterritory(salesTerritory);
		salesTerritoryHistoryAdd.setEnddate(Timestamp.valueOf(LocalDateTime.now().minusDays(2)));
		salesTerritoryHistoryAdd.setModifieddate(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
		
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryHistoryService.add(salesTerritoryHistoryAdd);
		});
		
		salesTerritoryHistoryAdd.setEnddate(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryHistoryService.add(salesTerritoryHistoryAdd);
		});
		
		salesTerritoryHistoryAdd.setEnddate(Timestamp.valueOf(LocalDateTime.now()));
		salesTerritoryHistoryAdd.setModifieddate(Timestamp.valueOf(LocalDateTime.now()));
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryHistoryService.add(salesTerritoryHistoryAdd);
		});
		

		salesTerritoryHistoryAdd.setModifieddate(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
		assertDoesNotThrow(()->{
			salesTerritoryHistoryService.add(salesTerritoryHistoryAdd);
		});
	}
	
	@Test
	void editSalesTerritoryHistoryTest() {
		Salesterritoryhistory salesTerritoryHistoryEdit = new Salesterritoryhistory();
		salesTerritoryHistoryEdit.setId(1234);
		salesTerritoryHistoryEdit.setSalesterritory(salesTerritory);
		salesTerritoryHistoryEdit.setEnddate(Timestamp.valueOf(LocalDateTime.now().minusDays(2)));
		salesTerritoryHistoryEdit.setModifieddate(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
		
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryHistoryService.edit(salesTerritoryHistoryEdit);
		});
		
		salesTerritoryHistoryEdit.setEnddate(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryHistoryService.edit(salesTerritoryHistoryEdit);
		});
		
		salesTerritoryHistoryEdit.setEnddate(Timestamp.valueOf(LocalDateTime.now()));
		salesTerritoryHistoryEdit.setModifieddate(Timestamp.valueOf(LocalDateTime.now()));
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryHistoryService.edit(salesTerritoryHistoryEdit);
		});
		

		salesTerritoryHistoryEdit.setModifieddate(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
		assertDoesNotThrow(()->{
			salesTerritoryHistoryService.edit(salesTerritoryHistoryEdit);
		});
	}
}
