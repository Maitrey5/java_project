/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.Billmaster;
import entity.Categorymaster;
import entity.Groups;
import entity.Inventorymaster;
import entity.Menumaster;
import entity.OrderMenuJointable;
import entity.Ordermaster;
import entity.Restaurantmaster;
import entity.StaffTransaction;
import entity.Staffmaster;
import entity.Tablebooking;
import entity.Tablemaster;
import entity.Transactionmaster;
import entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
@Stateless
public class ejb_restaurant_crud implements ejb_restaurant_crudLocal {

    @PersistenceContext(unitName = "restaurant_pu")
    EntityManager em;

    @Inject
    Pbkdf2PasswordHash passwordHasher;

    @PostConstruct
    public void init() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "2048");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA256");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");

        passwordHasher.initialize(parameters);
        System.out.println("✅ init() called: PasswordHasher initialized");

    }

    
    

    @Override
    public void add_user_of_restaurant(String username, String password, Integer restaurant_id, String role) {

        init();
        String hashpass = passwordHasher.generate(password.toCharArray());

        //Pbkdf2PasswordHashImpl pb;
        // Insert Student
//        pb = new Pbkdf2PasswordHashImpl();
//        String hashpass = pb.generate(password.toCharArray());
        System.err.println(hashpass);
        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);
        System.out.println(username);
        System.out.println("pppppppppppppppppppppppppp");
        System.out.println(hashpass);
        System.out.println(restaurant_id);
        System.out.println(role);

        User u = new User();
        u.setUsername(username);
        u.setPassword(hashpass);
        u.setRestaurantId(r);

        em.persist(u);

        Collection<User> uu = r.getUserCollection();

        uu.add(u);

        r.setUserCollection(uu);

        Groups g = new Groups();

        g.setUsername(username);
        g.setRoles(role);

        em.persist(g);

    }

    @Override
    public Integer add_restaurant(String restaurant_name, String restaurant_address, String restaurant_contactno, String restaurant_email, String restaurant_city, String restaurant_state, String restaurant_country, Integer restaurant_pincode, Date created_at, Date updated_at, Boolean is_active) {

        Restaurantmaster r = new Restaurantmaster();
        r.setRestaurantName(restaurant_name);
        r.setRestaurantAddress(restaurant_address);
        r.setRestaurantContactno(restaurant_contactno);
        r.setRestaurantEmail(restaurant_email);
        r.setRestaurantCity(restaurant_city);
        r.setRestaurantState(restaurant_state);
        r.setRestaurantCountry(restaurant_country);
        r.setRestaurantPincode(restaurant_pincode);
        r.setCreatedAt(created_at);
        r.setUpdatedAt(updated_at);
        r.setIsActive(is_active);

        em.persist(r);

        em.flush(); // 👈 Force JPA to push to DB and generate the ID

        Integer idd = r.getRestaurantId();
        System.out.println(idd);
        System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiis");

        return idd;
    }

    @Override
    public void delete_restaurant(Integer restaurant_id) {

        Restaurantmaster removerestaurant = (Restaurantmaster) em.find(Restaurantmaster.class, restaurant_id);

        em.remove(removerestaurant);
    }

    @Override
    public void update_restaurant(Integer restaurant_id, String restaurant_name, String restaurant_address, String restaurant_contactno, String restaurant_email, String restaurant_city, String restaurant_state, String restaurant_country, Integer restaurant_pincode, Date created_at, Date updated_at, Boolean is_active) {

        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class, restaurant_id);

        r.setRestaurantName(restaurant_name);
        r.setRestaurantAddress(restaurant_address);
        r.setRestaurantContactno(restaurant_contactno);
        r.setRestaurantEmail(restaurant_email);
        r.setRestaurantCity(restaurant_city);
        r.setRestaurantState(restaurant_state);
        r.setRestaurantCountry(restaurant_country);
        r.setRestaurantPincode(restaurant_pincode);
        r.setCreatedAt(created_at);
        r.setUpdatedAt(updated_at);
        r.setIsActive(is_active);

    }

    @Override
    public Restaurantmaster search_restaurant(Integer restaurant_id) {

        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class, restaurant_id);

        return r;
    }
    
    @Override
    public Tablemaster search_table(Integer rid , Integer table_no){
    
        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class,rid);

        Tablemaster t = (Tablemaster) em.createNamedQuery("Tablemaster.findByTableId").setParameter("tableNumber",table_no).setParameter("restaurantId",r).getSingleResult();
        
        return t;
    }
    @Override
    public Collection<Menumaster> searchmenubycategory(Integer categoryid , Integer rid){
        
        Categorymaster c = (Categorymaster ) em.find(Categorymaster.class,categoryid);
        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class,rid);

        Collection<Menumaster> t = em.createNamedQuery("Menumaster.findByCategoryId").setParameter("categoryId",c).setParameter("restaurantId",r).getResultList();

        return t;
        
    }
    
    @Override
    public void add_menu(Integer restaurant_id, Integer category_id, String item_name, Integer item_price, String description, Boolean is_avalaible, Date updated_at, String item_type) {

        Menumaster m = new Menumaster();
        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class, restaurant_id);
        Categorymaster c = (Categorymaster) em.find(Categorymaster.class, category_id);

        m.setCategoryId(c);
        m.setRestaurantId(r);
        m.setDescription(description);
        m.setItemName(item_name);
        m.setItemPrice(item_price);
        m.setItemType(item_type);
        m.setIsAvalaible(is_avalaible);
        m.setUpdatedAt(updated_at);

        em.persist(c);

        Collection<Menumaster> col_menu_rst = r.getMenumasterCollection();
        col_menu_rst.add(m);
        r.setMenumasterCollection(col_menu_rst);

        Collection<Menumaster> col_menu_cat = c.getMenumasterCollection();
        col_menu_cat.add(m);
        c.setMenumasterCollection(col_menu_cat);

        em.merge(r);
        em.merge(c);

    }

    @Override
    public void delete_menu(Integer menu_id) {

        Menumaster m = (Menumaster) em.find(Menumaster.class, menu_id);

        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class, m.getRestaurantId().getRestaurantId());
        Categorymaster c = (Categorymaster) em.find(Categorymaster.class, m.getCategoryId().getCategoryid());

        Collection<Menumaster> col_menu_rst = r.getMenumasterCollection();
        col_menu_rst.remove(m);
        r.setMenumasterCollection(col_menu_rst);

        Collection<Menumaster> col_menu_cat = c.getMenumasterCollection();
        col_menu_cat.remove(m);
        c.setMenumasterCollection(col_menu_cat);

        //em.merge(r);
        //em.merge(c);
        em.remove(m);
    }

    @Override
    public void update_menu(Integer menu_id, Integer restaurant_id, Integer category_id, String item_name, Integer item_price, String description, Boolean is_avalaible, Date updated_at, String item_type) {

        Menumaster m = (Menumaster) em.find(Menumaster.class, menu_id);
        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class, restaurant_id);
        Categorymaster c = (Categorymaster) em.find(Categorymaster.class, category_id);
        m.setCategoryId(c);
        m.setRestaurantId(r);
        m.setDescription(description);
        m.setItemName(item_name);
        m.setItemPrice(item_price);
        m.setItemType(item_type);
        m.setIsAvalaible(is_avalaible);
        m.setUpdatedAt(updated_at);

    }

    @Override
    public Menumaster search_menu(String itemname, Integer restaurantId) {

        return (Menumaster) em.createNamedQuery("Menumaster.findByItemNameandrestaurantid").setParameter("itemName", itemname).setParameter("restaurantId", restaurantId).getSingleResult();

    }

    @Override
    public Collection<Menumaster> get_all_menuitems_by_restaurant(Integer restaurant_id) {

        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class, restaurant_id);

        return r.getMenumasterCollection();

    }

    @Override
    public void add_category(Integer restaurant_id, String category_name, Date updated_at) {

        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class, restaurant_id);

        Categorymaster c = new Categorymaster();

        c.setRestaurantId(r);
        c.setCategoryName(category_name);
        c.setUpdatedAt(updated_at);
        em.persist(c);

        Collection<Categorymaster> coll_cat_rst = r.getCategorymasterCollection();
        coll_cat_rst.add(c);
        r.setCategorymasterCollection(coll_cat_rst);

        em.merge(r);

    }

    @Override
    public void delete_category(Integer category_id) {

        Categorymaster c = (Categorymaster) em.find(Categorymaster.class, category_id);
        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class, c.getRestaurantId().getRestaurantId());

        Collection<Categorymaster> coll_cat_rst = r.getCategorymasterCollection();
        coll_cat_rst.remove(c);
        r.setCategorymasterCollection(coll_cat_rst);

        //em.merge(r);
        em.remove(c);
    }

    @Override
    public void update_category(Integer category_id, Integer restaurant_id, String category_name, Date updated_at) {

        Categorymaster c = (Categorymaster) em.find(Categorymaster.class, category_id);

        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class, restaurant_id);
        System.err.println("in ejbbbbbbbs "+category_id+"res  "+restaurant_id+"category name "+category_name+updated_at);
        c.setRestaurantId(r);
        c.setCategoryName(category_name);
        c.setUpdatedAt(updated_at);

        //em.merge(c);
    }

    @Override
    public Collection<Categorymaster> get_categories_by_restaurant(Integer restaurant_id) {

        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class, restaurant_id);

        return r.getCategorymasterCollection();

    }

