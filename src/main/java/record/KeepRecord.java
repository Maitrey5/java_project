/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package record;

import jakarta.enterprise.context.SessionScoped;
import jakarta.security.enterprise.CallerPrincipal;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Admin
 */
@SessionScoped
public class KeepRecord implements Serializable{
    
        private  CredentialValidationResult result;
    private  CallerPrincipal principal;
   private  Set<String> roles;
   private  String token;
   private  String username;
   private  String password;
   private Integer r_id;
   private Integer ii;

    public Integer getIi() {
        return ii;
    }

    public void setIi(Integer ii) {
        this.ii = ii;
    }
   
   

    public CredentialValidationResult getResult() {
        return result;
    }

    public void setResult(CredentialValidationResult result) {
        this.result = result;
    }

    public CallerPrincipal getPrincipal() {
        return principal;
    }

    public Integer getR_id() {
        return r_id;
    }

    public void setR_id(Integer r_id) {
        this.r_id = r_id;
    }
    

    public void setPrincipal(CallerPrincipal principal) {
        this.principal = principal;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

   public  void reset()
    {
        
       principal=null;
       token=null;
    }
    
    
    
}
