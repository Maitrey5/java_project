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
@Table(name = "categorymaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorymaster.findAll", query = "SELECT c FROM Categorymaster c"),
    @NamedQuery(name = "Categorymaster.findByCategoryid", query = "SELECT c FROM Categorymaster c WHERE c.categoryid = :categoryid"),
    @NamedQuery(name = "Categorymaster.findByCategoryName", query = "SELECT c FROM Categorymaster c WHERE c.categoryName = :categoryName"),
    @NamedQuery(name = "Categorymaster.findByUpdatedAt", query = "SELECT c FROM Categorymaster c WHERE c.updatedAt = :updatedAt")})
public class Categorymaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "categoryid")
    private Integer categoryid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "category_name")
    private String categoryName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false)
    private Restaurantmaster restaurantId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private Collection<Menumaster> menumasterCollection;

    public Categorymaster() {
    }

    public Categorymaster(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Categorymaster(Integer categoryid, String categoryName, Date updatedAt) {
        this.categoryid = categoryid;
        this.categoryName = categoryName;
        this.updatedAt = updatedAt;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Restaurantmaster getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurantmaster restaurantId) {
        this.restaurantId = restaurantId;
    }

    @XmlTransient
    public Collection<Menumaster> getMenumasterCollection() {
        return menumasterCollection;
    }

    public void setMenumasterCollection(Collection<Menumaster> menumasterCollection) {
        this.menumasterCollection = menumasterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryid != null ? categoryid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorymaster)) {
            return false;
        }
        Categorymaster other = (Categorymaster) object;
        if ((this.categoryid == null && other.categoryid != null) || (this.categoryid != null && !this.categoryid.equals(other.categoryid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Categorymaster[ categoryid=" + categoryid + " ]";
    }
    
}
