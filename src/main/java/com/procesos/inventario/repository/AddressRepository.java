package com.procesos.inventario.repository;

import com.procesos.inventario.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,Long> {
}
