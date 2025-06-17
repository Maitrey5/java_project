/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import client.realclientforwaiters;
import client.updatedadminclient;
import entity.Categorymaster;
import entity.Menumaster;
import entity.OrderMenuJointable;
import entity.Ordermaster;
import entity.Tablemaster;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import record.KeepRecord;
import rest.orderdetails;

/**
 *
 * @author Admin
 */
@Named(value = "orderBean")
@ViewScoped

public class OrderBean implements Serializable {

    @Inject
    KeepRecord keepRecord;

    @Inject
    private Navigationbean navbean;  // Inject OrderBean here

    updatedadminclient em = new updatedadminclient();
    Response rs;

    realclientforwaiters ew = new realclientforwaiters();

    GenericType<Collection<Menumaster>> gmenus = new GenericType<Collection<Menumaster>>() {
    };

    GenericType<Collection<Categorymaster>> gcategory = new GenericType<Collection<Categorymaster>>() {
    };
    Collection<Categorymaster> category = new ArrayList<>();
    Collection<Categorymaster> categoryList = new ArrayList<>();

    GenericType<Collection<Tablemaster>> gtables = new GenericType<Collection<Tablemaster>>() {
    };
    Collection<Tablemaster> tables = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();

    Collection<Menumaster> menus = new ArrayList<>();

    Collection<OrderMenuJointable> oo;
    GenericType<Collection<OrderMenuJointable>> ooo = new GenericType<Collection<OrderMenuJointable>>() {
    };

    private String paymentMethod;

    /**
     * Creates a new instance of OrderBean
     */
    public OrderBean() {
    }

    private Collection<Menumaster> allItems = new ArrayList<>();
//    private List<MenuItem> appetizers;
    private Collection<Menumaster> orderItems = new ArrayList<>();
    private Integer selectedTable;
    private Integer price;
    private String noofpeoples;
    boolean flag = false;
    Integer oderid;
    Ordermaster selectedorder;
    Integer discountPercentage;

    //private List<String> tables = Arrays.asList("T1", "T2", "T3", "T4");
    @PostConstruct
    public void init() {
        rs = em.get_all_menuitems_by_restaurant(Response.class, String.valueOf(keepRecord.getIi()));

        allItems = rs.readEntity(gmenus);
        gettabledata();
        categoryList = getcategorydata();
        System.err.println("iiiiiiiiiiiiiiiiiiiiiii" + categoryList);
    }

    public Collection<Menumaster> getItemsByCategory(Categorymaster c) {

        rs = em.searchmenubycategory(Response.class, String.valueOf(c.getCategoryid()), String.valueOf(keepRecord.getIi()));
        menus = rs.readEntity(gmenus);

        return menus;

    }

    public void incrementQuantity(Integer menuid) {
        map.put(menuid, map.getOrDefault(menuid, 0) + 1);
        calculatetotalprice();
    }

    public void decrementQuantity(Menumaster m) {
        int currentQty = map.getOrDefault(m.getMenuId(), 0);
        if (currentQty > 1) {
            map.put(m.getMenuId(), currentQty - 1);
        } else {
            map.remove(m.getMenuId());
            orderItems.remove(m);// optional
        }
        calculatetotalprice();
    }

    public void removequantity(Menumaster m) {
        map.remove(m.getMenuId());
        orderItems.remove(m);// optional
        calculatetotalprice();

    }

    public void gettabledata() {

        Integer k = keepRecord.getIi();
        rs = em.get_tables_by_restaurant(Response.class, String.valueOf(k));

        if (rs.getStatus() == 200) {
            this.tables = rs.readEntity(gtables);
        } else {
            System.err.println("Error response: " + rs.getStatus());
            System.err.println("Error body: " + rs.readEntity(String.class));
        }

    }

    public void calculatetotalprice() {
        Integer p = 0;
        for (Menumaster m : orderItems) {
            p = p + ((m.getItemPrice()) * map.get(m.getMenuId()));

        }
        this.price = p;
    }

