/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import client.updatedadminclient;
import entity.Categorymaster;
import entity.Menumaster;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import record.KeepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "onlymenubean")
@ViewScoped
public class onlymenubean implements Serializable {

    /**
     * Creates a new instance of onlymenubean
     */
    @Inject
    KeepRecord keepRecord;

    updatedadminclient em = new updatedadminclient();
    Response rs;

    GenericType<Collection<Menumaster>> gmenus = new GenericType<Collection<Menumaster>>() {
    };
    Collection<Menumaster> menus = new ArrayList<>();

    private String menuname;
    private Integer price;
    private Integer selectedcategoryformenu;
    private Boolean editMode = false;
    private String description;
    private String foodtype;
    private boolean avability;
    private Boolean menudetails = false;
    private Boolean showformlist = false;
    private Boolean showmenulist = true;
    private String selectedCategoryFilter;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    Menumaster selectedmenu;

    public onlymenubean() {

//        menus = getmenudata();
    }

    @PostConstruct
    public void init() {
        if (keepRecord != null) {
            menus = getmenudata();
        } else {
            System.err.println("keepRecord is NULL in onlymenubean.init()");
        }
    }

    public void showFormlistfunc() {

        this.showmenulist = false;
        this.menudetails = false;
        this.showformlist = true;

    }

    public void showmenulistfunc() {

        this.menudetails = false;
        this.showformlist = false;
        this.showmenulist = true;

    }

    public void submitTable() {

        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(formatter);

        System.err.println("menu bean ------------ " + formatted + "oooooo" + menuname);

        if (editMode && selectedmenu != null) {
            // Update the selectedTable's fields
//            selectedcategory.setTableNumber(tableNumber);
//            selectedTable.setCapacity(capacity);
//
            System.err.println("###############################&&&&&&&&&&&&&&&&&&");
//            System.err.println(selectedcategory);
//            System.err.println(selectedcategory.getCategoryName());
//            System.err.println(selectedcategory.getCategoryid());
            System.err.println(keepRecord.getIi());
//
//            selectedTable.setCapacity(capacity);
//            selectedTable.setTableNumber(tableNumber);

//            em.update_category(String.valueOf(selectedcategory.getCategoryid()), String.valueOf(keepRecord.getIi()), this.categoryname, formatted);
            // Optionally update in DB
            // Reset edit mode
            editMode = false;
//            selectedcategory = null;
        } else {
            System.err.println("category bean ------------ " + formatted + "oooooo" + menuname);

            em.add_menu(String.valueOf(keepRecord.getIi()), String.valueOf(selectedcategoryformenu), menuname, String.valueOf(price), description, String.valueOf(true), formatted, foodtype);
            menus = getmenudata();
        }
    }

    public Collection<Menumaster> getmenudata() {

        GenericType<Collection<Menumaster>> gb = new GenericType<Collection<Menumaster>>() {
        };
        Collection<Menumaster> gg = new ArrayList<>();

        rs = em.get_all_menuitems_by_restaurant(Response.class, String.valueOf(keepRecord.getIi()));
        gg = rs.readEntity(gb);
        return gg;

    }

    public void filterMenuByCategory() {
        if (selectedCategoryFilter == null || selectedCategoryFilter.isEmpty()) {

            menus = getmenudata();

        } else {

            rs = em.searchmenubycategory(Response.class, selectedCategoryFilter, String.valueOf(keepRecord.getIi()));
            menus = rs.readEntity(gmenus);
        }
    }

    public void showmenudetails(Menumaster m) {
        this.showformlist = false;
        this.showmenulist =false;
        this.menudetails = true;
        this.selectedmenu = m;

        this.description = m.getDescription();
        this.foodtype = m.getItemType();
        this.menuname = m.getItemName();
        this.price = m.getItemPrice();
        this.selectedcategoryformenu = m.getCategoryId().getCategoryid();
        this.avability = m.getIsAvalaible();

    }

    public void updatemenu() {

        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(formatter);
        em.update_menu(String.valueOf(selectedmenu.getMenuId()), String.valueOf(keepRecord.getIi()), String.valueOf(selectedcategoryformenu), menuname, String.valueOf(price), description, String.valueOf(avability), formatted, foodtype);
        menus = getmenudata();
        showformlistfunc();
    }

    public void deletemenu() {
        em.delete_menu(String.valueOf(selectedmenu.getMenuId()));
        menus = getmenudata();
        showformlistfunc();

    }

    public void showformlistfunc() {
        this.showformlist = false;
        this.menudetails = false;
        this.showmenulist =true;

    }

    public String getMenuname() {
        return menuname;
    }

    public Boolean getShowmenulist() {
        return showmenulist;
    }

    public void setShowmenulist(Boolean showmenulist) {
        this.showmenulist = showmenulist;
    }
    

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSelectedcategoryformenu() {
        return selectedcategoryformenu;
    }

    public void setSelectedcategoryformenu(Integer selectedcategoryformenu) {
        this.selectedcategoryformenu = selectedcategoryformenu;
    }

    public Boolean getMenudetails() {
        return menudetails;
    }

    public void setMenudetails(Boolean menudetails) {
        this.menudetails = menudetails;
    }

    public Boolean getShowformlist() {
        return showformlist;
    }

    public void setShowformlist(Boolean showformlist) {
        this.showformlist = showformlist;
    }

    public Boolean getEditMode() {
        return editMode;
    }

    public void setEditMode(Boolean editMode) {
        this.editMode = editMode;
    }

    public Collection<Menumaster> getMenus() {
        return menus;
    }

    public void setMenus(Collection<Menumaster> menus) {
        this.menus = menus;
    }

    public boolean isAvability() {
        return avability;
    }

    public void setAvability(boolean avability) {
        this.avability = avability;
    }

    public String getSelectedCategoryFilter() {
        return selectedCategoryFilter;
    }

    public void setSelectedCategoryFilter(String selectedCategoryFilter) {
        this.selectedCategoryFilter = selectedCategoryFilter;
    }

}
