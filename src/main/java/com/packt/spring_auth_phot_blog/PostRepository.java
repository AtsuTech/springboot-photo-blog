package com.packt.spring_auth_phot_blog;

//import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

//import com.packt.spring_auth_phot_blog.Post;



public interface PostRepository extends CrudRepository<Post, Integer> {
    //Optional<Post> findByUsername(String username);
}