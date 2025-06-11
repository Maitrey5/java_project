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
@Table(name = "staffmaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staffmaster.findAll", query = "SELECT s FROM Staffmaster s"),
    @NamedQuery(name = "Staffmaster.findByStaffid", query = "SELECT s FROM Staffmaster s WHERE s.staffid = :staffid"),
    @NamedQuery(name = "Staffmaster.findByName", query = "SELECT s FROM Staffmaster s WHERE s.name = :name"),
    @NamedQuery(name = "Staffmaster.findBySurname", query = "SELECT s FROM Staffmaster s WHERE s.surname = :surname"),
    @NamedQuery(name = "Staffmaster.findByAge", query = "SELECT s FROM Staffmaster s WHERE s.age = :age"),
    @NamedQuery(name = "Staffmaster.findBySalary", query = "SELECT s FROM Staffmaster s WHERE s.salary = :salary"),
    @NamedQuery(name = "Staffmaster.findByIdNumber", query = "SELECT s FROM Staffmaster s WHERE s.idNumber = :idNumber"),
    @NamedQuery(name = "Staffmaster.findByDateOfJoining", query = "SELECT s FROM Staffmaster s WHERE s.dateOfJoining = :dateOfJoining"),
    @NamedQuery(name = "Staffmaster.findByposition", query = "SELECT s FROM Staffmaster s WHERE s.position = :position")})
public class Staffmaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "staffid")
    private Integer staffid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age")
    private int age;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salary")
    private int salary;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "id_number")
    private String idNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_joining")
    @Temporal(TemporalType.DATE)
    private Date dateOfJoining;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "id_type")
    private String id_type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "position")
    private String position;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false)
    private Restaurantmaster restaurantId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staffId")
    private Collection<StaffTransaction> staffTransactionCollection;

    public Staffmaster() {
    }

    public Staffmaster(Integer staffid) {
        this.staffid = staffid;
    }

    public Staffmaster(Integer staffid, String name, String surname, int age, int salary, String idNumber, Date dateOfJoining, String id_type, String position) {
        this.staffid = staffid;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
        this.idNumber = idNumber;
        this.dateOfJoining = dateOfJoining;
        this.id_type = id_type;
        this.position = position;
    }

    public Integer getStaffid() {
        return staffid;
    }

    public void setStaffid(Integer staffid) {
        this.staffid = staffid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    
    public Restaurantmaster getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurantmaster restaurantId) {
        this.restaurantId = restaurantId;
    }

    @XmlTransient
//        @JsonbTransient

    public Collection<StaffTransaction> getStaffTransactionCollection() {
        return staffTransactionCollection;
    }

    public void setStaffTransactionCollection(Collection<StaffTransaction> staffTransactionCollection) {
        this.staffTransactionCollection = staffTransactionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staffid != null ? staffid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Staffmaster)) {
            return false;
        }
        Staffmaster other = (Staffmaster) object;
        if ((this.staffid == null && other.staffid != null) || (this.staffid != null && !this.staffid.equals(other.staffid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Staffmaster[ staffid=" + staffid + " ]";
    }
    
}
