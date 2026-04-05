package com.packt.spring_auth_phot_blog;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;//use for relation User
//import jakarta.persistence.OneToOne;//use for relation User
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;

@Entity
public class Post {
    
    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title,body,image;

    // public Post(){

    // }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
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

    @OneToMany(mappedBy ="post", cascade = CascadeType.ALL)
	private List<Comment> comments;
    public List<Comment> getComments(){
        return comments;
    }
    public void setComments(List<Comment> comments){
        this.comments = comments;
    }

}
