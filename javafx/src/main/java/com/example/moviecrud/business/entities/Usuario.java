package com.example.moviecrud.business.entities;


public class  Usuario {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String adminPrivileges;



    public Usuario( String username,String password, String email, String adminPrivileges) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.adminPrivileges=adminPrivileges;
    }
    public Usuario(){

    }

    public String getAdminPrivileges() {
        return adminPrivileges;
    }

    public void setAdminPrivileges(String adminPrivileges) {
        this.adminPrivileges = adminPrivileges;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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



}