//    @Override
//    public void add_order_to_restaurant(Integer restaurant_id, Date oreder_date, Integer table_id, Integer noofpeople, List<Integer> menuids, List<Integer> quantity) {
//
//        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class, restaurant_id);
//        Tablemaster t = (Tablemaster) em.find(Tablemaster.class, table_id);
//
//        Ordermaster o = new Ordermaster();
//
//        o.setRestaurantId(r);
//        o.setNoOfPeople(noofpeople);
//        o.setTableId(t);
//        o.setOrederDate(oreder_date);
//
//        Collection<OrderMenuJointable> coll_menu_ord = new ArrayList<>();
//
//        Integer i = 0;
//        Integer quantityofitem = 0;
//        
//        System.err.println("ooooooooooooooooooooooooooooooooooo");
//        System.out.println(menuids);
//        System.out.println(quantity);
//
//        for (Integer id : menuids) {
//            Menumaster m = (Menumaster) em.find(Menumaster.class, id);
//        System.err.println("oppppppppppppppp");
//
//            if (m != null) {
//                
//                        System.err.println("wwwwwwwwwwwwwwwwwwww");
//
//                
//                //coll_menu_ord.add(m);
//
//                quantityofitem = quantity.get(i);
//
//                OrderMenuJointable om = new OrderMenuJointable();
//
//                om.setMenuId(m);
//                om.setOrderId(o);
//                om.setQuantity(quantityofitem);
//                Integer totalprice = (quantityofitem) * (m.getItemPrice());
//
//                om.setTotalPrice(totalprice);
//
//                em.persist(om);
//
////              Collection<OrderMenuJointable> coll_order_menu = o.getOrderMenuJointableCollection();
//                coll_menu_ord.add(om);
//                o.setOrderMenuJointableCollection(coll_menu_ord);
//                //em.merge(t);
//
//                Collection<OrderMenuJointable> p = m.getOrderMenuJointableCollection();
//                p.add(om);
//                m.setOrderMenuJointableCollection(coll_menu_ord);
//
//            }
//        }
//
//        em.persist(o);
//
//        Collection<Ordermaster> coll_ord_rst = r.getOrdermasterCollection();
//        coll_ord_rst.add(o);
//        r.setOrdermasterCollection(coll_ord_rst);
//
//        Collection<Ordermaster> coll_ord_tbl = t.getOrdermasterCollection();
//        coll_ord_tbl.add(o);
//        t.setOrdermasterCollection(coll_ord_rst);
//
//    }
//
//    @Override
//    public void update_item_to_order(Integer ordermenuid, Integer menuid, Integer qunatity) {
//
//        Menumaster m = (Menumaster) em.find(Menumaster.class, menuid);
//
//        OrderMenuJointable om = (OrderMenuJointable) em.createNamedQuery("OrderMenuJointable.findByOrdermenuid").setParameter("ordermenuid", ordermenuid).getSingleResult();
//
//        om.setQuantity(qunatity);
//
//        Integer i = 0;
//        i = (m.getItemPrice()) * (qunatity);
//        om.setTotalPrice(i);
//
//        em.merge(om);
//
//    }
//
//    @Override
//    public void add_item_to_order(Integer orderid, Integer menuid, Integer qunatity) {
//
//        Menumaster m = (Menumaster) em.find(Menumaster.class, menuid);
//        Ordermaster oo = (Ordermaster) em.find(Ordermaster.class, orderid);
//
//        OrderMenuJointable orr = new OrderMenuJointable();
//        orr.setMenuId(m);
//        orr.setOrderId(oo);
//        orr.setQuantity(qunatity);
//
//        Integer i = 0;
//        i = (m.getItemPrice()) * (qunatity);
//        orr.setTotalPrice(i);
//
//        em.persist(orr);
//
//        Collection<OrderMenuJointable> mm = oo.getOrderMenuJointableCollection();
//        mm.add(orr);
//        oo.setOrderMenuJointableCollection(mm);
//
//        Collection<OrderMenuJointable> p = m.getOrderMenuJointableCollection();
//        p.add(orr);
//        m.setOrderMenuJointableCollection(p);
//
//    }
//
//    @Override
//    public void delete_item_to_order(Integer ordermenuid, Integer menuid) {
//
//        Menumaster m = (Menumaster) em.find(Menumaster.class, menuid);
//
//        OrderMenuJointable om = (OrderMenuJointable) em.createNamedQuery("OrderMenuJointable.findByOrdermenuid").setParameter("ordermenuid", ordermenuid).getSingleResult();
//
//        em.remove(om);
//
//        Collection<OrderMenuJointable> Coll_item_menu = m.getOrderMenuJointableCollection();
//        Coll_item_menu.remove(om);
//
//        em.merge(m);
//
//    }
    @Override
    public void delete_order(Integer orderid) {

        Ordermaster om = em.find(Ordermaster.class, orderid);

        Restaurantmaster r = om.getRestaurantId();
        Collection<Ordermaster> rr = r.getOrdermasterCollection();
        rr.remove(om);
        em.merge(r);

        Tablemaster tr = om.getTableId();
        Collection<Ordermaster> rrr = tr.getOrdermasterCollection();
        rrr.remove(om);
        em.merge(tr);

        Collection<OrderMenuJointable> ee = om.getOrderMenuJointableCollection();

        for (OrderMenuJointable k : ee) {
            em.remove(k);
        }

        em.remove(om);

    }

    @Override
    public Collection<Ordermaster> get_orders_by_restaurant(Integer restaurant_id) {

        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class, restaurant_id);

        return r.getOrdermasterCollection();
    }

