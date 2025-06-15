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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void submitorder() {

        List<Integer> menuids = new ArrayList<>(map.keySet());
        List<Integer> quantities = new ArrayList<>(map.values());

        LocalDate d = LocalDate.now();
        String dateString = d.toString();

        orderdetails o = new orderdetails();

        o.setMenuids(menuids);
        o.setQuantity(quantities);
        System.err.println("price == " + price + "no-of-peoples " + noofpeoples);

        ew.add_order_to_restaurant(o, String.valueOf(keepRecord.getIi()), dateString, String.valueOf(selectedTable), noofpeoples, String.valueOf(price));
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

}
