package com.demoapi.demorestfulapi.repositories;

import com.demoapi.demorestfulapi.entities.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRespository extends JpaRepository<Order, Long> {
    
}
