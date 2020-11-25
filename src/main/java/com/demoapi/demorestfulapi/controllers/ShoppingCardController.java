package com.demoapi.demorestfulapi.controllers;

import java.util.List;
import java.util.Map;

import com.demoapi.demorestfulapi.entities.Order;
import com.demoapi.demorestfulapi.entities.OrderDetail;
import com.demoapi.demorestfulapi.repositories.OrderDetailRepository;
import com.demoapi.demorestfulapi.repositories.OrderRespository;
import com.demoapi.demorestfulapi.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shopping-card")
public class ShoppingCardController {
    @Autowired
    private OrderRespository orderRespository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository UserRepository;

    @PostMapping("/create")
    public String createShoppngCard(@Validated @RequestBody List<OrderDetail> orderDetails, @RequestHeader("username") String username) {
        Order order = new Order();
        order.setUserid(UserRepository.findByUsername(username).getId());
        order = orderRespository.save(order);
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrderid(order.getId());
            orderDetailRepository.save(orderDetail);
        }
        return "create order success!";
    }
    
}
