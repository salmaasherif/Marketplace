/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.market;

/**
 *
 * @author Salma
 */
public class User {
     static String username,pass;

    public User(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    static public String getUsername() {
        return username;
    }

    static public String getPass() {
        return pass;
    }

    static public void setUsername(String username) {
        User.username = username;
    }

    static public void setPass(String pass) {
        User.pass = pass;
    }
    
    
}
