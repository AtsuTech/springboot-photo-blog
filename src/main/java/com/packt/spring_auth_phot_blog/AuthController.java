
package com.packt.spring_auth_phot_blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class AuthController {

	@Autowired 
	private CustomUserRepository userRepository;

    	//ユーザー追加処理
	@PostMapping(path="/user/add")
	public String addNewUser (@ModelAttribute CustomUser user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
		userRepository.save(user);
		return "redirect:/users"; // Redirect to the page that lists all users
	}

	//ユーザー追加フォーム画面表示
	@GetMapping("/user/register")
	public String add(Model model) {
		model.addAttribute("user", new CustomUser());
		return "auth/register";
	}

    //Spring Securityで自作のフォーム画面を使用する
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

}