package com.lldproject.blinkitecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lldproject.blinkitecommerce.models.Product;
import com.lldproject.blinkitecommerce.models.ProductGroup;

@Repository
public interface ProductGroupsRepository extends JpaRepository<ProductGroup, Integer> {

    List<ProductGroup> findByProductsIn(List<Product> list);

}
