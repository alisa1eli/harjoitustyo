/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.notes;

import java.security.Permissions;
import java.util.Locale;
import sun.security.util.Password;

/**
 *
 * @author alisaelizarova
 */
public class User {
    int id;
    String email;
    String password;
    String name;
    Permissions permissions;
    int userType;
    
    // user isn't in the database
    public User(String email, String password) {
        this.id = -1;             // so the id isn't defined 
        this.email = email.toLowerCase();
        this.password = password;
        this.name = "";
        this.permissions = new Permissions();
        this.userType = 1;
    }
    
    //user isn't in the database, but she/he gives their name
    public User(String email, String password, String name) {
        this.id = -1;             // the id isn't defined 
        this.email = email.toLowerCase();
        this.password = password;
        this.name = name.toLowerCase();
        this.permissions = new Permissions();
        this.userType = 1;
    }
    
    // all parameters are defined
    // the user is saved in the db
    public User(int id, String email, String password, 
            String name, int userType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.permissions = new Permissions();
        this.userType = userType;
    }
    
    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public Permissions getPermissions() {
        return permissions;
    }
    public int getUserType() {
        return userType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
    
   
}
