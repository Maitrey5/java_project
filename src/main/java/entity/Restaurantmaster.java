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
@Table(name = "restaurantmaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restaurantmaster.findAll", query = "SELECT r FROM Restaurantmaster r"),
    @NamedQuery(name = "Restaurantmaster.findByRestaurantId", query = "SELECT r FROM Restaurantmaster r WHERE r.restaurantId = :restaurantId"),
    @NamedQuery(name = "Restaurantmaster.findByRestaurantName", query = "SELECT r FROM Restaurantmaster r WHERE r.restaurantName = :restaurantName"),
    @NamedQuery(name = "Restaurantmaster.findByRestaurantAddress", query = "SELECT r FROM Restaurantmaster r WHERE r.restaurantAddress = :restaurantAddress"),
    @NamedQuery(name = "Restaurantmaster.findByRestaurantContactno", query = "SELECT r FROM Restaurantmaster r WHERE r.restaurantContactno = :restaurantContactno"),
    @NamedQuery(name = "Restaurantmaster.findByRestaurantEmail", query = "SELECT r FROM Restaurantmaster r WHERE r.restaurantEmail = :restaurantEmail"),
    @NamedQuery(name = "Restaurantmaster.findByRestaurantCity", query = "SELECT r FROM Restaurantmaster r WHERE r.restaurantCity = :restaurantCity"),
    @NamedQuery(name = "Restaurantmaster.findByRestaurantState", query = "SELECT r FROM Restaurantmaster r WHERE r.restaurantState = :restaurantState"),
    @NamedQuery(name = "Restaurantmaster.findByRestaurantCountry", query = "SELECT r FROM Restaurantmaster r WHERE r.restaurantCountry = :restaurantCountry"),
    @NamedQuery(name = "Restaurantmaster.findByRestaurantPincode", query = "SELECT r FROM Restaurantmaster r WHERE r.restaurantPincode = :restaurantPincode"),
    @NamedQuery(name = "Restaurantmaster.findByCreatedAt", query = "SELECT r FROM Restaurantmaster r WHERE r.createdAt = :createdAt"),
    @NamedQuery(name = "Restaurantmaster.findByUpdatedAt", query = "SELECT r FROM Restaurantmaster r WHERE r.updatedAt = :updatedAt"),
    @NamedQuery(name = "Restaurantmaster.findByIsActive", query = "SELECT r FROM Restaurantmaster r WHERE r.isActive = :isActive")})
