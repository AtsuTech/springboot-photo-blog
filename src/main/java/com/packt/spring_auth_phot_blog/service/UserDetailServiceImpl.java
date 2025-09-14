package com.packt.spring_auth_phot_blog.service;

import java.util.Optional;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
// import com.packt.spring_auth_phot_blog.User;
// import com.packt.spring_auth_phot_blog.UserRepository;
import com.packt.spring_auth_phot_blog.CustomUser;
import com.packt.spring_auth_phot_blog.CustomUserRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService {
    //private final UserRepository repository;
    private final CustomUserRepository repository;
    public UserDetailServiceImpl(CustomUserRepository repository) {
        this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws
    UsernameNotFoundException {

        Optional<CustomUser> user = repository.findByUsername(username);
        UserBuilder builder = null;
        if (user.isPresent()) {
            CustomUser currentUser = user.get();
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password("{bcrypt}"+currentUser.getPassword());
            //builder.roles(currentUser.getRole());
            currentUser.setPassword("{bcrypt}"+currentUser.getPassword());
            return user.get();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        //return builder.build();
    }
}

