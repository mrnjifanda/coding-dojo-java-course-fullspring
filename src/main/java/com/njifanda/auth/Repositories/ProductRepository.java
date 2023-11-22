package com.njifanda.auth.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.njifanda.auth.Models.Product;

public interface ProductRepository  extends CrudRepository<Product, Long>{}
