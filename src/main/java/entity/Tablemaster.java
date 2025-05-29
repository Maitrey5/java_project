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
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "tablemaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablemaster.findAll", query = "SELECT t FROM Tablemaster t"),
    @NamedQuery(name = "Tablemaster.findByTableId", query = "SELECT t FROM Tablemaster t WHERE t.tableId = :tableId"),
    @NamedQuery(name = "Tablemaster.findByTableNumber", query = "SELECT t FROM Tablemaster t WHERE t.tableNumber = :tableNumber"),
    @NamedQuery(name = "Tablemaster.findByCapacity", query = "SELECT t FROM Tablemaster t WHERE t.capacity = :capacity")})
public class Tablemaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "table_id")
    private Integer tableId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "table_number")
    private int tableNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacity")
    private int capacity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableId")
    private Collection<Ordermaster> ordermasterCollection;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false)
    private Restaurantmaster restaurantId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableId")
    private Collection<Tablebooking> tablebookingCollection;

    public Tablemaster() {
    }

    public Tablemaster(Integer tableId) {
        this.tableId = tableId;
    }

    public Tablemaster(Integer tableId, int tableNumber, int capacity) {
        this.tableId = tableId;
        this.tableNumber = tableNumber;
        this.capacity = capacity;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @JsonbTransient
    public Collection<Ordermaster> getOrdermasterCollection() {
        return ordermasterCollection;
    }

    public void setOrdermasterCollection(Collection<Ordermaster> ordermasterCollection) {
        this.ordermasterCollection = ordermasterCollection;
    }

    public Restaurantmaster getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurantmaster restaurantId) {
        this.restaurantId = restaurantId;
    }

    @JsonbTransient
    public Collection<Tablebooking> getTablebookingCollection() {
        return tablebookingCollection;
    }

    public void setTablebookingCollection(Collection<Tablebooking> tablebookingCollection) {
        this.tablebookingCollection = tablebookingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tableId != null ? tableId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablemaster)) {
            return false;
        }
        Tablemaster other = (Tablemaster) object;
        if ((this.tableId == null && other.tableId != null) || (this.tableId != null && !this.tableId.equals(other.tableId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tablemaster[ tableId=" + tableId + " ]";
    }
    
}
