/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import client.updatedadminclient;
import entity.Tablemaster;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import record.KeepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "tableBean")
@ViewScoped
public class TableBean implements Serializable {

    @Inject
    KeepRecord keepRecord;
    Response rs;
    GenericType<Collection<Tablemaster>> gtables = new GenericType<Collection<Tablemaster>>() {
    };
    Collection<Tablemaster> tables = new ArrayList<>();
    updatedadminclient em = new updatedadminclient();

    private boolean showTableForm = false;
    private boolean showbookingForm = false;
    private int tableNumber;
    private int capacity;
    private boolean editMode = false;
    private Tablemaster selectedTable;
    private LocalDate bookingdate;
    private LocalTime bookingtime;
    private String formattedDate;
    private Boolean showcategory;
    private Boolean showmenuform;
    private Boolean showstaff;
    private Boolean showuser;

    public Boolean getShowuser() {
        return showuser;
    }

    public void setShowuser(Boolean showuser) {
        this.showuser = showuser;
    }

    public void showuserfunc() {
        showTableForm = false;
        showbookingForm = false;
        showcategory = false;
        showmenuform = false;
        showstaff = false;
        showuser = true;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public Tablemaster getSelectedTable() {
        return selectedTable;
    }

    public void setSelectedTable(Tablemaster selectedTable) {
        this.selectedTable = selectedTable;
    }

    public boolean isShowTableForm() {
        return showTableForm;
    }

    public void setShowTableForm(boolean showTableForm) {
        this.showTableForm = showTableForm;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Boolean getShowcategory() {
        return showcategory;
    }

    public void setShowcategory(Boolean showcategory) {
        this.showcategory = showcategory;
    }

    public int getCapacity() {
        return capacity;
    }

    public Boolean getShowmenuform() {
        return showmenuform;
    }

    public void setShowmenuform(Boolean showmenuform) {
        this.showmenuform = showmenuform;
    }

    public boolean isShowbookingForm() {
        return showbookingForm;
    }

    public Boolean getShowstaff() {
        return showstaff;
    }

    public void setShowstaff(Boolean showstaff) {
        this.showstaff = showstaff;
    }

    public void setShowbookingForm(boolean showbookingForm) {
        this.showbookingForm = showbookingForm;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void showForm() {
        showTableForm = true;
        showcategory = false;
        showmenuform = false;
        showstaff = false;
        showuser = false;
        showbookingForm = false;
    }

    public void showFormbooking() {
        showTableForm = false;
        showcategory = false;
        showmenuform = false;
        showstaff = false;
        showuser = false;
        showbookingForm = true;
    }

    public void showcategoryy() {
        showTableForm = false;
        showbookingForm = false;
        showmenuform = false;
        showstaff = false;
        showuser = false;
        showcategory = true;
    }

    public void showmenu() {
        showTableForm = false;
        showbookingForm = false;
        showcategory = false;
        showstaff = false;
        showuser = false;
        showmenuform = true;
    }

    public void showstafffunc() {
        showTableForm = false;
        showbookingForm = false;
        showcategory = false;
        showmenuform = false;
        showuser = false;
        showstaff = true;
    }

    public void submitTable() {
        if (editMode && selectedTable != null) {
            selectedTable.setTableNumber(tableNumber);
            selectedTable.setCapacity(capacity);
            em.update_table_to_restaurant(selectedTable, String.valueOf(selectedTable.getTableId()));
            editMode = false;
            selectedTable = null;
        } else {
            em.add_table_to_restaurant(String.valueOf(keepRecord.getIi()), String.valueOf(tableNumber), String.valueOf(capacity));
        }
        this.tableNumber = 0;
        this.capacity = 0;
    }

    public Collection<Tablemaster> gettabledata() {
        Integer k = keepRecord.getIi();
        rs = em.get_tables_by_restaurant(Response.class, String.valueOf(k));
        if (rs.getStatus() == 200) {
            tables = rs.readEntity(gtables);
        } else {
            System.err.println("Error response: " + rs.getStatus());
            System.err.println("Error body: " + rs.readEntity(String.class));
        }
        return tables;
    }

    public void editTable(Tablemaster t) {
        this.tableNumber = t.getTableNumber();
        this.capacity = t.getCapacity();
        this.selectedTable = t;
        this.selectedTable.setRestaurantId(t.getRestaurantId());
        this.editMode = true;
    }

    public void prepareBooking(Tablemaster t) {
        this.tableNumber = t.getTableNumber();
        this.capacity = t.getCapacity();
        this.bookingdate = LocalDate.now();
        this.bookingtime = LocalTime.now();
    }

    public void deleteTable(Tablemaster t) {
        em.delete_table_by_restaurant(String.valueOf(t.getTableId()));
    }

    public void bookTable() {
        // Implement booking logic here
        resetForm();
    }

    private void resetForm() {
        this.tableNumber = 0;
        this.capacity = 0;
        this.bookingdate = null;
        this.bookingtime = null;
        this.showbookingForm = false;
    }

    public TableBean() {
        this.showcategory = false;
        this.showmenuform = false;
        this.showstaff = false;
        this.showuser = false;
    }
}