//    @Override
//    public void add_bill_to_restaurant(Integer order_id, Integer restaurant_id, Integer total_amount, Integer discount, Integer final_amount, Integer final_payble_amount_with_tax, Date datetime, String modeofpayment, Integer transaction_id) {
//
//        Ordermaster om = em.find(Ordermaster.class, order_id);
//        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);
//        Transactionmaster tr = em.find(Transactionmaster.class, transaction_id);
//
//        Billmaster b = new Billmaster();
//        b.setRestaurantId(r);
//        b.setOrderId(om);
//        b.setTotalAmount(total_amount);
//        b.setDiscount(discount);
//        b.setFinalAmount(final_amount);
//        b.setFinalPaybleAmountWithTax(final_payble_amount_with_tax);
//        b.setDateTime(datetime);
//        b.setModeOfPayment(modeofpayment);
//        b.setTransactionId(tr);
//
//        em.persist(b);
//
//        Collection<Billmaster> bm = om.getBillmasterCollection();
//        bm.add(b);
//        om.setBillmasterCollection(bm);
//
//        Collection<Billmaster> bmr = r.getBillmasterCollection();
//        bmr.add(b);
//        r.setBillmasterCollection(bmr);
//
//        Collection<Billmaster> trc = tr.getBillmasterCollection();
//        trc.add(b);
//        tr.setBillmasterCollection(trc);
//
//    }
//
    @Override
    public Collection<Billmaster> get_bills_by_restaurant(Integer restaurant_id) {

        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);
        return r.getBillmasterCollection();

    }

    @Override
    public void add_table_to_restaurant(Integer restaurant_id, Integer table_number, Integer capacity) {
        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);
        
        System.err.println("in ejb");
        System.err.println(restaurant_id);
        System.err.println(table_number);
        System.err.println(capacity);

        Tablemaster tb = new Tablemaster();

        tb.setRestaurantId(r);
        tb.setTableNumber(table_number);
        tb.setCapacity(capacity);

        em.persist(r);

        Collection<Tablemaster> bm = r.getTablemasterCollection();
        bm.add(tb);
        r.setTablemasterCollection(bm);

    }

    @Override
    public void update_table_to_restaurant(Integer table_id, Integer restaurant_id, Integer table_number, Integer capacity) {

        Tablemaster tb = em.find(Tablemaster.class, table_id);

        tb.setCapacity(capacity);
        tb.setTableNumber(table_number);

    }

    @Override
    public void delete_table_by_restaurant(Integer table_id) {

        Tablemaster tb = em.find(Tablemaster.class, table_id);

        Restaurantmaster r = tb.getRestaurantId();
        Collection<Tablemaster> tt = r.getTablemasterCollection();
        tt.remove(tb);
        r.setTablemasterCollection(tt);

        em.remove(tb);

    }

    @Override
    public Collection<Tablemaster> get_tables_by_restaurant(Integer restaurant_id) {
        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);
        return r.getTablemasterCollection();

    }

    @Override
    public void book_table_by_restaurant(Integer table_id, Integer restaurant_id, LocalTime booking_time, LocalTime dine_in_time, Date booking_date, Date dine_in_date, Integer no_of_peoples, String contact_no, String customer_name) {

        Tablemaster tb = em.find(Tablemaster.class, table_id);

        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);

        System.err.println(" in ejd time @@@@@ "+booking_time);
        System.err.println(" in ejd time @@@@@ "+booking_time.getClass());

        System.err.println(" in ejd date @@@@@ "+booking_date);
        System.err.println(" in ejd date @@@@@ "+booking_date.getClass());
        Tablebooking tt = new Tablebooking();
        tt.setRestaurantId(r);
        tt.setTableId(tb);
        tt.setBookingDate(booking_date);
        tt.setBookingTime(booking_time);
        tt.setDineInDate(dine_in_date);
        tt.setDineInTime(dine_in_time);
        tt.setNoofpeoples(no_of_peoples);
        tt.setCustomername(customer_name);
        tt.setContactno(contact_no);

        em.persist(tt);

        Collection<Tablebooking> cr = r.getTablebookingCollection();
        cr.add(tt);
        r.setTablebookingCollection(cr);

    }

    @Override
    public void update_table_by_restaurant(Integer table_booking_id, Integer table_id, Integer restaurant_id, LocalTime booking_time, LocalTime dine_in_time, Date booking_date, Date dine_in_date, Integer no_of_peoples, String contact_no, String customer_name) {

        Tablemaster tb = em.find(Tablemaster.class, table_id);
        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);

        Tablebooking tt = em.find(Tablebooking.class, table_booking_id);
        tt.setTableId(tb);

        tt.setBookingDate(booking_date);
        tt.setBookingTime(booking_time);
        tt.setDineInDate(dine_in_date);
        tt.setDineInTime(dine_in_time);
        tt.setNoofpeoples(no_of_peoples);
        tt.setCustomername(customer_name);
        tt.setContactno(contact_no);

