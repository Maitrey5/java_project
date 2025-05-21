/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import ejb.ejb_restaurant_crud;
import entity.User;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.AuthenticationStatus;
import static jakarta.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static jakarta.security.enterprise.AuthenticationStatus.SUCCESS;
import jakarta.security.enterprise.SecurityContext;
import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Admin
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    @PersistenceContext(unitName = "restaurant_pu")
    EntityManager em;

    @Inject
    Pbkdf2PasswordHash passwordHasher;

    //@EJB ejb_restaurant_crud em;
    @Inject
    SecurityContext ctx;

    private String username;
    private String password;
    private Set<String> roles;
    private String errorstatus;
    private AuthenticationStatus status;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getErrorstatus() {
        return errorstatus;
    }

    public void setErrorstatus(String errorstatus) {
        this.errorstatus = errorstatus;
    }

    public AuthenticationStatus getStatus() {
        return status;
    }

    /**
     * Creates a new instance of LoginBean
     */
    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }

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

        testLogin(username, password);
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Credential credential = new UsernamePasswordCredential(username, new Password(password));
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            System.err.println(credential);
            status = ctx.authenticate(request, response, withParams().credential(credential));

            System.err.println(status);
            System.err.println(roles);

            System.err.println("---------------------------------");
            // AuthenticationStatus mystatus = AuthenticationStatus.;
            if (status.equals(SEND_CONTINUE)) {
                System.err.println("loowwwwwwwwwwww");

                // Authentication mechanism has send a redirect, should not
                // send anything to response from JSF now. The control will now go into HttpAuthenticationMechanism
                context.responseComplete();
            }

            if (status.equals(SUCCESS)) {
                System.err.println(roles);
                
                
                if (roles.contains("Admin")) {
                    System.err.println("poooooooooooooooo");

                    System.err.println("admin");

                    return "/webpages/Admin";
                } else if (roles.contains("Waiter")) {
                    System.err.println("sssssssssssssssssss");

                    System.err.println("waiter");

                    return "/webpages/User";
                }

            } else {
                System.err.println("fffffffffffffffff");

                errorstatus = "User Name or Password may be wrong";
                System.err.println(errorstatus);
                return "/webpages/login";
            }

        } catch (Exception e) {
            errorstatus = "User Name or Password may be wrong";
            e.printStackTrace();
        }

        return "";

//        if ("admin".equals(username) 
//        && "admin123".equals(password)) {
//            return "home.xhtml?faces-redirect=true"; // Redirect to home.xhtml
//    }
//
//    
//        else {
//            // Stay on the same page (add message if desired)
//            return null;
//    }
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

    public void testLogin(String inputUsername, String inputPassword) {
        User user = em.find(User.class, inputUsername);
        if (user == null) {
            System.out.println("❌ Username not found.");
            return;
        }

        String storedHash = user.getPassword();
        boolean match = passwordHasher.verify(inputPassword.toCharArray(), storedHash);

        System.out.println("Stored hash: " + storedHash);
        System.out.println("Entered password: " + inputPassword);
        System.out.println("✅ Password match? " + match);
    }
}
