package com.example.jwtsecurityappyt.dao;

import com.example.jwtsecurityappyt.entities.AppRole;
import com.example.jwtsecurityappyt.entities.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;


public interface AppUserRepository extends MongoRepository<AppUser,String> {
    public AppUser findByUsername(String username);

    public Collection<AppUser> findAppUserByRoles(Collection<AppRole> roles);
}
