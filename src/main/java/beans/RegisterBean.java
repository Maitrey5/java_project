/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import client.updatedadminclient;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author Admin
 */
@Named(value = "registerBean")
@RequestScoped
public class RegisterBean implements Serializable {

//    @Inject
//    private Pbkdf2PasswordHash passwordHasher;
    
    @Inject
    private Pbkdf2PasswordHash passwordHash;
    updatedadminclient em;
    Response rs;
    GenericType<Integer> g = new GenericType<Integer>() {};
    Integer id;
    
    // String rawPassword = "admin";

    // Manually create an instance of the hash class
    Pbkdf2PasswordHash hash = new Pbkdf2PasswordHashImpl();

//        System.out.println("PBKDF2 hash of 'admin':");
//        System.out.println(hashedPassword);

    /**
     * Creates a new instance of RegisterBean
     */
    private String restaurant_name;
    private String restaurant_address;
    private String restaurant_contactno;
    private String restaurant_email;
    private String restaurant_city;
    private String restaurant_state;
    private String restaurant_country;
    private String restaurant_pincode;
    private String username;
    private String password;

    public RegisterBean() {
    }
    
    
    public String generateHash(String password) {
        // Optional: set parameters like iteration count, algorithm, etc.
        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "3072");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");

        hash.initialize(parameters);

        return passwordHash.generate(password.toCharArray());
    }

    public void register() {

        em = new updatedadminclient();

        FacesContext context = FacesContext.getCurrentInstance();

        if (restaurant_name == null || restaurant_name.trim().length() < 3) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Restaurant name must be at least 3 characters"));
            context.validationFailed(); // prevent redirect
            return;
        }

        if (username == null || username.trim().isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Username is required"));
            context.validationFailed();
            return;
        }

        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        rs = em.add_restaurant(Response.class, restaurant_name, restaurant_address, restaurant_contactno, restaurant_email, restaurant_city, restaurant_state, restaurant_country, restaurant_pincode, formattedDate, formattedDate, "true");

        if (rs.getStatus() == 200) {
            String idStr = rs.readEntity(String.class);
            Integer id = Integer.parseInt(idStr);

            System.out.println("Generated ID: " + id);

            String role = "Admin";
            
                //PasswordHashGenerator generator = container.select(PasswordHashGenerator.class).get();

                String hashedPassword = generateHash(password);

           // String hashedPassword = passwordHasher.generate(password.toCharArray());
            
            System.err.println(hashedPassword);
            em.add_user_of_restaurant(username, password, String.valueOf(id), role);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Registered successfully"));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registration failed: " + rs.getStatus()));
        }

        //id = rs.readEntity(g);

        //String idStr = rs.readEntity(String.class);  // Read as string
        //Integer id = Integer.parseInt(idStr);        // Convert to integer
//        System.out.println(id);
//
//        System.out.println(id);
//        System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
//        System.out.println(rs);
//
//        String role = "Waiter";
//        String hashedPassword = passwordHasher.generate(password.toCharArray());
//
//        System.out.println("after hashed pasworddddddddddddddddddddd");
//        System.out.println(hashedPassword);

        //em.add_user_of_restaurant(username, hashedPassword, String.valueOf(id), role);
        //em.add_user_of_restaurant(username, hashedPassword, String.valueOf(id), role);

        System.out.println("Registration successfully-------------------------------");

        // Add more validations as needed...
        // Success
        //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Registered successfully"));

//        System.out.println("Registered: " + restaurant_name);
//
//        FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Registered successfully"));
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

    // Getters and Setters for all fields
    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getRestaurant_address() {
        return restaurant_address;
    }

    public void setRestaurant_address(String restaurant_address) {
        this.restaurant_address = restaurant_address;
    }

    public String getRestaurant_contactno() {
        return restaurant_contactno;
    }

    public void setRestaurant_contactno(String restaurant_contactno) {
        this.restaurant_contactno = restaurant_contactno;
    }

    public String getRestaurant_email() {
        return restaurant_email;
    }

    public void setRestaurant_email(String restaurant_email) {
        this.restaurant_email = restaurant_email;
    }

    public String getRestaurant_city() {
        return restaurant_city;
    }

    public void setRestaurant_city(String restaurant_city) {
        this.restaurant_city = restaurant_city;
    }

    public String getRestaurant_state() {
        return restaurant_state;
    }

    public void setRestaurant_state(String restaurant_state) {
        this.restaurant_state = restaurant_state;
    }

    public String getRestaurant_country() {
        return restaurant_country;
    }

    public void setRestaurant_country(String restaurant_country) {
        this.restaurant_country = restaurant_country;
    }

    public String getRestaurant_pincode() {
        return restaurant_pincode;
    }

    public void setRestaurant_pincode(String restaurant_pincode) {
        this.restaurant_pincode = restaurant_pincode;
    }

}
