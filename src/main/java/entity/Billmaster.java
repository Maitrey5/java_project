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
@Table(name = "billmaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Billmaster.findAll", query = "SELECT b FROM Billmaster b"),
    @NamedQuery(name = "Billmaster.findByBillId", query = "SELECT b FROM Billmaster b WHERE b.billId = :billId"),
    @NamedQuery(name = "Billmaster.findByTotalAmount", query = "SELECT b FROM Billmaster b WHERE b.totalAmount = :totalAmount"),
    @NamedQuery(name = "Billmaster.findByDiscount", query = "SELECT b FROM Billmaster b WHERE b.discount = :discount"),
    @NamedQuery(name = "Billmaster.findByFinalAmount", query = "SELECT b FROM Billmaster b WHERE b.finalAmount = :finalAmount"),
    @NamedQuery(name = "Billmaster.findByFinalPaybleAmountWithtax", query = "SELECT b FROM Billmaster b WHERE b.finalPaybleAmountWithtax = :finalPaybleAmountWithtax"),
    @NamedQuery(name = "Billmaster.findByDateTime", query = "SELECT b FROM Billmaster b WHERE b.dateTime = :dateTime"),
    @NamedQuery(name = "Billmaster.findByModeofpayment", query = "SELECT b FROM Billmaster b WHERE b.modeofpayment = :modeofpayment")})
public class Billmaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bill_id")
    private Integer billId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_amount")
    private int totalAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount")
    private int discount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "final_amount")
    private int finalAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "final_payble_amount_withtax")
    private int finalPaybleAmountWithtax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "modeofpayment")
    private String modeofpayment;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false)
    private Restaurantmaster restaurantId;
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @ManyToOne(optional = false)
    private Ordermaster orderId;
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
    @ManyToOne(optional = false)
    private Transactionmaster transactionId;

    public Billmaster() {
    }

    public Billmaster(Integer billId) {
        this.billId = billId;
    }

    public Billmaster(Integer billId, int totalAmount, int discount, int finalAmount, int finalPaybleAmountWithtax, Date dateTime, String modeofpayment) {
        this.billId = billId;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.finalAmount = finalAmount;
        this.finalPaybleAmountWithtax = finalPaybleAmountWithtax;
        this.dateTime = dateTime;
        this.modeofpayment = modeofpayment;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(int finalAmount) {
        this.finalAmount = finalAmount;
    }

    public int getFinalPaybleAmountWithtax() {
        return finalPaybleAmountWithtax;
    }

    public void setFinalPaybleAmountWithtax(int finalPaybleAmountWithtax) {
        this.finalPaybleAmountWithtax = finalPaybleAmountWithtax;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getModeofpayment() {
        return modeofpayment;
    }

    public void setModeofpayment(String modeofpayment) {
        this.modeofpayment = modeofpayment;
    }

    public Restaurantmaster getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurantmaster restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Ordermaster getOrderId() {
        return orderId;
    }

    public void setOrderId(Ordermaster orderId) {
        this.orderId = orderId;
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
        hash += (billId != null ? billId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Billmaster)) {
            return false;
        }
        Billmaster other = (Billmaster) object;
        if ((this.billId == null && other.billId != null) || (this.billId != null && !this.billId.equals(other.billId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Billmaster[ billId=" + billId + " ]";
    }
    
}
