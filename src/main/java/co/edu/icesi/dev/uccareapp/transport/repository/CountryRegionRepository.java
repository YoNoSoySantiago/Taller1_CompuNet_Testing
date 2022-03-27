package co.edu.icesi.dev.uccareapp.transport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CountryRegionRepository<T,ID> extends CrudRepository<T,ID> {

}
