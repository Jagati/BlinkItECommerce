package com.lldproject.blinkitecommerce.repositories;

import com.lldproject.blinkitecommerce.models.Partner;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    List<Partner> findByIdIn(List<Long> partnerIds);

}
