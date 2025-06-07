/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import client.updatedadminclient;
import entity.Menumaster;
import entity.Staffmaster;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import org.primefaces.model.file.UploadedFile;
import record.KeepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "staffbean")
@ViewScoped
public class staffbean implements Serializable {

    /**
     * Creates a new instance of staffbean
     */
    @Inject
    KeepRecord keepRecord;

    updatedadminclient em = new updatedadminclient();
    Response rs;

    GenericType<Collection<Staffmaster>> gstaff = new GenericType<Collection<Staffmaster>>() {
    };
    Collection<Staffmaster> staff = new ArrayList<>();

    private Boolean showformlist = false;
    private Boolean showstafflist = true;
    private Boolean showstaffdetails = false;
    private String name;
    private String surname;
    private Integer age;
    private Integer salary;
    private String idno;
    private LocalDate datee;
    private String idtype;
    private String position;
    private Staffmaster ss;
    private UploadedFile file;
    private UploadedFile fileid;

    public staffbean() {
        System.err.println("constructor called ");
    }

    @PostConstruct
    public void init() {
        if (keepRecord != null) {
            staff = getmenudata();
        } else {
            System.err.println("keepRecord is NULL in onlymenubean.init()");
        }
    }

    public void updatestaff() {
        
                System.err.println("hello in bean");


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // or "dd/MM/yyyy", etc.

        String dateString = datee.format(formatter);
        em.update_staff_to_restaurant(String.valueOf(ss.getStaffid()), String.valueOf(keepRecord.getIi()), name, surname, String.valueOf(age), String.valueOf(salary), idno, dateString, idtype, position);
        staff = getmenudata();

        showstafflistfunc();

    }

    public void deletestaff() {

        em.delete_staff_to_restaurant(String.valueOf(ss.getStaffid()), String.valueOf(keepRecord.getIi()));
        staff = getmenudata();
        showstafflistfunc();

    }

    public Collection<Staffmaster> getmenudata() {

        Collection<Staffmaster> s;
        rs = em.get_all_staff_by_restaurant(Response.class, String.valueOf(keepRecord.getR_id()));
        s = rs.readEntity(gstaff);
        return s;

    }

    public String addstaff() {

        System.err.println("csddddddddddddddddddddddddddddddddddddddddddddd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // or "dd/MM/yyyy", etc.

        String dateString = datee.format(formatter);

        System.err.println("restaurant" + keepRecord.getIi() + "surname " + surname + " age " + age + "salry" + salary + "idno" + idno + "image" + position + "imageid" + idtype);

        em.add_staff_to_restaurant(String.valueOf(keepRecord.getIi()), name, surname, String.valueOf(age), String.valueOf(salary), idno, dateString, idtype, position);

        staff = getmenudata();
        showstafflistfunc();
        return null;
    }

    public void showformlistfunc() {

        showstafflist = false;
        showstaffdetails = false;
        showformlist = true;

    }

    public void showstaffdetailsfunc(Staffmaster s) {

        showstafflist = false;
        showformlist = false;
        showstaffdetails = true;

        this.ss = s;

        this.age = s.getAge();
        Date d = s.getDateOfJoining();
        LocalDate localDate = d.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        this.datee = localDate;
        this.idno = s.getIdNumber();
        this.idtype = s.getId_type();
        this.name = s.getName();
        this.surname =s.getSurname();
        this.position = s.getPosition();
        this.salary = s.getSalary();

    }

    public void showstafflistfunc() {

        System.err.println("bdhcbdasmcbbbbbbbbbbbbbbbbbbbbbb");
        showstaffdetails = false;
        showformlist = false;
        showstafflist = true;

    }

    public Boolean getShowformlist() {
        return showformlist;
    }

    public void setShowformlist(Boolean showformlist) {
        this.showformlist = showformlist;
    }

    public Boolean getShowstafflist() {
        return showstafflist;
    }

    public void setShowstafflist(Boolean showstafflist) {
        this.showstafflist = showstafflist;
    }

    public Boolean getShowstaffdetails() {
        return showstaffdetails;
    }

    public void setShowstaffdetails(Boolean showstaffdetails) {
        this.showstaffdetails = showstaffdetails;
    }

    public updatedadminclient getEm() {
        return em;
    }

    public void setEm(updatedadminclient em) {
        this.em = em;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public LocalDate getDatee() {
        return datee;
    }

    public void setDatee(LocalDate datee) {
        this.datee = datee;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile fileimage) {
        this.file = fileimage;
    }

    public UploadedFile getFileid() {
        return fileid;
    }

    public void setFileid(UploadedFile fileid) {
        this.fileid = fileid;
    }

    public Collection<Staffmaster> getStaff() {
        return staff;
    }

    public void setStaff(Collection<Staffmaster> staff) {
        this.staff = staff;
    }

}
