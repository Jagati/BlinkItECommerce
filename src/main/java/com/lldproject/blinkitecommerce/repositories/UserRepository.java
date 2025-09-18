package com.lldproject.blinkitecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lldproject.blinkitecommerce.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

}
