package com.example.jwtsecurityappyt.dao;

import com.example.jwtsecurityappyt.entities.AppRole;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppRoleRepository extends MongoRepository<AppRole,String> {
        AppRole findByRoleName(String rolename);
}
