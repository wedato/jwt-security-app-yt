package com.example.jwtsecurityappyt.service;

import com.example.jwtsecurityappyt.entities.AppRole;
import com.example.jwtsecurityappyt.entities.AppUser;

public interface AccountService {

    public AppUser saveUser(String username, String password, String confirmedPassword);
    public AppRole save(AppRole role);
    public AppUser loadUserByUsername(String username);

    void addRoleToUser(String user, String role);



    public void deleteAllBothDocuments();


}
