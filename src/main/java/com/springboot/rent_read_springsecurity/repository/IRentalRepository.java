package com.springboot.rent_read_springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.rent_read_springsecurity.entity.Rental;
import com.springboot.rent_read_springsecurity.entity.User;

@Repository
public interface IRentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByUserAndReturnedAtIsNull(User user);
    
}
