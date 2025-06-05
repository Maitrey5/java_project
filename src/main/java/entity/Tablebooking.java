/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
import java.time.LocalTime;
import java.util.Date;
import entity.LocalTimeAttributeConverter;

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
    @NamedQuery(name = "Tablebooking.findByNoofpeoples", query = "SELECT t FROM Tablebooking t WHERE t.noofpeoples = :noofpeoples"),
    @NamedQuery(name = "Tablebooking.findByContactno", query = "SELECT t FROM Tablebooking t WHERE t.contactno = :contactno"),
    @NamedQuery(name = "Tablebooking.findByCustomername", query = "SELECT t FROM Tablebooking t WHERE t.customername = :customername"),
    @NamedQuery(name = "Tablebooking.findByTablebookingid", query = "SELECT t FROM Tablebooking t WHERE t.tablebookingid = :tablebookingid")})
public class Tablebooking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "booking_time")
//    @Convert(converter = LocalTimeAttributeConverter.class)
    private LocalTime  bookingTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dine_in_time")
//    @Convert(converter = LocalTimeAttributeConverter.class)
    private LocalTime  dineInTime;
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
    private int noofpeoples;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "contactno")
    private String contactno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "customername")
    private String customername;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tablebookingid")
    private Integer tablebookingid;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false)
    private Restaurantmaster restaurantId;
    @JoinColumn(name = "table_id", referencedColumnName = "table_id")
    @ManyToOne(optional = false)
    private Tablemaster tableId;

    public Tablebooking() {
    }

    public Tablebooking(Integer tablebookingid) {
        this.tablebookingid = tablebookingid;
    }

    public Tablebooking(Integer tablebookingid, LocalTime bookingTime, LocalTime dineInTime, Date bookingDate, Date dineInDate, int noofpeoples, String contactno, String customername) {
        this.tablebookingid = tablebookingid;
        this.bookingTime = bookingTime;
        this.dineInTime = dineInTime;
        this.bookingDate = bookingDate;
        this.dineInDate = dineInDate;
        this.noofpeoples = noofpeoples;
        this.contactno = contactno;
        this.customername = customername;
    }

    public LocalTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public LocalTime getDineInTime() {
        return dineInTime;
    }

    public void setDineInTime(LocalTime dineInTime) {
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

    public int getNoofpeoples() {
        return noofpeoples;
    }

    public void setNoofpeoples(int noofpeoples) {
        this.noofpeoples = noofpeoples;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public Integer getTablebookingid() {
        return tablebookingid;
    }

    public void setTablebookingid(Integer tablebookingid) {
        this.tablebookingid = tablebookingid;
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
        hash += (tablebookingid != null ? tablebookingid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablebooking)) {
            return false;
        }
        Tablebooking other = (Tablebooking) object;
        if ((this.tablebookingid == null && other.tablebookingid != null) || (this.tablebookingid != null && !this.tablebookingid.equals(other.tablebookingid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tablebooking[ tablebookingid=" + tablebookingid + " ]";
    }
    
}
