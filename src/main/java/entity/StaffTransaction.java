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
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "staff_transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StaffTransaction.findAll", query = "SELECT s FROM StaffTransaction s"),
    @NamedQuery(name = "StaffTransaction.findByStaffTransactionId", query = "SELECT s FROM StaffTransaction s WHERE s.staffTransactionId = :staffTransactionId"),
    @NamedQuery(name = "StaffTransaction.findByAmount", query = "SELECT s FROM StaffTransaction s WHERE s.amount = :amount"),
    @NamedQuery(name = "StaffTransaction.findByDate", query = "SELECT s FROM StaffTransaction s WHERE s.date = :date")})
public class StaffTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "staff_transaction_id")
    private Integer staffTransactionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false)
    private Restaurantmaster restaurantId;
    @JoinColumn(name = "staff_id", referencedColumnName = "staffid")
    @ManyToOne(optional = false)
    private Staffmaster staffId;
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
    @ManyToOne(optional = false)
    private Transactionmaster transactionId;

    public StaffTransaction() {
    }

    public StaffTransaction(Integer staffTransactionId) {
        this.staffTransactionId = staffTransactionId;
    }

    public StaffTransaction(Integer staffTransactionId, int amount, Date date) {
        this.staffTransactionId = staffTransactionId;
        this.amount = amount;
        this.date = date;
    }

    public Integer getStaffTransactionId() {
        return staffTransactionId;
    }

    public void setStaffTransactionId(Integer staffTransactionId) {
        this.staffTransactionId = staffTransactionId;
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

    public Restaurantmaster getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurantmaster restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Staffmaster getStaffId() {
        return staffId;
    }

    public void setStaffId(Staffmaster staffId) {
        this.staffId = staffId;
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
        hash += (staffTransactionId != null ? staffTransactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StaffTransaction)) {
            return false;
        }
        StaffTransaction other = (StaffTransaction) object;
        if ((this.staffTransactionId == null && other.staffTransactionId != null) || (this.staffTransactionId != null && !this.staffTransactionId.equals(other.staffTransactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StaffTransaction[ staffTransactionId=" + staffTransactionId + " ]";
    }
    
}
