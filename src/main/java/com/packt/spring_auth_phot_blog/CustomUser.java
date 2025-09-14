package com.packt.spring_auth_phot_blog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity // This tells Hibernate to make a table out of this class
public class CustomUser implements UserDetails {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String username;

  private String password;

  private String email;

  private String description;

  private String avator;

  private String role;

  private Collection<? extends GrantedAuthority> authorities;//UsrDeatimigrateカスタム

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDescription(){
    return description;
  }

  public void setDescription(String description){
    this.description = description;
  }

  public String getAvator(){
    return avator;
  }

  public void setAvator(String avator){
    this.avator = avator;
  }

  public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

  //UsrDeatimigrateカスタム
//   public void setAuthorities(Collection<? extends GrantedAuthority> authorities){
//     this.authorities = authorities;
//   }

  //UsrDeatimigrateカスタム
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities(){
    //return authorities;
    return List.of(new SimpleGrantedAuthority(role));
  }

  //UsrDeatimigrateカスタム
  @Override
  public boolean isAccountNonExpired() {
		return true;
	}

  //UsrDeatimigrateカスタム
  @Override
  public boolean isAccountNonLocked(){
    return true;
  }

  //UsrDeatimigrateカスタム
  @Override
  public boolean isCredentialsNonExpired(){
    return true;
  }

  //UsrDeatimigrateカスタム
  @Override
  public boolean isEnabled(){
    return true;
  }

}