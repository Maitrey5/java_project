/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "tablebooking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablebooking.findAll", query = "SELECT t FROM Tablebooking t"),
    @NamedQuery(name = "Tablebooking.findByBookingTime", query = "SELECT t FROM Tablebooking t WHERE t.bookingTime = :bookingTime"),
    @NamedQuery(name = "Tablebooking.findByDineInTime", query = "SELECT t FROM Tablebooking t WHERE t.dineInTime = :dineInTime"),
    @NamedQuery(name = "Tablebooking.findByBookingDate", query = "SELECT t FROM Tablebooking t WHERE t.bookingDate = :bookingDate"),
    @NamedQuery(name = "Tablebooking.findByDineInDate", query = "SELECT t FROM Tablebooking t WHERE t.dineInDate = :dineInDate"),
    @NamedQuery(name = "Tablebooking.findByNoOfPeoples", query = "SELECT t FROM Tablebooking t WHERE t.noOfPeoples = :noOfPeoples"),
    @NamedQuery(name = "Tablebooking.findByContactNo", query = "SELECT t FROM Tablebooking t WHERE t.contactNo = :contactNo"),
    @NamedQuery(name = "Tablebooking.findByCustomerName", query = "SELECT t FROM Tablebooking t WHERE t.customerName = :customerName"),
    @NamedQuery(name = "Tablebooking.findByTableBookingId", query = "SELECT t FROM Tablebooking t WHERE t.tableBookingId = :tableBookingId")})
public class Tablebooking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "booking_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    private Date bookingTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dine_in_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    private Date dineInTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "booking_date")
    @Temporal(TemporalType.DATE)
    private Date bookingDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dine_in_date")
    @Temporal(TemporalType.DATE)
    private Date dineInDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "noofpeoples")
    private int noOfPeoples;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "contactno")
    private String contactNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "customername")
    private String customerName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tablebookingid")
    private Integer tableBookingId;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false)
    private Restaurantmaster restaurantId;
    @JoinColumn(name = "table_id", referencedColumnName = "table_id")
    @ManyToOne(optional = false)
    private Tablemaster tableId;

    public Tablebooking() {
    }

    public Tablebooking(Integer tableBookingId) {
        this.tableBookingId = tableBookingId;
    }

    public Tablebooking(Integer tableBookingId, Date bookingTime, Date dineInTime, Date bookingDate, Date dineInDate, int noOfPeoples, String contactNo, String customerName) {
        this.tableBookingId = tableBookingId;
        this.bookingTime = bookingTime;
        this.dineInTime = dineInTime;
        this.bookingDate = bookingDate;
        this.dineInDate = dineInDate;
        this.noOfPeoples = noOfPeoples;
        this.contactNo = contactNo;
        this.customerName = customerName;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Date getDineInTime() {
        return dineInTime;
    }

    public void setDineInTime(Date dineInTime) {
        this.dineInTime = dineInTime;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getDineInDate() {
        return dineInDate;
    }

    public void setDineInDate(Date dineInDate) {
        this.dineInDate = dineInDate;
    }

    public int getNoOfPeoples() {
        return noOfPeoples;
    }

    public void setNoOfPeoples(int noOfPeoples) {
        this.noOfPeoples = noOfPeoples;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getTableBookingId() {
        return tableBookingId;
    }

    public void setTableBookingId(Integer tableBookingId) {
        this.tableBookingId = tableBookingId;
    }

    public Restaurantmaster getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurantmaster restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Tablemaster getTableId() {
        return tableId;
    }

    public void setTableId(Tablemaster tableId) {
        this.tableId = tableId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tableBookingId != null ? tableBookingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablebooking)) {
            return false;
        }
        Tablebooking other = (Tablebooking) object;
        if ((this.tableBookingId == null && other.tableBookingId != null) || (this.tableBookingId != null && !this.tableBookingId.equals(other.tableBookingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tablebooking[ tableBookingId=" + tableBookingId + " ]";
    }
    
}
