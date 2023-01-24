package com.example.furniturestore;

public class User {
    public String username,email,password,mobile;
    public User(){}
    public User(String name,String password, String email,String mobile){
        this.username=name;
        this.email=email;
        this.password=password;
        this.mobile=mobile;
    }
}
