/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.json.bind.annotation.JsonbTransient;
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
@Table(name = "ordermaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordermaster.findAll", query = "SELECT o FROM Ordermaster o"),
    @NamedQuery(name = "Ordermaster.findByOrderId", query = "SELECT o FROM Ordermaster o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "Ordermaster.findByOrederDate", query = "SELECT o FROM Ordermaster o WHERE o.orederDate = :orederDate"),
    @NamedQuery(name = "Ordermaster.findByNoOfPeople", query = "SELECT o FROM Ordermaster o WHERE o.noOfPeople = :noOfPeople")})
public class Ordermaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_id")
    private Integer orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "oreder_date")
    @Temporal(TemporalType.DATE)
    private Date orederDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "noofpeople")
    private int noOfPeople;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<OrderMenuJointable> orderMenuJointableCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<Billmaster> billmasterCollection;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false)
    private Restaurantmaster restaurantId;
    @JoinColumn(name = "table_id", referencedColumnName = "table_id")
    @ManyToOne(optional = false)
    private Tablemaster tableId;

    public Ordermaster() {
    }

    public Ordermaster(Integer orderId) {
        this.orderId = orderId;
    }

    public Ordermaster(Integer orderId, Date orederDate, int noOfPeople) {
        this.orderId = orderId;
        this.orederDate = orederDate;
        this.noOfPeople = noOfPeople;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrederDate() {
        return orederDate;
    }

    public void setOrederDate(Date orederDate) {
        this.orederDate = orederDate;
    }

    public int getNoOfPeople() {
        return noOfPeople;
    }

    public void setNoOfPeople(int noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    @JsonbTransient
    public Collection<OrderMenuJointable> getOrderMenuJointableCollection() {
        return orderMenuJointableCollection;
    }

    public void setOrderMenuJointableCollection(Collection<OrderMenuJointable> orderMenuJointableCollection) {
        this.orderMenuJointableCollection = orderMenuJointableCollection;
    }

    @JsonbTransient
    public Collection<Billmaster> getBillmasterCollection() {
        return billmasterCollection;
    }

    public void setBillmasterCollection(Collection<Billmaster> billmasterCollection) {
        this.billmasterCollection = billmasterCollection;
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
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordermaster)) {
            return false;
        }
        Ordermaster other = (Ordermaster) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ordermaster[ orderId=" + orderId + " ]";
    }
    
}
