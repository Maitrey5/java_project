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
@Table(name = "menumaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menumaster.findAll", query = "SELECT m FROM Menumaster m"),
    @NamedQuery(name = "Menumaster.findByMenuId", query = "SELECT m FROM Menumaster m WHERE m.menuId = :menuId"),
    @NamedQuery(name = "Menumaster.findByItemName", query = "SELECT m FROM Menumaster m WHERE m.itemName = :itemName"),
    @NamedQuery(name = "Menumaster.findByItemPrice", query = "SELECT m FROM Menumaster m WHERE m.itemPrice = :itemPrice"),
    @NamedQuery(name = "Menumaster.findByDescription", query = "SELECT m FROM Menumaster m WHERE m.description = :description"),
    @NamedQuery(name = "Menumaster.findByIsAvalaible", query = "SELECT m FROM Menumaster m WHERE m.isAvalaible = :isAvalaible"),
    @NamedQuery(name = "Menumaster.findByUpdatedAt", query = "SELECT m FROM Menumaster m WHERE m.updatedAt = :updatedAt"),
    @NamedQuery(name = "Menumaster.findByItemType", query = "SELECT m FROM Menumaster m WHERE m.itemType = :itemType")})
public class Menumaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "menu_id")
    private Integer menuId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "item_name")
    private String itemName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "item_price")
    private int itemPrice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_avalaible")
    private boolean isAvalaible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "item_type")
    private String itemType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuId")
    private Collection<OrderMenuJointable> orderMenuJointableCollection;
    @JoinColumn(name = "category_id", referencedColumnName = "categoryid")
    @ManyToOne(optional = false)
    private Categorymaster categoryId;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false)
    private Restaurantmaster restaurantId;

    public Menumaster() {
    }

    public Menumaster(Integer menuId) {
        this.menuId = menuId;
    }

    public Menumaster(Integer menuId, String itemName, int itemPrice, String description, boolean isAvalaible, Date updatedAt, String itemType) {
        this.menuId = menuId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.description = description;
        this.isAvalaible = isAvalaible;
        this.updatedAt = updatedAt;
        this.itemType = itemType;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsAvalaible() {
        return isAvalaible;
    }

    public void setIsAvalaible(boolean isAvalaible) {
        this.isAvalaible = isAvalaible;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @XmlTransient
    public Collection<OrderMenuJointable> getOrderMenuJointableCollection() {
        return orderMenuJointableCollection;
    }

    public void setOrderMenuJointableCollection(Collection<OrderMenuJointable> orderMenuJointableCollection) {
        this.orderMenuJointableCollection = orderMenuJointableCollection;
    }

    public Categorymaster getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Categorymaster categoryId) {
        this.categoryId = categoryId;
    }

    public Restaurantmaster getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurantmaster restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuId != null ? menuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menumaster)) {
            return false;
        }
        Menumaster other = (Menumaster) object;
        if ((this.menuId == null && other.menuId != null) || (this.menuId != null && !this.menuId.equals(other.menuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Menumaster[ menuId=" + menuId + " ]";
    }
    
}
