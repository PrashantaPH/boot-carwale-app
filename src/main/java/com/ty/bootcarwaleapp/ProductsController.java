package com.ty.bootcarwaleapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

	@Autowired
	private ProductsRepository productsRepository;
	
	@PostMapping("/products")
	public Products saveProducts(@RequestBody Products products) {
		return productsRepository.save(products);
	}
	
	@GetMapping("/products")
	public List<Products> allProducts(){
		List<Products> products=productsRepository.findAll();
		if(products.isEmpty()) {
			return null;
		}else
			return products;
	}
	
	@GetMapping("/products/{id}")
	public Products getProductsById(@PathVariable int id) {
		Optional< Products> optional=productsRepository.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else
			return optional.get();
	}
	
	@PutMapping("products/{id}")
	public Products updateProducts(@PathVariable int id,@RequestBody Products products) {
		Optional<Products> optional=productsRepository.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else
			productsRepository.save(products);
			return optional.get();
	}
	
	@DeleteMapping("/products/{id}")
	public String deleteProduct(@RequestParam int id) {
		Optional<Products> optional=productsRepository.findById(id);
		if(optional.isEmpty())
			return "no id";
		else
			productsRepository.deleteById(id);
			return "product successfully deleted";
	}
}
