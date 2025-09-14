package com.packt.spring_auth_phot_blog;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

//import com.packt.spring_auth_phot_blog.CustomUser;

public interface CustomUserRepository extends CrudRepository<CustomUser, Integer> {
    Optional<CustomUser> findByUsername(String username);
}