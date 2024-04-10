package com.jainam.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jainam.ecommerce.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
