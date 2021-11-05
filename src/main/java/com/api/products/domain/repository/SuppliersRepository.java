package com.api.products.domain.repository;

import com.api.products.domain.entity.Suppliers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersRepository extends CrudRepository<Suppliers, Long> {

}
