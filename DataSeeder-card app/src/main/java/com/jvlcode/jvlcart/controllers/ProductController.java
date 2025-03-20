package com.jvlcode.jvlcart.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jvlcode.jvlcart.models.Product;
import com.jvlcode.jvlcart.services.ProductService;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@PostMapping("/addProduct")
	public List<Product> addProduct(@RequestBody @Valid List<Product> product) {
		return productService.CreateProducts(product);
	}
	
	@GetMapping("/category")
	public List<Map<String,Object>> getCategoryProducts() {
		return Arrays.asList(
				Map.of("name", "Product 1", "price", 234),
				Map.of("name", "Product 2", "price", 123)
			);
	}
	
}
