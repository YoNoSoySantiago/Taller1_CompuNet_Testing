package co.edu.icesi.dev.uccareapp.transport.tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.demo.Taller1ShApplication;
import co.edu.icesi.dev.uccareapp.transport.model.person.Businessentity;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesperson;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salespersonquotahistory;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritoryhistory;
import co.edu.icesi.dev.uccareapp.transport.repository.BusinessentityRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.CountryRegionRepository;
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

@SpringBootTest(classes= Taller1ShApplication.class)
@MockitoSettings(strictness = Strictness.LENIENT)
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
	private SalesPersonRepository salesPersonRepository;
	@Mock
	private SalesPersonQuotaHistoryRepository salesPersonQuotaHistoryRepository;
	@Mock
	private SalesTerritoryRepository salesTerritoryRepository;
	@Mock
	private CountryRegionRepository countryRegionRepository;
	@Mock
	private SalesTerritoryHistoryRepository salesTerritoryHistoryRepository;
	@Mock
	private BusinessentityRepository businessentityRepository;
	
//	@BeforeAll
//	void loadMocks() {
//		doReturn(4321).when(salesTerritory).getTerritoryid();
//		doReturn(1234).when(businessEntity).getBusinessentityid();
//	}
	void setUpIdValues(){
		Optional<Salesperson> opPerson =  Optional.of(new Salesperson());
		Optional<Salesterritory> opTerritory = Optional.of(new Salesterritory());
		Optional<Salespersonquotahistory> opPersonHistory = Optional.of(new Salespersonquotahistory());
		Optional<Salesterritoryhistory> opTerriotyHistory = Optional.of(new Salesterritoryhistory());
		
		when(salesPersonRepository.findById(1234)).thenReturn(opPerson);
		when(salesTerritoryRepository.findById(4321)).thenReturn(opTerritory);
		when(salesPersonQuotaHistoryRepository.findById(1234)).thenReturn(opPersonHistory);
		when(salesTerritoryHistoryRepository.findById(1234)).thenReturn(opTerriotyHistory);
	}
	void setUpEmptyIdValues(){
		when(salesPersonRepository.findById(1234)).thenReturn(Optional.empty());
		when(salesTerritoryRepository.findById(4321)).thenReturn(Optional.empty());
		when(salesPersonQuotaHistoryRepository.findById(1234)).thenReturn(Optional.empty());
		when(salesTerritoryHistoryRepository.findById(1234)).thenReturn(Optional.empty());
		
	}

	
	@BeforeEach
	void loadService() {
		salesPersonService = new SalesPersonServiceImp(salesPersonRepository,businessentityRepository,salesTerritoryRepository);
		salesPersonQuotaHistoryService = new SalesPersonQuotaHistoryServiceImp(salesPersonQuotaHistoryRepository);
		salesTerritoryService = new SalesTerritoryServiceImp(salesTerritoryRepository,countryRegionRepository);
		salesTerritoryHistoryService = new SalesTerritoryHistoryServiceImp(salesTerritoryHistoryRepository, salesTerritoryRepository);
		Optional<Countryregion> opCountry = Optional.of(new Countryregion());
		when(countryRegionRepository.findById("COL")).thenReturn(opCountry);
		when(salesTerritory.getName()).thenReturn("TR-SH");
		when(salesTerritory.getCountryregioncode()).thenReturn("COL");
		when(salesTerritory.getTerritoryid()).thenReturn(4321);
	}
	@Test
	void saveSalesPersonTest() {
		setUpEmptyIdValues();
		Salesperson person = new Salesperson();
		person.setBusinessentityid(1234);
		person.setSalesterritory(salesTerritory);
		person.setSalesquota(new BigDecimal("-0.9999"));
		person.setCommissionpct(new BigDecimal("0.5"));
		
		assertThrows(InvalidValueException.class, ()->{
			salesPersonService.add(person);
		});
		
		person.setSalesquota(new BigDecimal("99999.99999"));
		person.setCommissionpct(new BigDecimal("-0.999"));
		
		assertThrows(InvalidValueException.class, ()->{
			salesPersonService.add(person);
		});
		
		person.setCommissionpct(new BigDecimal("1.001"));
		
		assertThrows(InvalidValueException.class, ()->{
			salesPersonService.add(person);
		});
		
		person.setCommissionpct(new BigDecimal("1"));
		assertDoesNotThrow(()->{
			salesPersonService.add(person);
		});
		person.setCommissionpct(new BigDecimal("0"));
		assertDoesNotThrow(()->{
			salesPersonService.add(person);
		});
		
		person.setBusinessentityid(null);
		assertThrows(NullPointerException.class, ()->{
			salesPersonService.add(person);
		});
		
		person.setSalesterritory(null);
		assertThrows(NullPointerException.class, ()->{
			salesPersonService.add(person);
		});
		
		person.setSalesquota(null);
		assertThrows(NullPointerException.class, ()->{
			salesPersonService.add(person);
		});
		
		person.setCommissionpct(null);
		assertThrows(NullPointerException.class, ()->{
			salesPersonService.add(person);
		});
	}
	
	
	@Test
	void editSalesPersonTest() {
		setUpIdValues();
		Salesperson person = new Salesperson();
		person.setBusinessentityid(1234);
		person.setSalesterritory(salesTerritory);
		person.setSalesquota(new BigDecimal("-0.9999"));
		person.setCommissionpct(new BigDecimal("0.5"));
		
		assertThrows(InvalidValueException.class, ()->{
			salesPersonService.edit(person);
		});
		
		person.setSalesquota(new BigDecimal("99999.99999"));
		person.setCommissionpct(new BigDecimal("-0.999"));
		
		assertThrows(InvalidValueException.class, ()->{
			salesPersonService.edit(person);
		});
		
		person.setCommissionpct(new BigDecimal("1.001"));
		
		assertThrows(InvalidValueException.class, ()->{
			salesPersonService.edit(person);
		});
		
		person.setCommissionpct(new BigDecimal("1"));
		assertDoesNotThrow(()->{
			salesPersonService.edit(person);
		});
		
		person.setCommissionpct(new BigDecimal("0"));
		assertDoesNotThrow(()->{
			salesPersonService.edit(person);
		});
		
		person.setBusinessentityid(null);
		assertThrows(NullPointerException.class, ()->{
			salesPersonService.edit(person);
		});
		
		person.setSalesterritory(null);
		assertThrows(NullPointerException.class, ()->{
			salesPersonService.edit(person);
		});
		
		person.setSalesquota(null);
		assertThrows(NullPointerException.class, ()->{
			salesPersonService.edit(person);
		});
		
		person.setCommissionpct(null);
		assertThrows(NullPointerException.class, ()->{
			salesPersonService.edit(person);
		});
	}
	
	@Test
	void saveSalesQuotaHistoryTest() {
		setUpEmptyIdValues();
		Salespersonquotahistory salesQuota = new Salespersonquotahistory();
		salesQuota.setId(1234);
		salesQuota.setModifieddate(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
		salesQuota.setSalesquota(BigDecimal.ZERO);
		assertThrows(InvalidValueException.class, ()->{
			salesPersonQuotaHistoryService.add(salesQuota);
		});
		salesQuota.setModifieddate(Timestamp.valueOf(LocalDateTime.now()));
		salesQuota.setSalesquota(new BigDecimal("-0.99"));
		assertThrows(InvalidValueException.class, ()->{
			salesPersonQuotaHistoryService.add(salesQuota);
		});
		
		salesQuota.setSalesquota(new BigDecimal("9999.9999"));
		assertDoesNotThrow(()->{
			salesPersonQuotaHistoryService.add(salesQuota);
		});
		
		salesQuota.setSalesquota(BigDecimal.ZERO);
		assertDoesNotThrow(()->{
			salesPersonQuotaHistoryService.add(salesQuota);
		});
		
		salesQuota.setId(null);
		assertThrows(NullPointerException.class, ()->{
			salesPersonQuotaHistoryService.add(salesQuota);
		});
		
		salesQuota.setModifieddate(null);
		assertThrows(NullPointerException.class, ()->{
			salesPersonQuotaHistoryService.add(salesQuota);
		});
		
		salesQuota.setSalesquota(null);
		assertThrows(NullPointerException.class, ()->{
			salesPersonQuotaHistoryService.add(salesQuota);
		});
	}
	
	@Test
	void editSalesQuotaHistoryTest() {
		setUpIdValues();
		Salespersonquotahistory salesQuota = new Salespersonquotahistory();
		salesQuota.setId(1234);
		salesQuota.setModifieddate(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
		salesQuota.setSalesquota(BigDecimal.ZERO);
		assertThrows(InvalidValueException.class, ()->{
			salesPersonQuotaHistoryService.edit(salesQuota);
		});
		salesQuota.setModifieddate(Timestamp.valueOf(LocalDateTime.now()));
		salesQuota.setSalesquota(new BigDecimal("-0.99"));
		assertThrows(InvalidValueException.class, ()->{
			salesPersonQuotaHistoryService.edit(salesQuota);
		});
		
		salesQuota.setSalesquota(new BigDecimal("9999.9999"));
		assertDoesNotThrow(()->{
			salesPersonQuotaHistoryService.edit(salesQuota);
		});
		
		salesQuota.setSalesquota(BigDecimal.ZERO);
		assertDoesNotThrow(()->{
			salesPersonQuotaHistoryService.edit(salesQuota);
		});
		
		salesQuota.setId(null);
		assertThrows(NullPointerException.class, ()->{
			salesPersonQuotaHistoryService.edit(salesQuota);
		});
		
		salesQuota.setModifieddate(null);
		assertThrows(NullPointerException.class, ()->{
			salesPersonQuotaHistoryService.edit(salesQuota);
		});
		
		salesQuota.setSalesquota(null);
		assertThrows(NullPointerException.class, ()->{
			salesPersonQuotaHistoryService.edit(salesQuota);
		});
	}
	
	@Test
	void addSalesTerritoryTest() {
		Salesterritory salesTerritory = new Salesterritory();
		salesTerritory.setName("TRSH");
		salesTerritory.setCountryregioncode("COL");
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryService.add(salesTerritory);
		});
		
		salesTerritory.setName("TR-SH");
		assertDoesNotThrow(()->{
			salesTerritoryService.add(salesTerritory);
		});
		
		salesTerritory.setName("TR-SHS");
		assertDoesNotThrow(()->{
			salesTerritoryService.add(salesTerritory);
		});
		
		salesTerritory.setName(null);
		assertThrows(NullPointerException.class,()->{
			salesTerritoryService.add(salesTerritory);
		});
		
		salesTerritory.setCountryregioncode(null);
		assertThrows(NullPointerException.class,()->{
			salesTerritoryService.add(salesTerritory);
		});
	}
	
	@Test
	void editSalesTerritoryTest() {
		Salesterritory salesTerritory = new Salesterritory();
		salesTerritory.setName("TRSH");
		salesTerritory.setCountryregioncode("COL");
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryService.edit(salesTerritory);
		});
		
		salesTerritory.setName("TR-SH");
		assertDoesNotThrow(()->{
			salesTerritoryService.edit(salesTerritory);
		});
		
		salesTerritory.setName("TR-SHS");
		assertDoesNotThrow(()->{
			salesTerritoryService.edit(salesTerritory);
		});
		
		salesTerritory.setName(null);
		assertThrows(NullPointerException.class,()->{
			salesTerritoryService.edit(salesTerritory);
		});
		
		salesTerritory.setCountryregioncode(null);
		assertThrows(NullPointerException.class,()->{
			salesTerritoryService.edit(salesTerritory);
		});
	}
	
	@Test
	void addSalesTerritoryHistoryTest() {
		setUpEmptyIdValues();
		Optional<Salesterritory> opTerritory = Optional.of(new Salesterritory());
		when(salesTerritoryRepository.findById(4321)).thenReturn(opTerritory);
		Salesterritoryhistory salesTerritoryHistory = new Salesterritoryhistory();
		salesTerritoryHistory.setId(1234);
		salesTerritoryHistory.setSalesterritory(salesTerritory);
		salesTerritoryHistory.setEnddate(Timestamp.valueOf(LocalDateTime.now().minusDays(2)));
		salesTerritoryHistory.setModifieddate(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
		
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryHistoryService.add(salesTerritoryHistory);
		});
		
		salesTerritoryHistory.setEnddate(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryHistoryService.add(salesTerritoryHistory);
		});
		
		salesTerritoryHistory.setEnddate(Timestamp.valueOf(LocalDateTime.now()));
		salesTerritoryHistory.setModifieddate(Timestamp.valueOf(LocalDateTime.now()));
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryHistoryService.add(salesTerritoryHistory);
		});

		salesTerritoryHistory.setModifieddate(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
		assertDoesNotThrow(()->{
			salesTerritoryHistoryService.add(salesTerritoryHistory);
		});
		
		salesTerritoryHistory.setId(null);
		assertThrows(NullPointerException.class,()->{
			salesTerritoryHistoryService.add(salesTerritoryHistory);
		});
		
		salesTerritoryHistory.setSalesterritory(null);
		assertThrows(NullPointerException.class,()->{
			salesTerritoryHistoryService.add(salesTerritoryHistory);
		});
		
		salesTerritoryHistory.setEnddate(null);
		assertThrows(NullPointerException.class,()->{
			salesTerritoryHistoryService.add(salesTerritoryHistory);
		});
		
		salesTerritoryHistory.setModifieddate(null);
		assertThrows(NullPointerException.class,()->{
			salesTerritoryHistoryService.add(salesTerritoryHistory);
		});
	}
	
	@Test
	void editSalesTerritoryHistoryTest() {
		setUpIdValues();
		Salesterritoryhistory salesTerritoryHistory = new Salesterritoryhistory();
		salesTerritoryHistory.setId(1234);
		salesTerritoryHistory.setSalesterritory(salesTerritory);
		salesTerritoryHistory.setEnddate(Timestamp.valueOf(LocalDateTime.now().minusDays(2)));
		salesTerritoryHistory.setModifieddate(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
		
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryHistoryService.edit(salesTerritoryHistory);
		});
		
		salesTerritoryHistory.setEnddate(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryHistoryService.edit(salesTerritoryHistory);
		});
		
		salesTerritoryHistory.setEnddate(Timestamp.valueOf(LocalDateTime.now()));
		salesTerritoryHistory.setModifieddate(Timestamp.valueOf(LocalDateTime.now()));
		assertThrows(InvalidValueException.class,()->{
			salesTerritoryHistoryService.edit(salesTerritoryHistory);
		});
		

		salesTerritoryHistory.setModifieddate(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
		assertDoesNotThrow(()->{
			salesTerritoryHistoryService.edit(salesTerritoryHistory);
		});
		
		salesTerritoryHistory.setId(null);
		assertThrows(NullPointerException.class,()->{
			salesTerritoryHistoryService.edit(salesTerritoryHistory);
		});
		
		salesTerritoryHistory.setSalesterritory(null);
		assertThrows(NullPointerException.class,()->{
			salesTerritoryHistoryService.edit(salesTerritoryHistory);
		});
		
		salesTerritoryHistory.setEnddate(null);
		assertThrows(NullPointerException.class,()->{
			salesTerritoryHistoryService.edit(salesTerritoryHistory);
		});
		
		salesTerritoryHistory.setModifieddate(null);
		assertThrows(NullPointerException.class,()->{
			salesTerritoryHistoryService.edit(salesTerritoryHistory);
		});
	}
}
