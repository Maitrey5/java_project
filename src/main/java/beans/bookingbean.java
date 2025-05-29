/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import client.updatedadminclient;
import entity.Tablemaster;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import record.KeepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "bookingbean")
@Dependent
public class bookingbean {

    /**
     * Creates a new instance of bookingbean
     */
    
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
    private LocalTime dinetime;
    private String formattedDate;
    private LocalDate dineDate;
    private String mobileno;
    private String customername;
    
    
    
    public bookingbean() {
    }
    
    
     public void prepareBooking(Tablemaster t) {
        this.tableNumber = t.getTableNumber();
        this.capacity = t.getCapacity();
        this.bookingdate = LocalDate.now();
        this.bookingtime = LocalTime.now();
        this.dineDate = LocalDate.now();
        this.dinetime = LocalTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        this.formattedDate = bookingdate.format(formatter);
//        System.out.println(formattedDate);  // Output: 2025-05-29

    }

    public LocalTime getDinetime() {
        return dinetime;
    }

    public void setDinetime(LocalTime dinetime) {
        this.dinetime = dinetime;
    }

    public LocalDate getDineDate() {
        return dineDate;
    }

    public void setDineDate(LocalDate dineDate) {
        this.dineDate = dineDate;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
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

    public LocalDate getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(LocalDate bookingdate) {
        this.bookingdate = bookingdate;
    }

    public LocalTime getBookingtime() {
        return bookingtime;
    }

    public void setBookingtime(LocalTime bookingtime) {
        this.bookingtime = bookingtime;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
    
     
}
