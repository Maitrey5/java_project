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
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "order_menu_jointable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderMenuJointable.findAll", query = "SELECT o FROM OrderMenuJointable o"),
    @NamedQuery(name = "OrderMenuJointable.findByOrdermenuid", query = "SELECT o FROM OrderMenuJointable o WHERE o.ordermenuid = :ordermenuid"),
    @NamedQuery(name = "OrderMenuJointable.findByQuantity", query = "SELECT o FROM OrderMenuJointable o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "OrderMenuJointable.findByTotalPrice", query = "SELECT o FROM OrderMenuJointable o WHERE o.totalPrice = :totalPrice")})
public class OrderMenuJointable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ordermenuid")
    private Integer ordermenuid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_price")
    private int totalPrice;
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @ManyToOne(optional = false)
    private Ordermaster orderId;
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    @ManyToOne(optional = false)
    private Menumaster menuId;

    public OrderMenuJointable() {
    }

    public OrderMenuJointable(Integer ordermenuid) {
        this.ordermenuid = ordermenuid;
    }

    public OrderMenuJointable(Integer ordermenuid, int quantity, int totalPrice) {
        this.ordermenuid = ordermenuid;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Integer getOrdermenuid() {
        return ordermenuid;
    }

    public void setOrdermenuid(Integer ordermenuid) {
        this.ordermenuid = ordermenuid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Ordermaster getOrderId() {
        return orderId;
    }

    public void setOrderId(Ordermaster orderId) {
        this.orderId = orderId;
    }

    public Menumaster getMenuId() {
        return menuId;
    }

    public void setMenuId(Menumaster menuId) {
        this.menuId = menuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordermenuid != null ? ordermenuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderMenuJointable)) {
            return false;
        }
        OrderMenuJointable other = (OrderMenuJointable) object;
        if ((this.ordermenuid == null && other.ordermenuid != null) || (this.ordermenuid != null && !this.ordermenuid.equals(other.ordermenuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderMenuJointable[ ordermenuid=" + ordermenuid + " ]";
    }
    
}
