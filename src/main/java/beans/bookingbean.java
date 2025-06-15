/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import client.updatedadminclient;
import entity.Tablebooking;
import entity.Tablemaster;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import jakarta.annotation.PostConstruct;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import record.KeepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "bookingbean")
@ViewScoped
public class bookingbean implements Serializable {
    @Inject
    KeepRecord keepRecord;
    Response rs;
    GenericType<Collection<Tablemaster>> gtables = new GenericType<Collection<Tablemaster>>() {};
    Collection<Tablemaster> tables = new ArrayList<>();
    updatedadminclient em = new updatedadminclient();

    private boolean showTableForm = false;
    private int tableNumber;
    private int capacity;
    private boolean editMode = false;
    private Tablemaster selectedTable;
    private LocalDate bookingdate;
    private LocalTime bookingtime;
    private LocalTime dinetime;
    private String btime;
    private String dtime;
    private String formattedDateb;
    private String formattedDated;
    private LocalDate dineDate;
    private String mobileno;
    private String customername;

    private Collection<Tablebooking> bookings = new ArrayList<>();
    private Tablebooking currentBooking;

    private boolean showBookingList = true;
    private boolean showBookingForm = false;
    private boolean showBookingDetails = false;

    public bookingbean() {
        this.bookingdate = LocalDate.now();
        this.bookingtime = LocalTime.now();
        this.dineDate = LocalDate.now();
        this.dinetime = LocalTime.now();
        bookings = fetchAllBookings();
    }
    
    @PostConstruct
    public void init() {
        if (keepRecord != null) {
            tables = gettabledata();
        } else {
            System.err.println("keepRecord is NULL in onlymenubean.init()");
        }
    }

    public void booktable(Tablemaster t) {
        showBookingList = false;
        showBookingForm = true;
        showBookingDetails = false;
        this.tableNumber = t.getTableNumber();
    }

    public Collection<Tablemaster> gettabledata() {

        Integer k = keepRecord.getIi();
        rs = em.searchtablebyrestaurant(Response.class, String.valueOf(k));

        if (rs.getStatus() == 200) {
            tables = rs.readEntity(gtables);
        } else {
            System.err.println("Error response: " + rs.getStatus());
            System.err.println("Error body: " + rs.readEntity(String.class));
        }

        return tables;

    }
    public void insertbook() {
        GenericType<Tablemaster> gt = new GenericType<Tablemaster>() {};
        rs = em.search_table(Response.class, String.valueOf(keepRecord.getIi()), String.valueOf(tableNumber));
        Tablemaster tt = rs.readEntity(gt);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatterT = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.formattedDateb = bookingdate.format(formatter);
        this.formattedDated = dineDate.format(formatter);
        this.btime = bookingtime.format(formatterT);
        this.dtime = dinetime.format(formatterT);
        em.book_table_by_restaurant(String.valueOf(tt.getTableId()), String.valueOf(keepRecord.getIi()), btime, dtime, formattedDateb, formattedDated, String.valueOf(capacity), mobileno, customername);
        bookings = fetchAllBookings();
    }

    public void prepareBooking(Tablemaster t) {
        this.tableNumber = t.getTableNumber();
        this.capacity = t.getCapacity();
    }

    public void showForm() {
        showTableForm = true;
    }

    public LocalTime getDinetime() {
        return dinetime;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public Collection<Tablebooking> getBookings() {
        return bookings;
    }

    public void setBookings(Collection<Tablebooking> bookings) {
        this.bookings = bookings;
    }

    public Tablebooking getCurrentBooking() {
        return currentBooking;
    }

    public void setCurrentBooking(Tablebooking currentBooking) {
        this.currentBooking = currentBooking;
    }

    public boolean isShowBookingList() {
        return showBookingList;
    }

    public void setShowBookingList(boolean showBookingList) {
        this.showBookingList = showBookingList;
    }

    public boolean isShowBookingForm() {
        return showBookingForm;
    }

    public void setShowBookingForm(boolean showBookingForm) {
        this.showBookingForm = showBookingForm;
    }

    public boolean isShowBookingDetails() {
        return showBookingDetails;
    }

    public void setShowBookingDetails(boolean showBookingDetails) {
        this.showBookingDetails = showBookingDetails;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getFormattedDated() {
        return formattedDated;
    }

    public void setFormattedDated(String formattedDated) {
        this.formattedDated = formattedDated;
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

    public String getFormattedDateb() {
        return formattedDateb;
    }

    public void setFormattedDateb(String formattedDateb) {
        this.formattedDateb = formattedDateb;
    }

    public Collection<Tablebooking> fetchAllBookings() {
        GenericType<Collection<Tablebooking>> gbookings = new GenericType<Collection<Tablebooking>>() {};
        rs = em.get_tablesbooking_by_restaurant(Response.class, String.valueOf(keepRecord.getIi()));
        
        if (rs.getStatus() == 200) {
            return rs.readEntity(gbookings);
        } else {
            System.err.println("Error fetching bookings: " + rs.getStatus());
            System.err.println("Error body: " + rs.readEntity(String.class));
            return new ArrayList<>();
        }
    }

    public void showBookingListView() {
        showBookingList = true;
        showBookingForm = false;
        showBookingDetails = false;
    }

    public void showFormView() {
        showBookingList = false;
        showBookingForm = true;
        showBookingDetails = false;
    }

    public void showDetailsView(Tablebooking booking) {
        currentBooking = booking;
        showBookingList = false;
        showBookingForm = false;
        showBookingDetails = true;
    }

    public void submitBooking() {
        // Implement logic to submit a new booking
        // Placeholder: add to bookings
        bookings = fetchAllBookings();
        showBookingListView();
    }

    public void updatedetails() {
        // Implement logic to update booking details
        bookings = fetchAllBookings();
        showBookingListView();
    }

    public void deletebooking() {
        // Implement logic to delete a booking
        bookings = fetchAllBookings();
        showBookingListView();
    }
}
