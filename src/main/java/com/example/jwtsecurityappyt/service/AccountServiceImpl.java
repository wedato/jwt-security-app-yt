package com.example.jwtsecurityappyt.service;

import com.example.jwtsecurityappyt.dao.AppRoleRepository;
import com.example.jwtsecurityappyt.dao.AppUserRepository;
import com.example.jwtsecurityappyt.entities.AppRole;
import com.example.jwtsecurityappyt.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public AppUser saveUser(String username, String password, String confirmedPassword) {
        // verifier si l'utilisateur n'existe pas deja
        AppUser user = appUserRepository.findByUsername(username);
        if (user != null) throw new RuntimeException("User already exists");
        if (!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setActivated(true);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);

        addRoleToUser(appUser.getUsername(), "USER");
        return appUser;

    }

    @Override
    public AppRole save(AppRole role) {
        return appRoleRepository.save(role);

    }

    @Override
    public AppUser loadUserByUsername(String username) {

        return appUserRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String user , String role){
        AppUser appUser = appUserRepository.findByUsername(user);
        AppRole appRole = appRoleRepository.findByRoleName(role);
        appUser.getRoles().add(appRole);
        appUserRepository.save(appUser);
    }


    @Override
    public void deleteAllBothDocuments() {
        appUserRepository.deleteAll();
        appRoleRepository.deleteAll();
    }

}
