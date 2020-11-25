package com.demoapi.demorestfulapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.demoapi.demorestfulapi.entities.Product;
import com.demoapi.demorestfulapi.exceptions.ResourceNotFoundException;
import com.demoapi.demorestfulapi.repositories.ProductRespository;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	@Autowired
	private ProductRespository productRespository;

	@PostMapping("/upload-image")
	public String upload(@Validated @RequestParam("image") MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String uploadDir = Paths.get("").toAbsolutePath().toString() + "/src/main/resources/public" ;
		Path uploadPath = Paths.get(uploadDir);
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new IOException("Could not save upload file: " + fileName);
		}
		return fileName;
	}
	
	@PostMapping("/create")
	public Product create(@Validated @RequestBody Product product) throws IOException {
		return productRespository.save(product);
	}

	@GetMapping("/list-product")
	public List<Product> viewlist(){
		return productRespository.findAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId) throws ResourceNotFoundException {
		Product product = productRespository.findById(productId)
					.orElseThrow(() -> new ResourceNotFoundException("Product not found on:" + productId));
		return ResponseEntity.ok().body(product);
	}
}
