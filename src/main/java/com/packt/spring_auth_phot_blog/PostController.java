package com.packt.spring_auth_phot_blog;


//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.security.core.annotation.AuthenticationPrincipal;//Auth だめ？
import org.springframework.security.core.userdetails.UserDetails;



@Controller
public class PostController {

    @Autowired 
    private PostRepository postRepository;
    //private UserRepository userRepository;

    //投稿一覧
    @GetMapping("/posts")
	public String index(Model model) {
		Iterable<Post> posts = postRepository.findAll();
		model.addAttribute("posts", posts);
		return "post/posts";
	}


    //投稿追加処理
	@PostMapping(path="/post/add")
	public String addNewPost(@ModelAttribute Post post, @AuthenticationPrincipal(errorOnInvalidType=true) UserDetails user ) {
        post.setUser((CustomUser)user);
        postRepository.save(post);
        return "redirect:/posts"; 
	}

	//投稿追加フォーム画面表示
	@GetMapping("/post/add-form")
	public String add(Model model) {
		model.addAttribute("post", new Post());
		return "post/add";
	}
    
}
