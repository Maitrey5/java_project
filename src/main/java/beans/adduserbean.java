/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import client.updatedadminclient;
import entity.Staffmaster;
import entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import record.KeepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "adduserbean")
@ViewScoped
public class adduserbean implements Serializable{

    @Inject
    KeepRecord keepRecord;
     
    updatedadminclient em = new updatedadminclient();
    
    Response rs;
    GenericType<Integer> g = new GenericType<Integer>() {};
    Integer id;
    /**
     * Creates a new instance of adduserbean
     */
    String username;
    String password;
    String role;
    GenericType<Collection<User>> guser= new GenericType<Collection<User>>() {
    };
    Collection<User> user = new ArrayList<>();
  
    
    public void adduser(){
        System.err.println("inside add userrrrrrrr");
    
                em.add_user_of_restaurant(username, password, String.valueOf(keepRecord.getIi()),"Waiter");

    }
    
    public Collection<User> displayuser()
    {
        Collection<User> userrr;
            System.err.println("displayyyyyyy");
            
            rs = em.getusers(Response.class,String.valueOf(42));
            
            userrr = rs.readEntity(guser);
            System.err.println("ooooooooooooooooooooooooooooooooooooooo");
            System.err.println(userrr);
            
            return userrr;
        }
    
    
    
    
    public adduserbean() {
                user = displayuser();

        
    }
    
//    @PostConstruct
//    public void init() {
//        if (keepRecord != null) {
//            user = displayuser();
//        } else {
//            System.err.println("keepRecord is NULL in onlymenubean.init()");
//        }
//    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<User> getUser() {
        return user;
    }

    public void setUser(Collection<User> user) {
        this.user = user;
    }
    
    
    
}
