package com.demoapi.demorestfulapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demoapi.demorestfulapi.entities.Product;
import com.demoapi.demorestfulapi.exceptions.ResourceNotFoundException;
import com.demoapi.demorestfulapi.repositories.ProductRespository;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	@Autowired
	private ProductRespository productRespository;
	
	@PostMapping("/create")
	public Product create(@Validated @RequestBody Product product, @RequestParam("image") MultipartFile multipartFile) {
		
		return productRespository.save(product);
	}
}
