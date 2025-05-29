/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class DashboardBean implements Serializable{

    /**
     * Creates a new instance of DashboardBean
     */
    public DashboardBean() {
    }
    


    private BarChartModel barChartModel;

    @PostConstruct
    public void init() {
        barChartModel = new BarChartModel();
        ChartData data = new ChartData();

        // Offline Sales dataset
        BarChartDataSet offline = new BarChartDataSet();
        offline.setLabel("Offline Sales");
        offline.setData(Arrays.asList(300, 500, 100, 200, 400));
        offline.setBackgroundColor("rgba(255, 99, 132, 0.5)");

        // Online Sales dataset
        BarChartDataSet online = new BarChartDataSet();
        online.setLabel("Online Sales");
        online.setData(Arrays.asList(400, 200, 300, 500, 600));
        online.setBackgroundColor("rgba(54, 162, 235, 0.5)");

        data.addChartDataSet(offline);
        data.addChartDataSet(online);

        // X-axis labels
        List<String> labels = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May");
        data.setLabels(labels);

        barChartModel.setData(data);
    }

    public BarChartModel getBarChartModel() {
        return barChartModel;
    }
}

    

