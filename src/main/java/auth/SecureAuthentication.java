/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auth;

import beans.LoginBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStoreHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.Serializable;
import record.KeepRecord;

/**
 *
 * @author Admin
 */
@Named
@RequestScoped
public class SecureAuthentication implements HttpAuthenticationMechanism , Serializable{

    @Inject IdentityStoreHandler handler;
    CredentialValidationResult result;
    AuthenticationStatus status;
    @Inject TokenProvider tokenProvider;
    @Inject LoginBean lbean;
    @Inject KeepRecord keepRecord;
    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest hsr, HttpServletResponse hsr1, HttpMessageContext hmc) throws AuthenticationException {
        
        
 
    }
    
}
