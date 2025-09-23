package com.lldproject.blinkitecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lldproject.blinkitecommerce.models.HighDemandProduct;

@Repository
public interface HighDemandProductRepository extends JpaRepository<HighDemandProduct, Integer> {

    Optional<HighDemandProduct> findByProduct_Id(int requiredProductId);

}
