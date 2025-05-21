/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author Admin
 */
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/restaurant_jndi",
        callerQuery = "select password from user where username = ?",
        groupsQuery = "select roles from groups where username = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        hashAlgorithmParameters = {
            "Pbkdf2PasswordHash.Iterations=2048",
            "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA256",
            "Pbkdf2PasswordHash.SaltSizeBytes=64"
        },
        priority = 30)

@ApplicationScoped
public class Project {

}
