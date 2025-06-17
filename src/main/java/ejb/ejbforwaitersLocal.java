/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import entity.Billmaster;
import entity.Menumaster;
import entity.OrderMenuJointable;
import entity.Ordermaster;
import jakarta.ejb.Local;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
@Local
public interface ejbforwaitersLocal {

    Menumaster search_menu(String itemname, Integer restaurantId);
    Menumaster search_menu_by_id(Integer mid);
    void ordercompleted(Integer oderid);

    Collection<Menumaster> get_all_menuitems_by_restaurant(Integer restaurant_id);

    void add_order_to_restaurant(Integer restaurant_id, Date oreder_date, Integer table_id, Integer noofpeople, Integer amount, List<Integer> menuids, List<Integer> quantity);
    //void update_order_to_restaurant(Integer order_id , Integer restaurant_id,Date oreder_date, Integer table_id , Integer noofpeople ,Collection<Integer> menuids);

    public void update_item_to_order(Integer ordermenuid, Integer menuid, Integer qunatity);

    public void delete_item_to_order(Integer ordermenuid, Integer menuid);

    public void add_item_to_order(Integer ordermenuid, Integer menuid, Integer qunatity);

    Collection<OrderMenuJointable> get_menuitems_by_order(Integer orderid);

    void delete_order(Integer orderid);

    Collection<Ordermaster> get_orders_by_restaurant(Integer restaurant_id);

    void add_bill_to_restaurant(Integer order_id, Integer restaurant_id, Integer total_amount, Integer discount, Integer final_amount, Integer final_payble_amount_with_tax, Timestamp datetime, String modeofpayment, Integer transaction_id);

    Collection<Billmaster> get_bills_by_restaurant(Integer restaurant_id);

    void add_transaction_to_restaurant(Integer restaurant_id, Integer amount, String transaction_type, String description, Date Date, Timestamp time);

}
