package com.demoapi.demorestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoapi.demorestfulapi.entities.Product;

public interface ProductRespository extends JpaRepository<Product, Long> {

}
