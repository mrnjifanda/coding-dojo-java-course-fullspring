package com.njifanda.auth.Api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.njifanda.auth.Models.Category;
import com.njifanda.auth.Models.Product;
import com.njifanda.auth.Services.ShopService;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

	@Autowired
	private ShopService shopService;

	@PostMapping("/create-product")
	public Product createProduct() {

		return null;
	}
	
	@PostMapping("/create-category")
	public Category createCategory() {

		return null;
	}
	
}
