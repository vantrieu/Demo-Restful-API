package com.demoapi.demorestfulapi.repositories;

import com.demoapi.demorestfulapi.entities.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    
}
