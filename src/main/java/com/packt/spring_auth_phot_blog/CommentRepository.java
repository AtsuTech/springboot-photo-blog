package com.packt.spring_auth_phot_blog;
import org.springframework.data.repository.CrudRepository;



public interface CommentRepository extends CrudRepository<Comment, Integer> {
    //
    
}