//        Collection<Tablebooking> cr = r.getTablebookingCollection();
//        cr.remove(tt);
//        r.setTablebookingCollection(cr);
    }

    @Override
    public void delete_table_booking_by_restaurant(Integer table_booking_id) {
        Tablebooking tt = em.find(Tablebooking.class, table_booking_id);
        em.remove(tt);

        Collection<Tablebooking> cr = tt.getRestaurantId().getTablebookingCollection();
        cr.remove(tt);
        tt.getRestaurantId().setTablebookingCollection(cr);

    }

    @Override
    public Collection<Tablebooking> get_tablesbooking_by_restaurant(Integer restaurant_id) {

        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);

        return r.getTablebookingCollection();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void add_item_in_inventory(Integer restaurant_id, String quantity, Integer amount, Date date, Date time, String description, Integer transaction_id) {
        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);
        Transactionmaster t = em.find(Transactionmaster.class, transaction_id);

        Inventorymaster i = new Inventorymaster();
        i.setRestaurantId(r);
        i.setAmount(amount);
        i.setDate(date);
        i.setQuantity(quantity);
        i.setTime(time);
        i.setDescription(description);
        i.setTransactionId(t);

        em.persist(i);

        Collection<Inventorymaster> tt = r.getInventorymasterCollection();
        tt.add(i);
        r.setInventorymasterCollection(tt);

        Collection<Inventorymaster> tr = t.getInventorymasterCollection();
        tr.add(i);
        r.setInventorymasterCollection(tr);
    }

    @Override
    public void update_item_to_inventory(Integer inventoryid, Integer restaurant_id, String quantity, Integer amount, Date date, Date time, String description, Integer transaction_id) {

        Inventorymaster i = em.find(Inventorymaster.class, inventoryid);
        Transactionmaster t = em.find(Transactionmaster.class, transaction_id);

        i.setAmount(amount);
        i.setQuantity(quantity);
        i.setDate(date);
        i.setTime(time);
        i.setDescription(description);
        i.setTransactionId(t);

    }

    @Override
    public void delete_item_in_inventory(Integer inventory_id) {

        Inventorymaster i = em.find(Inventorymaster.class, inventory_id);
        em.remove(i);

    }

    @Override
    public Collection<Inventorymaster> get_inventory_item_by_restaurant(Integer restaurant_id) {

        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);

        return r.getInventorymasterCollection();

    }

    @Override
    public void add_staff_to_restaurant(Integer restaurant_id, String name, String surname, Integer age, Integer salary, String id_number, Date date_of_joining, String id_type, String position) {

        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);

        Staffmaster s = new Staffmaster();

        s.setRestaurantId(r);
        s.setAge(age);
        s.setName(name);
        s.setSurname(surname);
        s.setSalary(salary);
        s.setIdNumber(id_number);
        s.setDateOfJoining(date_of_joining);
        s.setId_type(id_type);
        s.setPosition(position);

        em.persist(s);

        Collection<Staffmaster> tt = r.getStaffmasterCollection();
        tt.add(s);
        r.setStaffmasterCollection(tt);

    }

    @Override
    public void update_staff_to_restaurant(Integer Staff_id, Integer restaurant_id, String name, String surname, Integer age, Integer salary, String id_number, Date date_of_joining, String id_type, String position) {

        Staffmaster s = em.find(Staffmaster.class, Staff_id);

        System.err.println("hello in ejb");

        s.setAge(age);
        s.setName(name);
        s.setSurname(surname);
        s.setSalary(salary);
        s.setIdNumber(id_number);
        s.setDateOfJoining(date_of_joining);
        s.setId_type(id_type);
        s.setPosition(position);

    }

    @Override
    public void delete_staff_to_restaurant(Integer Staff_id, Integer restaurant_id) {
        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);

        Staffmaster s = em.find(Staffmaster.class, Staff_id);
        em.remove(s);

        Collection<Staffmaster> tt = r.getStaffmasterCollection();
        tt.remove(s);
        r.setStaffmasterCollection(tt);

    }

    @Override
    public Collection<Staffmaster> get_all_staff_by_restaurant(Integer restaurant_id) {

        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);
        return r.getStaffmasterCollection();

    }

    @Override
    public void add_transaction_to_staff(Integer restaurant_id, Integer staff_id, Integer amount, Date date, Integer transaction_id) {
        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);

        Transactionmaster t = em.find(Transactionmaster.class, transaction_id);

        Staffmaster ss = em.find(Staffmaster.class, staff_id);

        StaffTransaction s = new StaffTransaction();

        s.setAmount(amount);
        s.setDate(date);
        s.setRestaurantId(r);
        s.setStaffId(ss);
        s.setTransactionId(t);

        Collection<StaffTransaction> kl = ss.getStaffTransactionCollection();
        kl.add(s);
        ss.setStaffTransactionCollection(kl);

        Collection<StaffTransaction> faf = r.getStaffTransactionCollection();
        faf.add(s);
        r.setStaffTransactionCollection(faf);

        Collection<StaffTransaction> jfm = t.getStaffTransactionCollection();
        jfm.add(s);
        r.setStaffTransactionCollection(jfm);

    }

    @Override
    public void update_transaction_to_staff(Integer staff_transaction_id, Integer restaurant_id, Integer staff_id, Integer amount, Date date, Integer transaction_id) {

        Staffmaster ss = em.find(Staffmaster.class, staff_id);
        StaffTransaction s = em.find(StaffTransaction.class, staff_transaction_id);

        s.setAmount(amount);
        s.setDate(date);
        s.setStaffId(ss);

    }

    @Override
    public void delete_transaction_to_staff(Integer staff_transaction_id) {

        StaffTransaction s = em.find(StaffTransaction.class, staff_transaction_id);

        Restaurantmaster r = em.find(Restaurantmaster.class, s.getRestaurantId().getRestaurantId());

        Transactionmaster t = em.find(Transactionmaster.class, s.getTransactionId().getTransactionId());

        Staffmaster ss = em.find(Staffmaster.class, s.getStaffId().getStaffid());

        em.remove(s);

        Collection<StaffTransaction> kl = ss.getStaffTransactionCollection();
        kl.remove(s);
        ss.setStaffTransactionCollection(kl);

        Collection<StaffTransaction> faf = r.getStaffTransactionCollection();
        faf.remove(s);
        r.setStaffTransactionCollection(faf);

        Collection<StaffTransaction> jfm = t.getStaffTransactionCollection();
        jfm.remove(s);
        r.setStaffTransactionCollection(jfm);

    }

    @Override
    public Collection<StaffTransaction> get_all_staff_transaction_by_restaurant(Integer restaurant_id) {

        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);

        return r.getStaffTransactionCollection();
    }

    @Override
    public void add_transaction_to_restaurant(Integer restaurant_id, Integer amount, String transaction_type, String description, Date Date, Time time) {

        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);

        Transactionmaster t = new Transactionmaster();

        t.setAmount(amount);
        t.setRestaurantId(r);
        t.setDate(Date);
        t.setDescription(description);
        t.setTransactionType(transaction_type);
        t.setTime(time);

        em.persist(t);

        Collection<Transactionmaster> faf = r.getTransactionmasterCollection();
        faf.add(t);
        r.setTransactionmasterCollection(faf);

    }

    @Override
    public Collection<Transactionmaster> get_all_transaction_by_restaurant(Integer restaurant_id) {

        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);

        return r.getTransactionmasterCollection();
    }

}