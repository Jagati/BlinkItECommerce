package com.lldproject.blinkitecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lldproject.blinkitecommerce.models.Advertisement;
import com.lldproject.blinkitecommerce.models.Preference;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer>{
    Optional<Advertisement> findByPreference(Preference preference);
}
