/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import client.realclientforwaiters;
import client.updatedadminclient;
import entity.Categorymaster;
import entity.Menumaster;
import entity.Ordermaster;
import entity.Tablemaster;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import record.KeepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "orderhistorybean")
@ViewScoped
public class orderhistorybean implements Serializable {

    /**
     * Creates a new instance of orderhistorybean
     */
    @Inject
    KeepRecord keepRecord;

    updatedadminclient em = new updatedadminclient();
    Response rs;

    realclientforwaiters ew = new realclientforwaiters();

    GenericType<Collection<Ordermaster>> gorder = new GenericType<Collection<Ordermaster>>() {
    };

    Collection<Ordermaster> order = new ArrayList<>();

    Map<Integer, Integer> map = new HashMap<>();

    private Collection<Ordermaster> orders;

    public orderhistorybean() {

    }

    @PostConstruct
    public void init() {
        getorders();
    }

    public void getorders() {
        rs = ew.get_orders_by_restaurant(Response.class, String.valueOf(keepRecord.getIi()));
        this.order = rs.readEntity(gorder);
    }

    // Getters & setters
   
    private String orderId, tableNumber, date, time, total;
    private String status = "completed";
    private int items;
    public Collection<Ordermaster> getOrder() {
        return order;
    }

    // Inner class (or move to separate file)
    public void setOrder(Collection<Ordermaster> order) {
        this.order = order;
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  

    public String getTableNumber() {
        return tableNumber;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getItems() {
        return items;
    }

    public String getTotal() {
        return total;
    }

}
