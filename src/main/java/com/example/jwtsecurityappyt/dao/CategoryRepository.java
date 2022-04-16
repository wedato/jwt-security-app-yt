package com.example.jwtsecurityappyt.dao;

import com.example.jwtsecurityappyt.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {
}
