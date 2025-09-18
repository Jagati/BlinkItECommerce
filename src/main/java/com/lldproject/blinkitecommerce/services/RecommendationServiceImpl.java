package com.lldproject.blinkitecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lldproject.blinkitecommerce.exceptions.ProductNotFoundException;
import com.lldproject.blinkitecommerce.models.Product;
import com.lldproject.blinkitecommerce.models.ProductGroup;
import com.lldproject.blinkitecommerce.repositories.ProductGroupsRepository;
import com.lldproject.blinkitecommerce.repositories.ProductRepository;

@Service
public class RecommendationServiceImpl implements RecommendationsService {
    private final ProductGroupsRepository productGroupsRepository;
    private final ProductRepository productRepository;

    @Autowired
    public RecommendationServiceImpl(ProductGroupsRepository productGroupsRepository, ProductRepository productRepository){
        this.productGroupsRepository=productGroupsRepository;
        this.productRepository=productRepository;
    }

    @Override
    public List<Product> getRecommendations(int productId) throws ProductNotFoundException{
        Optional<Product> productOp = productRepository.findById(productId);
        if(productOp.isEmpty()){
            throw new ProductNotFoundException("product not found");
        }
        Product product = productOp.get();
        List<ProductGroup> productGroups = productGroupsRepository.findByProductsIn(List.of(product));
        List<Product> recommendedProducts = new ArrayList<>();
        for(ProductGroup group: productGroups){
            List<Product> products = group.getProducts();
            for(Product prod : products){
                //Check if list already contains the recommended product and it is not the same as the purchased product
                if(!recommendedProducts.contains(prod) && !prod.equals(product)){
                    recommendedProducts.add(prod);
                }
            }

        }
        return recommendedProducts;
    }
}
