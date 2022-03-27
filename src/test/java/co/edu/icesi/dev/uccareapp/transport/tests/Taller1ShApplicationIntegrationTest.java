package co.edu.icesi.dev.uccareapp.transport.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import co.edu.icesi.dev.uccareapp.transport.Taller1ShApplication;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectDoesNotExistException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Businessentity;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.BusinessentityService;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.CountryRegionService;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesPersonQuotaHistoryService;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesPersonService;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesTerritoryHistoryService;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesTerritoryService;

@SpringBootTest
@ContextConfiguration(classes = Taller1ShApplication.class)
class Taller1ShApplicationIntegrationTest {
	
	
	private SalesPersonService salesPersonService;
	private SalesPersonQuotaHistoryService salesPersonQuotaHistoryService;
	private SalesTerritoryService salesTerritoryService;
	private SalesTerritoryHistoryService salesTerritoryHistoryService;
	
	private BusinessentityService businessentityService;
	private CountryRegionService countryRegionService;
	
	@Autowired
	public Taller1ShApplicationIntegrationTest(SalesPersonService salesPersonService) {
		this.salesPersonService = salesPersonService;
	}
	

	void setUpInstantiate() {
		Businessentity businessEntity = new Businessentity();
		businessEntity.setBusinessentityid(1234);
		Businessentity businessEntity2 = new Businessentity();
		businessEntity.setBusinessentityid(0123);
		
		businessentityService.add(businessEntity);
		businessentityService.add(businessEntity2);
		
		Countryregion countryRegion = new Countryregion();
		countryRegion.setCountryregioncode("COL");
		Countryregion countryRegion2 = new Countryregion();
		countryRegion.setCountryregioncode("MEX");
		
		countryRegionService.add(countryRegion);
		countryRegionService.add(countryRegion2);
	}
	
	@Test
	void addSalesTerritoryTest() throws InvalidValueException, ObjectDoesNotExistException {
		Salesterritory salesTerritory = new Salesterritory();
		salesTerritory.setTerritoryid(4321);
		
		salesTerritory.setName("TR-SH");
		salesTerritory.setCountryregioncode("ABC");
		this.salesTerritoryService.add(salesTerritory);
		
		assertTrue(salesTerritoryService.findById(4321).isEmpty());
		
		salesTerritory.setCountryregioncode("COL");
		assertTrue(salesTerritoryService.findById(4321).isEmpty());
	}
	
	@Test
	void editSalesTerritoryTest() {
		
	}
	
	@Test
	void addSalesTerritoryHistoryTest() {
		
	}
	
	@Test
	void editSalesTerritoryHistoryTest() {
		
	}
	
//	@Test
//	void saveSalesPersonTest() {
//
//		Salesperson salesPerson = new Salesperson();
//		salesPerson.setBusinessentityid(1234);
//	}
//	
//	
//	@Test
//	void editSalesPersonTest() {
//		
//	}
//	
//	@Test
//	void saveSalesQuotaHistoryTest() {
//		
//	}
//	
//	@Test
//	void editSalesQuotaHistoryTest() {
//		
//	}

}
