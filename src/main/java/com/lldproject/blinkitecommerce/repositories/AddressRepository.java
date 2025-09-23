package com.lldproject.blinkitecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lldproject.blinkitecommerce.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
