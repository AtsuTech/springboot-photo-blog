package com.packt.spring_auth_phot_blog;

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
import java.util.Optional;

@Controller
public class CommentController {

    @Autowired 
    private CommentRepository commentRepository;

    @Autowired 
    private PostRepository postRepository;


    //追加処理
	@PostMapping(path="/comment/add")
	public String addNewPost(@ModelAttribute Comment comment,@RequestParam Integer postId, @AuthenticationPrincipal(errorOnInvalidType=true) UserDetails user ) {
        
        //投稿ユーザー
        comment.setUser((CustomUser)user);

        //投稿ID
        Post post = postRepository.findById(postId).orElseThrow();
        comment.setPost(post);

        //comment.setComment("デバック2");
        commentRepository.save(comment);
        return "redirect:/post/detail/" + postId; 
	}    
}



