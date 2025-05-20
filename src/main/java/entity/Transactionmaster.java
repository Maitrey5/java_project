/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "transactionmaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactionmaster.findAll", query = "SELECT t FROM Transactionmaster t"),
    @NamedQuery(name = "Transactionmaster.findByTransactionId", query = "SELECT t FROM Transactionmaster t WHERE t.transactionId = :transactionId"),
    @NamedQuery(name = "Transactionmaster.findByAmount", query = "SELECT t FROM Transactionmaster t WHERE t.amount = :amount"),
    @NamedQuery(name = "Transactionmaster.findByTransactionType", query = "SELECT t FROM Transactionmaster t WHERE t.transactionType = :transactionType"),
    @NamedQuery(name = "Transactionmaster.findByDescription", query = "SELECT t FROM Transactionmaster t WHERE t.description = :description"),
    @NamedQuery(name = "Transactionmaster.findByDate", query = "SELECT t FROM Transactionmaster t WHERE t.date = :date"),
    @NamedQuery(name = "Transactionmaster.findByTime", query = "SELECT t FROM Transactionmaster t WHERE t.time = :time")})
public class Transactionmaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transaction_id")
    private Integer transactionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "transaction_type")
    private String transactionType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false)
    private Restaurantmaster restaurantId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionId")
    private Collection<Billmaster> billmasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionId")
    private Collection<StaffTransaction> staffTransactionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionId")
    private Collection<Inventorymaster> inventorymasterCollection;

    public Transactionmaster() {
    }

    public Transactionmaster(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Transactionmaster(Integer transactionId, int amount, String transactionType, String description, Date date, Date time) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Restaurantmaster getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurantmaster restaurantId) {
        this.restaurantId = restaurantId;
    }

    @XmlTransient
    public Collection<Billmaster> getBillmasterCollection() {
        return billmasterCollection;
    }

    public void setBillmasterCollection(Collection<Billmaster> billmasterCollection) {
        this.billmasterCollection = billmasterCollection;
    }

    @XmlTransient
    public Collection<StaffTransaction> getStaffTransactionCollection() {
        return staffTransactionCollection;
    }

    public void setStaffTransactionCollection(Collection<StaffTransaction> staffTransactionCollection) {
        this.staffTransactionCollection = staffTransactionCollection;
    }

    @XmlTransient
    public Collection<Inventorymaster> getInventorymasterCollection() {
        return inventorymasterCollection;
    }

    public void setInventorymasterCollection(Collection<Inventorymaster> inventorymasterCollection) {
        this.inventorymasterCollection = inventorymasterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactionmaster)) {
            return false;
        }
        Transactionmaster other = (Transactionmaster) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Transactionmaster[ transactionId=" + transactionId + " ]";
    }
    
}
