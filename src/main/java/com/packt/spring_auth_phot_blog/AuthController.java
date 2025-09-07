
package com.packt.spring_auth_phot_blog;


//import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class AuthController {

	// @Autowired 
	// private UserRepository userRepository;

    //Spring Securityで自作のフォーム画面を使用する
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

}