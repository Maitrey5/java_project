/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean implements Serializable{

    
     private String username;
    private String password;
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
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
    
    public String login() {
        // Dummy authentication logic
        if ("admin".equals(username) && "admin123".equals(password)) {
            return "home.xhtml?faces-redirect=true"; // Redirect to home.xhtml
        } else {
            // Stay on the same page (add message if desired)
            return null;
        }
    }
    
    public void register() {
        // Dummy authentication logic
         try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("register.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public String forgotPassword() {
        // Add password recovery logic
        return "forgot-password?faces-redirect=true";
    }
    
}
