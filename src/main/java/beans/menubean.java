/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import client.updatedadminclient;
import entity.Categorymaster;
import entity.Tablemaster;
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
@Named(value = "menubean")
@ViewScoped
public class menubean implements Serializable {

    /**
     * Creates a new instance of menubean
     */
    @Inject
    KeepRecord keepRecord;

    updatedadminclient em = new updatedadminclient();
    Response rs;

    GenericType<Collection<Categorymaster>> gcategory = new GenericType<Collection<Categorymaster>>() {};
    Collection<Categorymaster> category = new ArrayList<>();

    String categoryname;
    LocalDateTime now = LocalDateTime.now();
    private boolean editMode = false;


    Categorymaster selectedcategory;
    Boolean showcategoryform = false;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formatted = now.format(formatter);

    public menubean() {

    }

    public void showcategory() {

        System.err.println("buuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
        this.showcategoryform = true;
        System.err.println(showcategoryform);
    }

    private void clearFormFields() {
        // Reset all form fields to default values
        this.categoryname = null;
        // Reset edit mode
        this.editMode = false;
        this.selectedcategory = null;
    }

    public void submitTable() {

        System.err.println("category bean ------------ " + formatted + "oooooo" + categoryname);

        if (editMode && selectedcategory != null) {
            // Update the selectedTable's fields
//            selectedcategory.setTableNumber(tableNumber);
//            selectedTable.setCapacity(capacity);
//
            System.err.println("###############################&&&&&&&&&&&&&&&&&&");
            System.err.println(selectedcategory);
            System.err.println(selectedcategory.getCategoryName());
            System.err.println(selectedcategory.getCategoryid());
            System.err.println(keepRecord.getIi());
//
//            selectedTable.setCapacity(capacity);
//            selectedTable.setTableNumber(tableNumber);

            em.update_category(String.valueOf(selectedcategory.getCategoryid()), String.valueOf(keepRecord.getIi()), this.categoryname, formatted);

            // Optionally update in DB
            // Reset edit mode
            editMode = false;
            selectedcategory = null;
        } else {
            System.err.println("category bean ------------ " + formatted + "oooooo" + categoryname);
            em.add_category(String.valueOf(keepRecord.getIi()), categoryname, formatted);
            // Clear form fields after successfully adding a category
            clearFormFields();
        }
    }

//    public void addcategory() {
//
//        System.err.println("category bean ------------ " + formatted + "oooooo" + categoryname);
//        em.add_category(String.valueOf(keepRecord.getIi()), categoryname, formatted);
//
//    }
    public Collection<Categorymaster> getcategorydata() {

        System.err.println("inside method!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        rs = em.get_categories_by_restaurant(Response.class, String.valueOf(keepRecord.getIi()));
//           System.err.println("❌ Error: " + rs.getStatus());
//               System.err.println("❌ Body: " + rs.readEntity(String.class)); // read HTML error as text


        category = rs.readEntity(gcategory);
        return category;
    }

    public void deletecategory(Categorymaster c) {

        em.delete_category(String.valueOf(c.getCategoryid()));
    }

    public void editTable(Categorymaster cc) {

        System.err.println("in edit tale " + cc.getCategoryName());
        editMode = true;
        this.categoryname = cc.getCategoryName();
        selectedcategory = cc;

    }

   

    public String getCategoryname() {
        return categoryname;
    }


    
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public Boolean getShowcategoryform() {
        return showcategoryform;
    }

    public void setShowcategoryform(Boolean showcategoryform) {
        this.showcategoryform = showcategoryform;
    }

}
