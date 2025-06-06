/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import entity.Billmaster;
import entity.Categorymaster;
import entity.Inventorymaster;
import entity.Menumaster;
import entity.Ordermaster;
import entity.Restaurantmaster;
import entity.StaffTransaction;
import entity.Staffmaster;
import entity.Tablebooking;
import entity.Tablemaster;
import entity.Transactionmaster;
import jakarta.ejb.Local;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
@Local
public interface ejb_restaurant_crudLocal {
    
        Tablemaster search_table(Integer rid , Integer table_no);
        Collection<Menumaster> searchmenubycategory(Integer categoryid , Integer rid);

        Integer add_restaurant(String restaurant_name, String restaurant_address, String restaurant_contactno, String restaurant_email, String restaurant_city, String restaurant_state, String restaurant_country, Integer restaurant_pincode, Date created_at, Date updated_at, Boolean is_active);
        void delete_restaurant(Integer restaurant_id);
        void update_restaurant(Integer restaurant_id , String restaurant_name, String restaurant_address, String restaurant_contactno, String restaurant_email, String restaurant_city, String restaurant_state, String restaurant_country, Integer restaurant_pincode, Date created_at, Date updated_at, Boolean is_active);
        Restaurantmaster search_restaurant(Integer restaurant_id);
        
        
        void add_menu(Integer restaurant_id, Integer category_id, String item_name, Integer item_price,String description, Boolean is_avalaible,Date updated_at,String item_type);
        void delete_menu(Integer menu_id);
        void update_menu(Integer menu_id,Integer restaurant_id, Integer category_id, String item_name, Integer item_price,String description, Boolean is_avalaible,Date updated_at,String item_type);
        Menumaster search_menu(String itemname , Integer restaurantId);
        Collection<Menumaster> get_all_menuitems_by_restaurant(Integer restaurant_id);
        
        
        void add_category(Integer restaurant_id,String category_name, Date updated_at);
        void delete_category(Integer category_id);
        void update_category(Integer category_id , Integer restaurant_id,String category_name, Date updated_at);
        Collection<Categorymaster> get_categories_by_restaurant(Integer restaurant_id );
        
        
//        void add_order_to_restaurant(Integer restaurant_id,Date oreder_date, Integer table_id , Integer noofpeople ,List<Integer> menuids ,List<Integer> quantity);
//        //void update_order_to_restaurant(Integer order_id , Integer restaurant_id,Date oreder_date, Integer table_id , Integer noofpeople ,Collection<Integer> menuids);
//        public void update_item_to_order(Integer ordermenuid,Integer menuid ,Integer qunatity);
//        public void delete_item_to_order(Integer ordermenuid,Integer menuid );
//        public void add_item_to_order(Integer ordermenuid,Integer menuid ,Integer qunatity);

        void delete_order(Integer orderid);
        Collection<Ordermaster> get_orders_by_restaurant(Integer restaurant_id);
        
        
        //void add_bill_to_restaurant(Integer order_id, Integer restaurant_id,Integer total_amount,Integer discount,Integer final_amount,Integer final_payble_amount_with_tax , Date datetime, String modeofpayment , Integer transaction_id);
        Collection<Billmaster> get_bills_by_restaurant(Integer restaurant_id);
        
        
        void add_table_to_restaurant(Integer restaurant_id, Integer table_number, Integer capacity);
        void update_table_to_restaurant(Integer table_id,Integer restaurant_id, Integer table_number, Integer capacity);
        void delete_table_by_restaurant(Integer table_id);
        Collection<Tablemaster> get_tables_by_restaurant(Integer restaurant_id);
        
        
        void book_table_by_restaurant(Integer table_id , Integer restaurant_id ,LocalTime booking_time, LocalTime dine_in_time,Date booking_date,Date dine_in_date,Integer no_of_peoples,String contact_no, String customer_name);
        void update_table_by_restaurant(Integer table_booking_id ,Integer table_id , Integer restaurant_id ,LocalTime booking_time, LocalTime dine_in_time,Date booking_date,Date dine_in_date,Integer no_of_peoples,String contact_no, String customer_name);
        void delete_table_booking_by_restaurant(Integer table_booking_id);
        Collection<Tablebooking> get_tablesbooking_by_restaurant(Integer restaurant_id);
        
        
        void add_item_in_inventory(Integer restaurant_id,String quantity, Integer amount,Date date,Date time,String description,Integer transaction_id);
        void update_item_to_inventory(Integer inventoryid,Integer restaurant_id,String quantity, Integer amount,Date date,Date time,String description,Integer transaction_id);
        void delete_item_in_inventory(Integer inventory_id);
        Collection<Inventorymaster> get_inventory_item_by_restaurant(Integer restaurant_id);
        
        void add_staff_to_restaurant(Integer restaurant_id, String name,String surname, Integer age,Integer salary, String id_number,Date date_of_joining,String image, String id_image);
        void update_staff_to_restaurant(Integer Staff_id,Integer restaurant_id, String name,String surname, Integer age,Integer salary, String id_number,Date date_of_joining,String image, String id_image);
        void delete_staff_to_restaurant(Integer Staff_id , Integer restaurant_id);
        Collection<Staffmaster> get_all_staff_by_restaurant(Integer restaurant_id);
        
        void add_transaction_to_staff(Integer restaurant_id,Integer staff_id, Integer amount,Date date,Integer transaction_id);
        void update_transaction_to_staff(Integer staff_transaction_id , Integer restaurant_id,Integer staff_id, Integer amount,Date date,Integer transaction_id);
        void delete_transaction_to_staff(Integer staff_transaction_id);
        Collection<StaffTransaction> get_all_staff_transaction_by_restaurant(Integer restaurant_id);
        
        void add_transaction_to_restaurant(Integer restaurant_id,Integer amount, String transaction_type,String description,Date Date,Time time);
        Collection<Transactionmaster> get_all_transaction_by_restaurant(Integer restaurant_id);

        void add_user_of_restaurant(String username , String password ,Integer restaurant_id , String role );

}
