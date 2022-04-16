package com.example.jwtsecurityappyt;

import com.example.jwtsecurityappyt.dao.CategoryRepository;
import com.example.jwtsecurityappyt.dao.ProductRepository;
import com.example.jwtsecurityappyt.entities.AppRole;
import com.example.jwtsecurityappyt.entities.Category;
import com.example.jwtsecurityappyt.entities.Product;
import com.example.jwtsecurityappyt.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class JwtSecurityAppYtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSecurityAppYtApplication.class, args);
    }


    @Bean
    CommandLineRunner start(CategoryRepository categoryRepository, ProductRepository productRepository, AccountService accountService){
        return args -> {

            accountService.deleteAllBothDocuments();
            accountService.save(new AppRole("USER"));
            accountService.save(new AppRole("ADMIN"));
            Stream.of("user1","user2","user3","admin").forEach(u->{
                accountService.saveUser(u,"1234","1234");
            });
            accountService.addRoleToUser("admin", "ADMIN");

            categoryRepository.deleteAll();
            Stream.of("C1 Ordinateurs","C2 Imprimantes").forEach(c-> {
                categoryRepository.save(new Category(c.split(" ")[0],c.split(" ")[1],new ArrayList<>()));
            });
            categoryRepository.findAll().forEach(System.out::println);
            productRepository.deleteAll();
            Category c1 = categoryRepository.findById("C1").get();
            Stream.of("P1", "P2", "P3", "P4").forEach(name -> {
                Product p =productRepository.save(new Product(null,name,Math.random()* 1000,c1));
                c1.getProducts().add(p);
                categoryRepository.save(c1);
            });
            Category c2 = categoryRepository.findById("C2").get();
            Stream.of("P5","P6").forEach(name -> {
                Product p = productRepository.save(new Product(null,name,Math.random()* 1000,c2));
                c2.getProducts().add(p);
                categoryRepository.save(c2);
            });
            productRepository.findAll().forEach(System.out::println);

        };
    }
    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }
}

