/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

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
@Table(name = "inventorymaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventorymaster.findAll", query = "SELECT i FROM Inventorymaster i"),
    @NamedQuery(name = "Inventorymaster.findByInventoryid", query = "SELECT i FROM Inventorymaster i WHERE i.inventoryid = :inventoryid"),
    @NamedQuery(name = "Inventorymaster.findByQuantity", query = "SELECT i FROM Inventorymaster i WHERE i.quantity = :quantity"),
    @NamedQuery(name = "Inventorymaster.findByAmount", query = "SELECT i FROM Inventorymaster i WHERE i.amount = :amount"),
    @NamedQuery(name = "Inventorymaster.findByDate", query = "SELECT i FROM Inventorymaster i WHERE i.date = :date"),
    @NamedQuery(name = "Inventorymaster.findByTime", query = "SELECT i FROM Inventorymaster i WHERE i.time = :time"),
    @NamedQuery(name = "Inventorymaster.findByDescription", query = "SELECT i FROM Inventorymaster i WHERE i.description = :description")})
public class Inventorymaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inventoryid")
    private Integer inventoryid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "quantity")
    private String quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false)
    private Restaurantmaster restaurantId;
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
    @ManyToOne(optional = false)
    private Transactionmaster transactionId;

    public Inventorymaster() {
    }

    public Inventorymaster(Integer inventoryid) {
        this.inventoryid = inventoryid;
    }

    public Inventorymaster(Integer inventoryid, String quantity, int amount, Date date, Date time, String description) {
        this.inventoryid = inventoryid;
        this.quantity = quantity;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public Integer getInventoryid() {
        return inventoryid;
    }

    public void setInventoryid(Integer inventoryid) {
        this.inventoryid = inventoryid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurantmaster getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurantmaster restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Transactionmaster getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Transactionmaster transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inventoryid != null ? inventoryid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventorymaster)) {
            return false;
        }
        Inventorymaster other = (Inventorymaster) object;
        if ((this.inventoryid == null && other.inventoryid != null) || (this.inventoryid != null && !this.inventoryid.equals(other.inventoryid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Inventorymaster[ inventoryid=" + inventoryid + " ]";
    }
    
}