    public Collection<Categorymaster> getcategorydata() {

        System.err.println("inside method!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        rs = em.get_categories_by_restaurant(Response.class, String.valueOf(keepRecord.getIi()));
//           System.err.println("❌ Error: " + rs.getStatus());
//               System.err.println("❌ Body: " + rs.readEntity(String.class)); // read HTML error as text

        category = rs.readEntity(gcategory);
        return category;
    }

    public void addToOrder(Menumaster item) {

        System.err.println("bkjbjkcbajkbckjabj" + item);
//        orderItems.add(item);
//        map.put(item.getMenuId(), 1);
//        calculatetotalprice();

        if (map.containsKey(item.getMenuId())) {

            incrementQuantity(item.getMenuId());

        } else {
            orderItems.add(item);
            map.put(item.getMenuId(), 1);
            calculatetotalprice();
        }

    }

    public void updateorder(Ordermaster order) {

        Collection<OrderMenuJointable> oo;
        GenericType<Collection<OrderMenuJointable>> ooo = new GenericType<Collection<OrderMenuJointable>>() {
        };
        navbean.goOrder();

        this.selectedTable = order.getTableId().getTableId();
        this.noofpeoples = String.valueOf(order.getNoofpeople());
        this.oderid = order.getOrderId();

        this.flag = true;
//        rs = ew.get_menuitems_by_order(Response.class,String.valueOf(order.getOrderId()));
//        oo = rs.readEntity(ooo);
//        
//        for(OrderMenuJointable l : oo)
//        {
//            orderItems.add(l.getMenuId());
//            map.put(l.getMenuId().getMenuId(),l.getQuantity());
//        }

    }

    public void vieworder(Ordermaster o) {

        navbean.goview();
        System.err.println("navvvvvvvvvvvvvvvvvvvvv");
       
        this.selectedorder = o;

        rs = ew.get_menuitems_by_order(Response.class, String.valueOf(o.getOrderId()));
        this.oo = rs.readEntity(ooo);
//        
        System.err.println("kokonxkkkkkkkkkkkkkkkk");
//        for(OrderMenuJointable l : oo)
//        {
//            orderItems.add(l.getMenuId());
//            map.put(l.getMenuId().getMenuId(),l.getQuantity());
//        }        

    }

    public void submitorder() {

        if (flag == true) {
            for (Menumaster oo : orderItems) {
                Integer k = map.get(oo.getMenuId());

                ew.add_item_to_order(String.valueOf(oderid), String.valueOf(oo.getMenuId()), String.valueOf(k));
            }

            orderItems = null;
            map = null;
            flag = false;
        } else {

            List<Integer> menuids = new ArrayList<>(map.keySet());
            List<Integer> quantities = new ArrayList<>(map.values());

            LocalDate d = LocalDate.now();
            String dateString = d.toString();

            orderdetails o = new orderdetails();

            o.setMenuids(menuids);
            o.setQuantity(quantities);
            System.err.println("price == " + price + "no-of-peoples " + noofpeoples);

            ew.add_order_to_restaurant(o, String.valueOf(keepRecord.getIi()), dateString, String.valueOf(selectedTable), noofpeoples, String.valueOf(price));

            orderItems = null;
            map = null;
        }
    }

    // Getters and setters...
    public Collection<Menumaster> getAllItems() {
        return allItems;
    }

    public Collection<Menumaster> getOrderItems() {
        return orderItems;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Collection<Tablemaster> getTables() {
        return tables;
    }

    public String getNoofpeoples() {
        return noofpeoples;
    }

    public void setNoofpeoples(String noofpeoples) {
        this.noofpeoples = noofpeoples;
    }

    public void setTables(Collection<Tablemaster> tables) {
        this.tables = tables;
    }

    public Integer getSelectedTable() {
        return selectedTable;
    }

    public void setSelectedTable(Integer selectedTable) {
        System.err.println("selected table  --" + selectedTable);
        this.selectedTable = selectedTable;
    }

    public Collection<Categorymaster> getCategory() {
        return category;
    }

    public void setCategory(Collection<Categorymaster> category) {
        this.category = category;
    }

    public Collection<Categorymaster> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(Collection<Categorymaster> categoryList) {
        this.categoryList = categoryList;
    }

    public Collection<Menumaster> getMenus() {
        return menus;
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }

    public void setMenus(Collection<Menumaster> menus) {
        this.menus = menus;
    }

    public Ordermaster getSelectedorder() {
        return selectedorder;
    }

    public void setSelectedorder(Ordermaster selectedorder) {
        this.selectedorder = selectedorder;
    }

    public Collection<OrderMenuJointable> getOo() {
        return oo;
    }

    public void setOo(Collection<OrderMenuJointable> oo) {
        this.oo = oo;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    
    public void generatebill(Ordermaster o)
    {
        ew.ordercompleted(String.valueOf(o.getOrderId()));
        
        int d = (o.getTotalamount()*discountPercentage)/100;
        int f = o.getTotalamount() - d ;
        
         LocalDate currentDate = LocalDate.now();
        
        // Format to desired pattern, e.g., "dd-MM-yyyy"
        DateTimeFormatter formatterr = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = currentDate.format(formatterr);
        
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Convert to string
        String formattedDateTime = currentDateTime.format(formatter);
       
        
        ew.add_transaction_to_restaurant(String.valueOf(keepRecord.getIi()),String.valueOf(f),"credit","bill",formattedDate,formattedDateTime);
        ew.add_bill_to_restaurant(String.valueOf(o.getOrderId()),String.valueOf(keepRecord.getIi()),String.valueOf(o.getTotalamount()),String.valueOf(discountPercentage),String.valueOf(f),String.valueOf(f), formattedDateTime,paymentMethod, String.valueOf(1));
        
        
    }
    

}
