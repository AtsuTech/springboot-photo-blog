package com.packt.spring_auth_phot_blog;


//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;//Auth だめ？
import org.springframework.web.bind.annotation.ModelAttribute;//URLパスの一部を変数として扱うためのアノテーション
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;//処理後のメッセージ表示
import org.springframework.web.bind.annotation.RequestParam;//投稿が存在しない場合のエラー処理(例外発生)
import org.springframework.web.multipart.MultipartFile;//file upload
import org.springframework.web.servlet.mvc.support.RedirectAttributes;//file upload

import com.packt.spring_auth_phot_blog.storage.StorageService;//file upload
import java.util.Optional;//値が空であるかもしれないことを宣言

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


	//投稿編集フォーム画面表示
	@GetMapping("/post/update-form/{id}")
	public String update(@PathVariable("id") Integer id, Model model){

		//Optionalは値がnullかもしれないことを宣言
		Optional<Post> post = postRepository.findById(id);

		if(post.isPresent()){
			model.addAttribute("post", post.get());
			return "post/update";
		}
		//
		return "redirect:/posts";
	}


	//投稿更新処理
	@PostMapping(path="/post/update")
	public String updateUser(@ModelAttribute Post post) {
		//ファイルの処理が必要なので検討中
		// storageService.store(file);
        // post.setImage("/files/"+file.getOriginalFilename());

		postRepository.save(post); // saveメソッドは、Idが存在すれば更新、なければ新規作成する
		return "redirect:/posts";
	}


	//投稿削除
	@GetMapping(path="/post/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model,RedirectAttributes redirectAttrs){
		
		try {
			postRepository.deleteById(id);
			redirectAttrs.addFlashAttribute("successMessage", "投稿が正常に削除されました。");
		} catch (EmptyResultDataAccessException e) {
			redirectAttrs.addFlashAttribute("successMessage", "投稿が存在しません。");
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("successMessage", "投稿の削除に失敗しました。");
		}
		return "redirect:/posts"; 
	}
    
}
