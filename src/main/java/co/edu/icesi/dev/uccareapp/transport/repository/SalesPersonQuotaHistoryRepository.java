package co.edu.icesi.dev.uccareapp.transport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.sales.Salespersonquotahistory;

public interface SalesPersonQuotaHistoryRepository extends CrudRepository<Salespersonquotahistory,Integer> {

}
