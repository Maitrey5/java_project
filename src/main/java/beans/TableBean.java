/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import client.updatedadminclient;
import entity.Tablemaster;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
    private int tableNumber;
    private int capacity;
    private boolean editMode = false;
    private Tablemaster selectedTable;
    private LocalDate  bookingdate;
    private LocalTime bookingtime;
    private String formattedDate;
    

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public Tablemaster getSelectedTable() {
        return selectedTable;
    }

    // Getters and Setters
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void showForm() {
        showTableForm = true;
    }

    public void submitTable() {
        
        if (editMode && selectedTable != null) {
            // Update the selectedTable's fields
            selectedTable.setTableNumber(tableNumber);
            selectedTable.setCapacity(capacity);
            
            System.err.println("###############################");
            System.err.println(selectedTable);
            System.err.println(tableNumber);
            System.err.println(capacity);
            System.err.println(keepRecord.getIi());
            
            selectedTable.setCapacity(capacity);
            selectedTable.setTableNumber(tableNumber);
            
            
     
            em.update_table_to_restaurant(selectedTable ,  String.valueOf(selectedTable.getTableId()));

            // Optionally update in DB
            // Reset edit mode
            editMode = false;
            selectedTable = null;
        } else {
            em.add_table_to_restaurant(String.valueOf(keepRecord.getIi()), String.valueOf(tableNumber), String.valueOf(capacity));

            // Insert new table logic
        }

        // Clear form fields
        this.tableNumber = 0;
        this.capacity = 0;


        System.err.println("inside table bean ++++++++++++++++++++++++++++");
        System.err.println(keepRecord.getR_id());
        System.err.println(keepRecord.getIi());

        // Add save logic here (e.g., database insert)
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

//        em.update_table_to_restaurant(String.valueOf(t.getTableId()), String.valueOf(keepRecord.getIi()), String.valueOf(t.getTableNumber()), String.valueOf(t.getCapacity()));
    }
    
     public void prepareBooking(Tablemaster t) {
        this.tableNumber = t.getTableNumber();
        this.capacity = t.getCapacity();
        this.bookingdate = LocalDate.now();
        this.bookingtime = LocalTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        this.formattedDate = bookingdate.format(formatter);
//        System.out.println(formattedDate);  // Output: 2025-05-29

    }


    public void deleteTable(Tablemaster t) {

        em.delete_table_by_restaurant(String.valueOf(t.getTableId()));
    }

    public void bookTable() {
        // Your logic here
    }

    private void resetForm() {
        tableNumber = 0;
        capacity = 0;
    }

    /**
     * Creates a new instance of TableBean
     */
    public TableBean() {
    }

}
