/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.ejb_restaurant_crudLocal;
import entity.Billmaster;
import entity.Categorymaster;
import entity.Groups;
import entity.Inventorymaster;
import entity.Menumaster;
import entity.Ordermaster;
import entity.Restaurantmaster;
import entity.StaffTransaction;
import entity.Staffmaster;
import entity.Tablebooking;
import entity.Tablemaster;
import entity.Transactionmaster;
import entity.User;
import jakarta.ejb.EJB;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * REST Web Service
 *
 * @author Admin
 */
@Path("generic")
@RequestScoped
public class GenericRestResource {

    @EJB
    ejb_restaurant_crudLocal em;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericRestResource
     */
    public GenericRestResource() {
    }

    /**
     * Retrieves representation of an instance of rest.GenericRestResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/search_table/{rid}/{table_no}")
    @Produces("application/json")
    public Tablemaster search_table(@PathParam("rid") Integer rid, @PathParam("table_no") Integer table_no) {

        return em.search_table(rid, table_no);

    }

    @POST
    @Path("/add/{restaurant_name}/{restaurant_address}/{restaurant_contactno}/{restaurant_email}/{restaurant_city}/{restaurant_state}/{restaurant_country}/{restaurant_pincode}/{created_at}/{updated_at}/{is_active}")
    @Produces("application/json")
    public Integer add_restaurant(
            @PathParam("restaurant_name") String restaurant_name,
            @PathParam("restaurant_address") String restaurant_address,
            @PathParam("restaurant_contactno") String restaurant_contactno,
            @PathParam("restaurant_email") String restaurant_email,
            @PathParam("restaurant_city") String restaurant_city,
            @PathParam("restaurant_state") String restaurant_state,
            @PathParam("restaurant_country") String restaurant_country,
            @PathParam("restaurant_pincode") Integer restaurant_pincode,
            @PathParam("created_at") String created_at,
            @PathParam("updated_at") String updated_at,
            @PathParam("is_active") Boolean is_active) throws ParseException {

        System.out.println("hello world ");

        Date created_date = null;
        Date updated_date = null;

        created_date = new SimpleDateFormat("yyyy-MM-dd").parse(created_at);
        updated_date = new SimpleDateFormat("yyyy-MM-dd").parse(updated_at);

        Integer idd = em.add_restaurant(restaurant_name, restaurant_address, restaurant_contactno, restaurant_email, restaurant_city, restaurant_state, restaurant_country, restaurant_pincode, created_date, updated_date, is_active);

        System.out.println(idd);
        System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiis");
        return idd;
    }

    @DELETE
    @Path("delete/{restaurant_id}")
    public void delete_restaurant(@PathParam("restaurant_id") Integer restaurant_id) {
        em.delete_restaurant(restaurant_id);
    }

    @PUT
    @Path("update/{restaurant_id}/{restaurant_name}/{restaurant_address}/{restaurant_contactno}/{restaurant_email}/{restaurant_city}/{restaurant_state}/{restaurant_country}/{restaurant_pincode}/{created_at}/{updated_at}/{is_active}")
    public void update_restaurant(
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("restaurant_name") String restaurant_name,
            @PathParam("restaurant_address") String restaurant_address,
            @PathParam("restaurant_contactno") String restaurant_contactno,
            @PathParam("restaurant_email") String restaurant_email,
            @PathParam("restaurant_city") String restaurant_city,
            @PathParam("restaurant_state") String restaurant_state,
            @PathParam("restaurant_country") String restaurant_country,
            @PathParam("restaurant_pincode") Integer restaurant_pincode,
            @PathParam("created_at") String created_at,
            @PathParam("updated_at") String updated_at,
            @PathParam("is_active") Boolean is_active) throws ParseException {

        Date created_date = null;
        Date updated_date = null;

        created_date = new SimpleDateFormat("yyyy-MM-dd").parse(created_at);
        updated_date = new SimpleDateFormat("yyyy-MM-dd").parse(updated_at);

        em.update_restaurant(restaurant_id, restaurant_name, restaurant_address, restaurant_contactno, restaurant_email, restaurant_city, restaurant_state, restaurant_country, restaurant_pincode, created_date, updated_date, is_active);
    }

    @GET
    @Path("/search/{restaurant_id}")
    @Produces("application/json")
    public Restaurantmaster search_restaurant(@PathParam("restaurant_id") Integer restaurant_id) {

        return em.search_restaurant(restaurant_id);
    }

    @POST
    @Path("add_menu/{restaurant_id}/{category_id}/{item_name}/{item_price}/{description}/{is_avalaible}/{updated_at}/{item_type}")
    public void add_menu(
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("category_id") Integer category_id,
            @PathParam("item_name") String item_name,
            @PathParam("item_price") Integer item_price,
            @PathParam("description") String description,
            @PathParam("is_avalaible") Boolean is_avalaible,
            @PathParam("updated_at") String updated_at,
            @PathParam("item_type") String item_type) throws ParseException {

        Date updated_date = null;

        updated_date = new SimpleDateFormat("yyyy-MM-dd").parse(updated_at);

        em.add_menu(restaurant_id, category_id, item_name, item_price, description, is_avalaible, updated_date, item_type);
    }

    @DELETE
    @Path("delete_menu/{menu_id}")
    public void delete_menu(@PathParam("menu_id") Integer menu_id) {

        em.delete_menu(menu_id);
    }

    @PUT
    @Path("update_menu/{menu_id}/{restaurant_id}/{category_id}/{item_name}/{item_price}/{description}/{is_avalaible}/{updated_at}/{item_type}")
    public void update_menu(
            @PathParam("menu_id") Integer menu_id,
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("category_id") Integer category_id,
            @PathParam("item_name") String item_name,
            @PathParam("item_price") Integer item_price,
            @PathParam("description") String description,
            @PathParam("is_avalaible") Boolean is_avalaible,
            @PathParam("updated_at") String updated_at,
            @PathParam("item_type") String item_type) throws ParseException {

        Date updated_date = null;

        updated_date = new SimpleDateFormat("yyyy-MM-dd").parse(updated_at);

        em.update_menu(menu_id, restaurant_id, category_id, item_name, item_price, description, is_avalaible, updated_date, item_type);
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
    @Path("add_category/{restaurant_id}/{category_name}/{updated_at}")
    public void add_category(
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("category_name") String category_name,
            @PathParam("updated_at") String updated_at) throws ParseException {

        Date updated_date = null;

        updated_date = new SimpleDateFormat("yyyy-MM-dd").parse(updated_at);

        em.add_category(restaurant_id, category_name, updated_date);
    }

    @DELETE
    @Path("delete_category/{category_id}")
    public void delete_category(@PathParam("category_id") Integer category_id) {

        em.delete_category(category_id);
    }

    @PUT
    @Path("update_category/{category_id}/{restaurant_id}/{category_name}/{updated_at}")
    public void update_category(
            @PathParam("category_id") Integer category_id,
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("category_name") String category_name,
            @PathParam("updated_at") String updated_at) throws ParseException {

        Date updated_date = null;

        updated_date = new SimpleDateFormat("yyyy-MM-dd").parse(updated_at);

        em.update_category(category_id, restaurant_id, category_name, updated_date);
    }

    @GET
    @Path("get_categories_by_restaurant/{restaurant_id}")
    @Produces("application/json")
    public Collection<Categorymaster> get_categories_by_restaurant(@PathParam("restaurant_id") Integer restaurant_id) {
        return em.get_categories_by_restaurant(restaurant_id);
    }

//    @POST
//    @Path("add_order_to_restaurant/{restaurant_id}/{order_date}/{table_id}/{noofpeople}")
//    @Consumes("application/json")
//    public void add_order_to_restaurant(
//            @PathParam("restaurant_id") Integer restaurant_id,
//            @PathParam("order_date") String oreder_date,
//            @PathParam("table_id") Integer table_id,
//            @PathParam("noofpeople") Integer noofpeople,
//            orderdetails orders
//           ) throws ParseException {
//        
//                Date order_date = null;
//
//                order_date = new SimpleDateFormat("yyyy-MM-dd").parse(oreder_date);
//
//        em.add_order_to_restaurant(restaurant_id, order_date, table_id, noofpeople, orders.menuids, orders.quantity);
//    }
//
//    @PUT
//    @Path("update_item_to_order/{ordermenuid}/{menuid}/{qunatity}")
//    public void update_item_to_order(
//            @PathParam("ordermenuid") Integer ordermenuid,
//            @PathParam("menuid") Integer menuid,
//            @PathParam("qunatity") Integer qunatity) {
//        
//        em.update_item_to_order(ordermenuid, menuid, qunatity);
//    }
//
//    @DELETE
//    @Path("delete_item_to_order/{ordermenuid}/{menuid}")
//    public void delete_item_to_order(
//            @PathParam("ordermenuid") Integer ordermenuid,
//            @PathParam("menuid") Integer menuid) {
//        
//        em.delete_item_to_order(ordermenuid, menuid);
//        
//    }
//    @POST
//    @Path("add_item_to_order/{ordermenuid}/{menuid}/{qunatity}")
//    public void add_item_to_order(
//            @PathParam("ordermenuid") Integer ordermenuid,
//            @PathParam("menuid") Integer menuid,
//            @PathParam("qunatity") Integer qunatity) {
//        
//        em.add_item_to_order(ordermenuid, menuid, qunatity);
//    }
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

//    @POST
//    @Path("add_bill_to_restaurant/{order_id}/{restaurant_id}/{total_amount}/{discount}/{final_amount}/{final_payble_amount_with_tax}/{datetime}/{modeofpayment}/{transaction_id}")
//    public void add_bill_to_restaurant(
//            @PathParam("order_id") Integer order_id,
//            @PathParam("restaurant_id") Integer restaurant_id,
//            @PathParam("total_amount") Integer total_amount,
//            @PathParam("discount") Integer discount,
//            @PathParam("final_amount") Integer final_amount,
//            @PathParam("final_payble_amount_with_tax") Integer final_payble_amount_with_tax,
//            @PathParam("datetime") String datetime,
//            @PathParam("modeofpayment") String modeofpayment,
//            @PathParam("transaction_id") Integer transaction_id) throws ParseException {
//        
//         Date order_date = null;
//
//                order_date = new SimpleDateFormat("yyyy-MM-dd").parse(datetime);
//        
//        em.add_bill_to_restaurant(order_id, restaurant_id, total_amount, discount, final_amount, final_payble_amount_with_tax, order_date, modeofpayment, transaction_id);
//    }
    @GET
    @Path("get_bills_by_restaurant/{restaurant_id}")
    @Produces("application/json")
    public Collection<Billmaster> get_bills_by_restaurant(@PathParam("restaurant_id") Integer restaurant_id) {
        return em.get_bills_by_restaurant(restaurant_id);
    }

    @POST
    @Path("add_table_to_restaurant/{restaurant_id}/{table_number}/{capacity}")
    public void add_table_to_restaurant(
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("table_number") Integer table_number,
            @PathParam("capacity") Integer capacity) {

        System.err.println("inside table rest &&&&&&&&&&&&&&&&&&&&&");
        System.err.println(restaurant_id + " uu " + table_number + " oo " + "capacity" + capacity);
        em.add_table_to_restaurant(restaurant_id, table_number, capacity);
    }

//    @PUT
//    @Path("update_table_to_restaurant/{table_id}/{restaurant_id}/{table_number}/{capacity}")
//    public void update_table_to_restaurant(
//            @PathParam("table_id") Integer table_id,
//            @PathParam("restaurant_id") Integer restaurant_id,
//            @PathParam("table_number") Integer table_number,
//            @PathParam("capacity") Integer capacity) {
//        em.update_table_to_restaurant(table_id, restaurant_id, table_number, capacity);
//    }
    @PUT
    @Path("update_table_to_restaurant/{table_id}")
    @Consumes(MediaType.APPLICATION_JSON)

    public void update_table_to_restaurant(@PathParam("table_id") Integer table_id, Tablemaster updatedTable) {

        em.update_table_to_restaurant(table_id, updatedTable.getRestaurantId().getRestaurantId(), updatedTable.getTableNumber(), updatedTable.getCapacity());
    }

    //Tablemaster updatedTable
    @DELETE
    @Path("delete_table_by_restaurant/{table_id}")
    public void delete_table_by_restaurant(@PathParam("table_id") Integer table_id) {

        em.delete_table_by_restaurant(table_id);
    }

    @GET
    @Path("get_tables_by_restaurant/{restaurant_id}")
    @Produces("application/json")
    public Response get_tables_by_restaurant(@PathParam("restaurant_id") Integer restaurant_id) {
        try {
            System.err.println("inside display -----------------" + restaurant_id);
            Collection<Tablemaster> klo = em.get_tables_by_restaurant(restaurant_id);
            System.err.println(klo);
            return Response.ok(klo).build(); // Return Response object with JSON
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred: " + e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @POST
    @Path("book_table_by_restaurant/{table_id}/{restaurant_id}/{booking_time}/{dine_in_time}/{booking_date}/{dine_in_date}/{no_of_peoples}/{contact_no}/{customer_name}")
    public void book_table_by_restaurant(
            @PathParam("table_id") Integer table_id,
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("booking_time") String booking_time,
            @PathParam("dine_in_time") String dine_in_time,
            @PathParam("booking_date") String booking_date,
            @PathParam("dine_in_date") String dine_in_date,
            @PathParam("no_of_peoples") Integer no_of_peoples,
            @PathParam("contact_no") String contact_no,
            @PathParam("customer_name") String customer_name) throws ParseException {

//        Time booking_timee = null;
//        Time dine_in_timee = null;
        Date booking_datee = null;
        Date dine_in_datee = null;
//
//        System.err.println("booking time ---- "+booking_time);
//        System.err.println("dine time ---- "+dine_in_time);
//        System.err.println("dine date ---- "+dine_in_date);
//        booking_timee = Time.valueOf(booking_time);
//        dine_in_timee = Time.valueOf(dine_in_time);
        booking_datee = new SimpleDateFormat("yyyy-MM-dd").parse(booking_date);
        dine_in_datee = new SimpleDateFormat("yyyy-MM-dd").parse(dine_in_date);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalTime bookingTime = LocalTime.parse(booking_time, timeFormatter);
        LocalTime dineInTime = LocalTime.parse(dine_in_time, timeFormatter);

        System.err.println(" in rest time @@@@@ " + bookingTime);
        System.err.println(" in rest time @@@@@ " + bookingTime.getClass());

        System.err.println(" in rest date @@@@@ " + booking_datee);
        System.err.println(" in rest date @@@@@ " + booking_datee.getClass());

        em.book_table_by_restaurant(table_id, restaurant_id, bookingTime, dineInTime, booking_datee, dine_in_datee, no_of_peoples, contact_no, customer_name);
    }

    @POST
    @Path("update_table_by_restaurant/{table_booking_id}/{table_id}/{restaurant_id}/{booking_time}/{dine_in_time}/{booking_date}/{dine_in_date}/{no_of_peoples}/{contact_no}/{customer_name}")
    public void update_table_by_restaurant(
            @PathParam("table_booking_id") Integer table_booking_id,
            @PathParam("table_id") Integer table_id,
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("booking_time") String booking_time,
            @PathParam("dine_in_time") String dine_in_time,
            @PathParam("booking_date") String booking_date,
            @PathParam("dine_in_date") String dine_in_date,
            @PathParam("no_of_peoples") Integer no_of_peoples,
            @PathParam("contact_no") String contact_no,
            @PathParam("customer_name") String customer_name) throws ParseException {

//        Time booking_timee = null;
//        Time dine_in_timee = null;
        Date booking_datee = null;
        Date dine_in_datee = null;

//        booking_timee = Time.valueOf(booking_time);
//        dine_in_timee = Time.valueOf(dine_in_time);
        booking_datee = new SimpleDateFormat("yyyy-MM-dd").parse(booking_date);
        dine_in_datee = new SimpleDateFormat("yyyy-MM-dd").parse(dine_in_date);
        
         DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalTime bookingTime = LocalTime.parse(booking_time, timeFormatter);
        LocalTime dineInTime = LocalTime.parse(dine_in_time, timeFormatter);

        em.update_table_by_restaurant(table_booking_id, table_id, restaurant_id, bookingTime, dineInTime, booking_datee, dine_in_datee, no_of_peoples, contact_no, customer_name);
    }

    @DELETE
    @Path("delete_table_booking_by_restaurant/{table_booking_id}")
    public void delete_table_booking_by_restaurant(@PathParam("table_booking_id") Integer table_booking_id) {

        em.delete_table_booking_by_restaurant(table_booking_id);
    }

    @GET
    @Path("get_tablesbooking_by_restaurant/{restaurant_id}")
    @Produces("application/json")
    public Collection<Tablebooking> get_tablesbooking_by_restaurant(@PathParam("restaurant_id") Integer restaurant_id) {
        return em.get_tablesbooking_by_restaurant(restaurant_id);
    }

    @POST
    @Path("add_item_in_inventory/{restaurant_id}/{quantity}/{amount}/{date}/{time}/{description}/{transaction_id}")
    public void add_item_in_inventory(
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("quantity") String quantity,
            @PathParam("amount") Integer amount,
            @PathParam("date") String d,
            @PathParam("time") Date time,
            @PathParam("description") String description,
            @PathParam("transaction_id") Integer transaction_id) throws ParseException {

        Date date = null;

        date = new SimpleDateFormat("yyyy-MM-dd").parse(d);

        em.add_item_in_inventory(restaurant_id, quantity, amount, date, time, description, transaction_id);

    }

    @PUT
    @Path("update_item_to_inventory/{inventoryid}/{restaurant_id}/{quantity}/{amount}/{date}/{time}/{description}/{transaction_id}")
    public void update_item_to_inventory(
            @PathParam("inventoryid") Integer inventoryid,
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("quantity") String quantity,
            @PathParam("amount") Integer amount,
            @PathParam("date") String d,
            @PathParam("time") Date time,
            @PathParam("description") String description,
            @PathParam("transaction_id") Integer transaction_id) throws ParseException {

        Date date = null;

        date = new SimpleDateFormat("yyyy-MM-dd").parse(d);

        em.update_item_to_inventory(inventoryid, restaurant_id, quantity, amount, date, time, description, transaction_id);
    }

    @DELETE
    @Path("delete_item_in_inventory/{inventory_id}")
    public void delete_item_in_inventory(@PathParam("inventory_id") Integer inventory_id) {

        em.delete_item_in_inventory(inventory_id);
    }

    @GET
    @Path("get_inventory_item_by_restaurant/{restaurant_id}")
    @Produces("application/json")
    public Collection<Inventorymaster> get_inventory_item_by_restaurant(@PathParam("restaurant_id") Integer restaurant_id) {
        return em.get_inventory_item_by_restaurant(restaurant_id);
    }

    @POST
    @Path("add_staff_to_restaurant/{restaurant_id}/{name}/{surname}/{age}/{salary}/{id_number}/{date_of_joining}/{image}/{id_image}")
    public void add_staff_to_restaurant(
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("name") String name,
            @PathParam("surname") String surname,
            @PathParam("age") Integer age,
            @PathParam("salary") Integer salary,
            @PathParam("id_number") String id_number,
            @PathParam("date_of_joining") String date_of_joining,
            @PathParam("image") String image,
            @PathParam("id_image") String id_image) throws ParseException {

        Date date = null;

        date = new SimpleDateFormat("yyyy-MM-dd").parse(date_of_joining);

        em.add_staff_to_restaurant(restaurant_id, name, surname, age, salary, id_number, date, image, id_image);
    }

    @PUT
    @Path("update_staff_to_restaurant/{Staff_id}/{restaurant_id}/{name}/{surname}/{age}/{salary}/{id_number}/{date_of_joining}/{image}/{id_image}")
    public void update_staff_to_restaurant(
            @PathParam("Staff_id") Integer Staff_id,
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("name") String name,
            @PathParam("surname") String surname,
            @PathParam("age") Integer age,
            @PathParam("salary") Integer salary,
            @PathParam("id_number") String id_number,
            @PathParam("date_of_joining") String date_of_joining,
            @PathParam("image") String image,
            @PathParam("id_image") String id_image) throws ParseException {

        Date date = null;

        date = new SimpleDateFormat("yyyy-MM-dd").parse(date_of_joining);

        em.update_staff_to_restaurant(Staff_id, restaurant_id, name, surname, age, salary, id_number, date, image, id_image);
    }

    @DELETE
    @Path("delete_staff_to_restaurant/{Staff_id}/{restaurant_id}")
    public void delete_staff_to_restaurant(
            @PathParam("Staff_id") Integer Staff_id,
            @PathParam("restaurant_id") Integer restaurant_id) {

        em.delete_staff_to_restaurant(Staff_id, restaurant_id);
    }

    @GET
    @Path("get_all_staff_by_restaurant/{restaurant_id}")
    @Produces("application/json")
    public Collection<Staffmaster> get_all_staff_by_restaurant(@PathParam("restaurant_id") Integer restaurant_id) {
        return em.get_all_staff_by_restaurant(restaurant_id);
    }

    @POST
    @Path("add_transaction_to_staff/{restaurant_id}/{staff_id}/{amount}/{date}/{transaction_id}")
    public void add_transaction_to_staff(
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("staff_id") Integer staff_id,
            @PathParam("amount") Integer amount,
            @PathParam("date") String date,
            @PathParam("transaction_id") Integer transaction_id) throws ParseException {

        Date d = null;

        d = new SimpleDateFormat("yyyy-MM-dd").parse(date);

        em.add_transaction_to_staff(restaurant_id, staff_id, amount, d, transaction_id);
    }

    @PUT
    @Path("update_transaction_to_staff/{staff_transaction_id}/{restaurant_id}/{staff_id}/{amount}/{date}/{transaction_id}")
    public void update_transaction_to_staff(
            @PathParam("staff_transaction_id") Integer staff_transaction_id,
            @PathParam("restaurant_id") Integer restaurant_id,
            @PathParam("staff_id") Integer staff_id,
            @PathParam("amount") Integer amount,
            @PathParam("date") String date,
            @PathParam("transaction_id") Integer transaction_id) throws ParseException {

        Date d = null;

        d = new SimpleDateFormat("yyyy-MM-dd").parse(date);

        em.update_transaction_to_staff(staff_transaction_id, restaurant_id, staff_id, amount, d, transaction_id);
    }

    @DELETE
    @Path("delete_transaction_to_staff/{staff_transaction_id}")
    public void delete_transaction_to_staff(@PathParam("staff_transaction_id") Integer staff_transaction_id) {

        em.delete_transaction_to_staff(staff_transaction_id);
    }

    @GET
    @Path("get_all_staff_transaction_by_restaurant/{restaurant_id}")
    @Produces("application/json")
    public Collection<StaffTransaction> get_all_staff_transaction_by_restaurant(@PathParam("restaurant_id") Integer restaurant_id) {
        return em.get_all_staff_transaction_by_restaurant(restaurant_id);
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

    @GET
    @Path("get_all_transaction_by_restaurant/{restaurant_id}")
    @Produces("application/json")
    public Collection<Transactionmaster> get_all_transaction_by_restaurant(@PathParam("restaurant_id") Integer restaurant_id) {
        return em.get_all_transaction_by_restaurant(restaurant_id);
    }

    @POST
    @Path("add_user_of_restaurant/{username}/{password}/{restaurant_id}/{role}")
    public void add_user_of_restaurant(@PathParam("username") String username, @PathParam("password") String password, @PathParam("restaurant_id") Integer restaurant_id, @PathParam("role") String role) {

        System.out.println("inside user rest ");
        System.out.println(username);
        System.out.println(password);
        System.out.println(restaurant_id);
        System.out.println(role);

        em.add_user_of_restaurant(username, password, restaurant_id, role);

    }
}
