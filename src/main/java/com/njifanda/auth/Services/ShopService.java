package com.njifanda.auth.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njifanda.auth.Models.Category;
import com.njifanda.auth.Models.Product;
import com.njifanda.auth.Repositories.CategoryRepository;
import com.njifanda.auth.Repositories.ProductRepository;

@Service
public class ShopService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public Category findCategoryById(Long categoryId) {
		
		Optional<Category> category = this.categoryRepository.findById(categoryId);
		if (category.isPresent()) {
			return category.get();
		}
		return null;
	}
	
	public Product findProductById(Long productId) {
		
		Optional<Product> product= this.productRepository.findById(productId);
		if (product.isPresent()) {
			return product.get();
		}
		return null;
	}

	public void addProductToCategory(Long productId, Long categoryId) {

		Product product = this.findProductById(productId);
		Category category = this.findCategoryById(categoryId);
		category.getProducts().add(product);
		this.categoryRepository.save(category);
	}
	
	public void addCategoryToProduct(Long productId, Long categoryId) {

		Product product = this.findProductById(productId);
		Category category = this.findCategoryById(categoryId);
		product.getCategories().add(category);
		this.productRepository.save(product);
	}
}
