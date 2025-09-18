package com.lldproject.blinkitecommerce.services;

import com.lldproject.blinkitecommerce.exceptions.ProductNotFoundException;
import com.lldproject.blinkitecommerce.models.Product;

import java.util.List;

public interface RecommendationsService {

    public List<Product> getRecommendations(int productId) throws ProductNotFoundException;
}
