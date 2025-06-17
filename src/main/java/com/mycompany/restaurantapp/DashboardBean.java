package com.mycompany.restaurantapp;

import beans.TableBean;
import ejb.ejb_restaurant_crudLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import entity.Tablemaster;
import entity.Staffmaster;
import java.util.Collection;
import record.KeepRecord;

@Named("dashboardBean")
@SessionScoped
public class DashboardBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean showDashboardView = true; // Default to true to show dashboard on startup

    @Inject
    private TableBean tableBean;
    
    @Inject
    private KeepRecord keepRecord;

    private long totalOrderCount;
    private long totalTableCount;
    private long totalStaffCount;
    private long activeStaffCount;

    @EJB
    private ejb_restaurant_crudLocal resBean;

    public DashboardBean() {
        // Default constructor
    }

    public String showDashboard() {
        this.showDashboardView = true;

        // Hide all other views
        if (tableBean != null) {
            tableBean.setShowTableForm(false);
            tableBean.setShowbookingForm(false);
            tableBean.setShowcategory(false);
            tableBean.setShowmenuform(false);
            tableBean.setShowstaff(false);
            tableBean.setShowuser(false);
        }

        return null;
    }

    // Called from other beans when they show their views
    public void hideDashboard() {
        this.showDashboardView = false;
    }

    public boolean isShowDashboardView() {
        return showDashboardView;
    }

    public void setShowDashboardView(boolean showDashboardView) {
        this.showDashboardView = showDashboardView;
    }

    public long getTotalOrderCount() {
        totalOrderCount = resBean.countAllOrders();
        return totalOrderCount;
    }
    
    public long getTotalTableCount() {
        Integer restaurantId = keepRecord.getIi();
        Collection<Tablemaster> tables = resBean.searchtablebyrestaurant(restaurantId);
        totalTableCount = tables != null ? tables.size() : 0;
        return totalTableCount;
    }
    
    public long getTotalStaffCount() {
        Integer restaurantId = keepRecord.getIi();
        Collection<Staffmaster> staff = resBean.get_all_staff_by_restaurant(restaurantId);
        totalStaffCount = staff != null ? staff.size() : 0;
        return totalStaffCount;
    }
    
    public long getActiveStaffCount() {
        // This would typically filter active staff based on some criteria
        // Using a placeholder of half of total staff for now
        activeStaffCount = Math.round(getTotalStaffCount() * 0.5);
        return activeStaffCount;
    }
}
