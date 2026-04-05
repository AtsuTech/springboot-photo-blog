package com.packt.spring_auth_phot_blog;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    //リレーション
    //@OneToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    //@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CustomUser user;
    public CustomUser getUser(){
        return user;
    }
    public void setUser(CustomUser user){
        this.user = user;
    }


    //リレーション
    //@OneToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    //@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Post post;
    public Post getPost(){
        return post;
    }
    public void setPost(Post post){
        this.post = post;
    }


}