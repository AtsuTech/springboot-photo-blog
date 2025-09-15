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

import org.springframework.web.bind.annotation.RequestParam;//file upload
import org.springframework.web.multipart.MultipartFile;//file upload
//import com.packt.spring_auth_phot_blog.storage.StorageFileNotFoundException;//file upload
import com.packt.spring_auth_phot_blog.storage.StorageService;//file upload


@Controller
public class PostController {

    //file uloadのためのインスタンス
    private final StorageService storageService;


    //file uloadのためのコンストラクタ
	@Autowired
	public PostController(StorageService storageService) {
		this.storageService = storageService;
	}

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
	public String addNewPost(@ModelAttribute Post post, @RequestParam("file") MultipartFile file, @AuthenticationPrincipal(errorOnInvalidType=true) UserDetails user ) {
        post.setUser((CustomUser)user);
        storageService.store(file);
        post.setImage("/files/"+file.getOriginalFilename());
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
