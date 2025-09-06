package com.packt.spring_auth_phot_blog;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.packt.spring_auth_phot_blog.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}