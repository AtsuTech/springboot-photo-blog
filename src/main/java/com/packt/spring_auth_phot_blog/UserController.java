package com.packt.spring_auth_phot_blog;

// import java.util.HashMap;
// import java.util.Map;
import java.util.Optional;
//import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
// import org.springframework.security.crypto.password.StandardPasswordEncoder;
// import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import org.springframework.security.crypto.password.DelegatingPasswordEncoder;//パスワードエンコード
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Controller
public class UserController {

	@Autowired 
	private UserRepository userRepository;

    @GetMapping("/top")
	public String bye(@RequestParam(name="name", required=false, defaultValue="さようなら") String name, Model model) {
		model.addAttribute("name", name);
		return "top";
	}

	@GetMapping("/users")
	public String index(Model model) {
		Iterable<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		return "users";
	}

	//ユーザー詳細画面表示
	@GetMapping("/user/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model){
		//Optionalは値がnullかもしれないことを宣言
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			//ここで値をhtmlに渡す
			model.addAttribute("user", user.get());
			return "detail";
		}
		return "redirect:/users";
	}

	//ユーザー追加処理
	@PostMapping(path="/user/add*")
	public String addNewUser (@ModelAttribute User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
		userRepository.save(user);
		return "redirect:/users"; // Redirect to the page that lists all users
	}

	//ユーザー追加フォーム画面表示
	@GetMapping("/user/register*")
	public String add(Model model) {
		model.addAttribute("user", new User());
		return "auth/register";
	}

	// ユーザー更新処理
	@PostMapping(path="/user/update")
	public String updateUser(@ModelAttribute User user) {
		userRepository.save(user); // saveメソッドは、Idが存在すれば更新、なければ新規作成する
		return "redirect:/users";
	}


	//ユーザー更新フォーム画面表示
	@GetMapping("/user/update-view/{id}")
	public String update(@PathVariable("id") Integer id, Model model){
		//Optionalは値がnullかもしれないことを宣言
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			//ここで値をhtmlに渡す
			model.addAttribute("user", user.get());
			return "update-form";
		}
		return "redirect:/users";
	}

	// ユーザー削除処理
	@GetMapping("/user/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model){
		userRepository.deleteById(id);
		return "redirect:/users";
	}
}