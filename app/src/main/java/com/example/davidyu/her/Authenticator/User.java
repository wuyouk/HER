package com.example.davidyu.her.Authenticator;

/**
 * Class to hold user information
 */
public class User {

    String name, username, password, token, id;

    public String getId() {
        return id;
    }

    public User(){

    }

    public User(String username, String password, String token, String id){
        this.username = username;
        this.password = password;
        this.name = "name";
        this.token = token;
        this.id = id;
    }

}
