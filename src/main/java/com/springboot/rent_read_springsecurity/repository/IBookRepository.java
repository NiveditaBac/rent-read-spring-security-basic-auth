package com.springboot.rent_read_springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.rent_read_springsecurity.entity.Book;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitle(String title);
    
}
