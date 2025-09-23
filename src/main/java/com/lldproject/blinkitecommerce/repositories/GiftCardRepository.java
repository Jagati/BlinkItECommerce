package com.lldproject.blinkitecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lldproject.blinkitecommerce.models.GiftCard;

@Repository
public interface GiftCardRepository extends JpaRepository<GiftCard, Integer>{
}