public class Restaurantmaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "restaurant_id")
    private Integer restaurantId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "restaurant_name")
    private String restaurantName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "restaurant_address")
    private String restaurantAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "restaurant_contactno")
    private String restaurantContactno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "restaurant_email")
    private String restaurantEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "restaurant_city")
    private String restaurantCity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "restaurant_state")
    private String restaurantState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "restaurant_country")
    private String restaurantCountry;
    @Basic(optional = false)
    @NotNull
    @Column(name = "restaurant_pincode")
    private int restaurantPincode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private Collection<Categorymaster> categorymasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private Collection<Menumaster> menumasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private Collection<Transactionmaster> transactionmasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private Collection<Staffmaster> staffmasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private Collection<Billmaster> billmasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private Collection<Ordermaster> ordermasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private Collection<StaffTransaction> staffTransactionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private Collection<Tablemaster> tablemasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private Collection<Inventorymaster> inventorymasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private Collection<User> userCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private Collection<Tablebooking> tablebookingCollection;

    public Restaurantmaster() {
    }

    public Restaurantmaster(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Restaurantmaster(Integer restaurantId, String restaurantName, String restaurantAddress, String restaurantContactno, String restaurantEmail, String restaurantCity, String restaurantState, String restaurantCountry, int restaurantPincode, Date createdAt, Date updatedAt, boolean isActive) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantContactno = restaurantContactno;
        this.restaurantEmail = restaurantEmail;
        this.restaurantCity = restaurantCity;
        this.restaurantState = restaurantState;
        this.restaurantCountry = restaurantCountry;
        this.restaurantPincode = restaurantPincode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantContactno() {
        return restaurantContactno;
    }

    public void setRestaurantContactno(String restaurantContactno) {
        this.restaurantContactno = restaurantContactno;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public void setRestaurantEmail(String restaurantEmail) {
        this.restaurantEmail = restaurantEmail;
    }

    public String getRestaurantCity() {
        return restaurantCity;
    }

    public void setRestaurantCity(String restaurantCity) {
        this.restaurantCity = restaurantCity;
    }

    public String getRestaurantState() {
        return restaurantState;
    }

    public void setRestaurantState(String restaurantState) {
        this.restaurantState = restaurantState;
    }

    public String getRestaurantCountry() {
        return restaurantCountry;
    }

    public void setRestaurantCountry(String restaurantCountry) {
        this.restaurantCountry = restaurantCountry;
    }

    public int getRestaurantPincode() {
        return restaurantPincode;
    }

    public void setRestaurantPincode(int restaurantPincode) {
        this.restaurantPincode = restaurantPincode;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public Collection<Categorymaster> getCategorymasterCollection() {
        return categorymasterCollection;
    }

    public void setCategorymasterCollection(Collection<Categorymaster> categorymasterCollection) {
        this.categorymasterCollection = categorymasterCollection;
    }

    @XmlTransient
    public Collection<Menumaster> getMenumasterCollection() {
        return menumasterCollection;
    }

    public void setMenumasterCollection(Collection<Menumaster> menumasterCollection) {
        this.menumasterCollection = menumasterCollection;
    }

    @XmlTransient
    public Collection<Transactionmaster> getTransactionmasterCollection() {
        return transactionmasterCollection;
    }

    public void setTransactionmasterCollection(Collection<Transactionmaster> transactionmasterCollection) {
        this.transactionmasterCollection = transactionmasterCollection;
    }

    @XmlTransient
    public Collection<Staffmaster> getStaffmasterCollection() {
        return staffmasterCollection;
    }

    public void setStaffmasterCollection(Collection<Staffmaster> staffmasterCollection) {
        this.staffmasterCollection = staffmasterCollection;
    }

    @XmlTransient
    public Collection<Billmaster> getBillmasterCollection() {
        return billmasterCollection;
    }

    public void setBillmasterCollection(Collection<Billmaster> billmasterCollection) {
        this.billmasterCollection = billmasterCollection;
    }

    @XmlTransient
    public Collection<Ordermaster> getOrdermasterCollection() {
        return ordermasterCollection;
    }

    public void setOrdermasterCollection(Collection<Ordermaster> ordermasterCollection) {
        this.ordermasterCollection = ordermasterCollection;
    }

    @XmlTransient
    public Collection<StaffTransaction> getStaffTransactionCollection() {
        return staffTransactionCollection;
    }

    public void setStaffTransactionCollection(Collection<StaffTransaction> staffTransactionCollection) {
        this.staffTransactionCollection = staffTransactionCollection;
    }

    @XmlTransient
    public Collection<Tablemaster> getTablemasterCollection() {
        return tablemasterCollection;
    }

    public void setTablemasterCollection(Collection<Tablemaster> tablemasterCollection) {
        this.tablemasterCollection = tablemasterCollection;
    }

    @XmlTransient
    public Collection<Inventorymaster> getInventorymasterCollection() {
        return inventorymasterCollection;
    }

    public void setInventorymasterCollection(Collection<Inventorymaster> inventorymasterCollection) {
        this.inventorymasterCollection = inventorymasterCollection;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @XmlTransient
    public Collection<Tablebooking> getTablebookingCollection() {
        return tablebookingCollection;
    }

    public void setTablebookingCollection(Collection<Tablebooking> tablebookingCollection) {
        this.tablebookingCollection = tablebookingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restaurantId != null ? restaurantId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restaurantmaster)) {
            return false;
        }
        Restaurantmaster other = (Restaurantmaster) object;
        if ((this.restaurantId == null && other.restaurantId != null) || (this.restaurantId != null && !this.restaurantId.equals(other.restaurantId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Restaurantmaster[ restaurantId=" + restaurantId + " ]";
    }
    
}
