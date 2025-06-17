/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
@Named(value = "navigationBean")
@SessionScoped
public class Navigationbean implements Serializable {

    /**
     * Creates a new instance of Navigationbean
     */
    public Navigationbean() {
    }

    private String activePage = "order"; // default page

    public void goOrder() {
        activePage = "order";
        // optionally navigate
    }

    public void goDashboard() {
        activePage = "dashboard";
    }

    public void goHome() {
        activePage = "home";
    }

    public void goTableStatus() {
        activePage = "table";
    }

    public void goOrderHistory() {
        activePage = "history";
    }
    public void goview() {
        System.err.println("viewwwwwwwwwwwwwwwwwww");
        activePage = "view";
                System.err.println("viewwwwwwwwwwwwwwwwwww====="+activePage);

    }

    public String getActivePage() {
        return activePage;
    }

    public void setActivePage(String activePage) {
        this.activePage = activePage;
    }

}
