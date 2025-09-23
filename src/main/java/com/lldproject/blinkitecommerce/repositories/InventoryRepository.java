package com.lldproject.blinkitecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lldproject.blinkitecommerce.models.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

    Optional<Inventory> findByProduct_Id(int requiredProductId);


}
