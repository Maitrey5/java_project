/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:GenericRestResourceforwaiters
 * [generic]<br>
 * USAGE:
 * <pre>
 *        realclientforwaiters client = new realclientforwaiters();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class realclientforwaiters {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:9090/restaurant_management_system/resources";

    public realclientforwaiters() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("genericwaiter");
    }

    public void delete_item_to_order(String ordermenuid, String menuid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("delete_item_to_order/{0}/{1}", new Object[]{ordermenuid, menuid})).request().delete();
    }

    public <T> T get_orders_by_restaurant(Class<T> responseType, String restaurant_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("get_orders_by_restaurant/{0}", new Object[]{restaurant_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void add_order_to_restaurant(Object requestEntity, String restaurant_id, String order_date, String table_id, String noofpeople) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("add_order_to_restaurant/{0}/{1}/{2}/{3}", new Object[]{restaurant_id, order_date, table_id, noofpeople})).request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).post(jakarta.ws.rs.client.Entity.entity(requestEntity, jakarta.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T get_bills_by_restaurant(Class<T> responseType, String restaurant_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("get_bills_by_restaurant/{0}", new Object[]{restaurant_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void add_transaction_to_restaurant(String restaurant_id, String amount, String transaction_type, String description, String Date, String time) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("add_transaction_to_restaurant/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{restaurant_id, amount, transaction_type, description, Date, time})).request().post(null);
    }

    public void delete_order(String orderid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("delete_order/{0}", new Object[]{orderid})).request().delete();
    }

    public void update_item_to_order(String ordermenuid, String menuid, String qunatity) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("update_item_to_order/{0}/{1}/{2}", new Object[]{ordermenuid, menuid, qunatity})).request().put(null);
    }

    public <T> T get_all_menuitems_by_restaurant(Class<T> responseType, String restaurant_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("get_all_menuitems_by_restaurant/{0}", new Object[]{restaurant_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void add_item_to_order(String ordermenuid, String menuid, String qunatity) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("add_item_to_order/{0}/{1}/{2}", new Object[]{ordermenuid, menuid, qunatity})).request().post(null);
    }

    public <T> T search_menu(Class<T> responseType, String itemname, String restaurantId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("search_menu/{0}/{1}", new Object[]{itemname, restaurantId}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void add_bill_to_restaurant(String order_id, String restaurant_id, String total_amount, String discount, String final_amount, String final_payble_amount_with_tax, String datetime, String modeofpayment, String transaction_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("add_bill_to_restaurant/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{order_id, restaurant_id, total_amount, discount, final_amount, final_payble_amount_with_tax, datetime, modeofpayment, transaction_id})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
