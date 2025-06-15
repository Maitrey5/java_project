/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.ejbforwaitersLocal;
import entity.Billmaster;
import entity.Menumaster;
import entity.Ordermaster;
import jakarta.ejb.EJB;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * REST Web Service
 *
 * @author Admin
 */
@Path("genericwaiter")
@RequestScoped
public class GenericRestResourceforwaiters {

    @EJB ejbforwaitersLocal em;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericRestResourceforwaiters
     */
    public GenericRestResourceforwaiters() {
    }

    /**
     * Retrieves representation of an instance of rest.GenericRestResourceforwaiters
     * @return an instance of java.lang.String
     */
    
    @POST
    @Path("add_order_to_restaurant/{restaurant_id}/{order_date}/{table_id}/{noofpeople}/{amount}")
    @Consumes("application/json")
    public void add_order_to_restaurant(
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("order_date") String oreder_date,
            @PathParam("table_id") Integer table_id,
            @PathParam("noofpeople") Integer noofpeople,
            @PathParam("amount") Integer amount,
            orderdetails orders
           ) throws ParseException {
        
                Date order_date = null;

                order_date = new SimpleDateFormat("yyyy-MM-dd").parse(oreder_date);

        em.add_order_to_restaurant(restaurant_id, order_date, table_id, noofpeople, amount ,orders.menuids, orders.quantity );
    }
//
    @PUT
    @Path("update_item_to_order/{ordermenuid}/{menuid}/{qunatity}")
    public void update_item_to_order(
            @PathParam("ordermenuid") Integer ordermenuid,
            @PathParam("menuid") Integer menuid,
            @PathParam("qunatity") Integer qunatity) {
        
        em.update_item_to_order(ordermenuid, menuid, qunatity);
    }
//
    @DELETE
    @Path("delete_item_to_order/{ordermenuid}/{menuid}")
    public void delete_item_to_order(
            @PathParam("ordermenuid") Integer ordermenuid,
            @PathParam("menuid") Integer menuid) {
        
        em.delete_item_to_order(ordermenuid, menuid);
        
    }

    @POST
    @Path("add_item_to_order/{ordermenuid}/{menuid}/{qunatity}")
    public void add_item_to_order(
            @PathParam("ordermenuid") Integer ordermenuid,
            @PathParam("menuid") Integer menuid,
            @PathParam("qunatity") Integer qunatity) {
        
        em.add_item_to_order(ordermenuid, menuid, qunatity);
    }

    @DELETE
    @Path("delete_order/{orderid}")
    public void delete_order(@PathParam("orderid") Integer orderid) {
        em.delete_order(orderid);
    }

    @GET
    @Path("get_orders_by_restaurant/{restaurant_id}")
    @Produces("application/json")
    public Collection<Ordermaster> get_orders_by_restaurant(@PathParam("restaurant_id") Integer restaurant_id) {
        return em.get_orders_by_restaurant(restaurant_id);
    }

    @POST
    @Path("add_bill_to_restaurant/{order_id}/{restaurant_id}/{total_amount}/{discount}/{final_amount}/{final_payble_amount_with_tax}/{datetime}/{modeofpayment}/{transaction_id}")
    public void add_bill_to_restaurant(
            @PathParam("order_id") Integer order_id,
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("total_amount") Integer total_amount,
            @PathParam("discount") Integer discount,
            @PathParam("final_amount") Integer final_amount,
            @PathParam("final_payble_amount_with_tax") Integer final_payble_amount_with_tax,
            @PathParam("datetime") String datetime,
            @PathParam("modeofpayment") String modeofpayment,
            @PathParam("transaction_id") Integer transaction_id) throws ParseException {
        
         Date order_date = null;

                order_date = new SimpleDateFormat("yyyy-MM-dd").parse(datetime);
        
        em.add_bill_to_restaurant(order_id, restaurant_id, total_amount, discount, final_amount, final_payble_amount_with_tax, order_date, modeofpayment, transaction_id);
    }

    @GET
    @Path("get_bills_by_restaurant/{restaurant_id}")
    @Produces("application/json")
    public Collection<Billmaster> get_bills_by_restaurant(@PathParam("restaurant_id") Integer restaurant_id) {
        return em.get_bills_by_restaurant(restaurant_id);
    }

    @GET
    @Path("search_menu/{itemname}/{restaurantId}")
    @Produces("application/json")
    public Menumaster search_menu(@PathParam("itemname") String itemname, @PathParam("restaurantId") Integer restaurantId) {
        return em.search_menu(itemname, restaurantId);
    }

    @GET
    @Path("get_all_menuitems_by_restaurant/{restaurant_id}")
    @Produces("application/json")
    public Collection<Menumaster> get_all_menuitems_by_restaurant(@PathParam("restaurant_id") Integer restaurant_id) {
        return em.get_all_menuitems_by_restaurant(restaurant_id);
    }

    @POST
    @Path("add_transaction_to_restaurant/{restaurant_id}/{amount}/{transaction_type}/{description}/{Date}/{time}")
    public void add_transaction_to_restaurant(
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("amount") Integer amount,
            @PathParam("transaction_type") String transaction_type,
            @PathParam("description") String description,
            @PathParam("Date") String Date,
            @PathParam("time") String time) throws ParseException {
        
                  Date d = null;
                  Date t = null;


        d = new SimpleDateFormat("yyyy-MM-dd").parse(Date);
        Time parsedTime = Time.valueOf(time); // for "14:30:00"
        
        
        
        em.add_transaction_to_restaurant(restaurant_id, amount, transaction_type, description, d, parsedTime);
    }

}
