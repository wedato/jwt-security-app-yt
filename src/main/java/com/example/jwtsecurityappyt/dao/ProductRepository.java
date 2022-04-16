package com.example.jwtsecurityappyt.dao;

import com.example.jwtsecurityappyt.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
