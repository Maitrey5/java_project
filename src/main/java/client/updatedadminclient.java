/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:GenericRestResource
 * [generic]<br>
 * USAGE:
 * <pre>
 *        updatedadminclient client = new updatedadminclient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class updatedadminclient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:9090/restaurant_management_system/resources";

    public updatedadminclient() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("generic");
    }

    public void add_transaction_to_staff(String restaurant_id, String staff_id, String amount, String date, String transaction_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("add_transaction_to_staff/{0}/{1}/{2}/{3}/{4}", new Object[]{restaurant_id, staff_id, amount, date, transaction_id})).request().post(null);
    }

    public void delete_category(String category_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("delete_category/{0}", new Object[]{category_id})).request().delete();
    }

    public <T> T get_bills_by_restaurant(Class<T> responseType, String restaurant_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("get_bills_by_restaurant/{0}", new Object[]{restaurant_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void add_staff_to_restaurant(String restaurant_id, String name, String surname, String age, String salary, String id_number, String date_of_joining, String image, String id_image) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("add_staff_to_restaurant/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{restaurant_id, name, surname, age, salary, id_number, date_of_joining, image, id_image})).request().post(null);
    }

    public void update_transaction_to_staff(String staff_transaction_id, String restaurant_id, String staff_id, String amount, String date, String transaction_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("update_transaction_to_staff/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{staff_transaction_id, restaurant_id, staff_id, amount, date, transaction_id})).request().put(null);
    }

    public void book_table_by_restaurant(String table_id, String restaurant_id, String booking_time, String dine_in_time, String booking_date, String dine_in_date, String no_of_peoples, String contact_no, String customer_name) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("book_table_by_restaurant/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{table_id, restaurant_id, booking_time, dine_in_time, booking_date, dine_in_date, no_of_peoples, contact_no, customer_name})).request().post(null);
    }

    public <T> T add_restaurant(Class<T> responseType, String restaurant_name, String restaurant_address, String restaurant_contactno, String restaurant_email, String restaurant_city, String restaurant_state, String restaurant_country, String restaurant_pincode, String created_at, String updated_at, String is_active) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("add/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}/{10}", new Object[]{restaurant_name, restaurant_address, restaurant_contactno, restaurant_email, restaurant_city, restaurant_state, restaurant_country, restaurant_pincode, created_at, updated_at, is_active})).request().post(null, responseType);
    }

    public <T> T search_menu(Class<T> responseType, String itemname, String restaurantId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("search_menu/{0}/{1}", new Object[]{itemname, restaurantId}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void delete_table_by_restaurant(String table_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("delete_table_by_restaurant/{0}", new Object[]{table_id})).request().delete();
    }

    public <T> T get_orders_by_restaurant(Class<T> responseType, String restaurant_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("get_orders_by_restaurant/{0}", new Object[]{restaurant_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T get_inventory_item_by_restaurant(Class<T> responseType, String restaurant_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("get_inventory_item_by_restaurant/{0}", new Object[]{restaurant_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void update_restaurant(String restaurant_id, String restaurant_name, String restaurant_address, String restaurant_contactno, String restaurant_email, String restaurant_city, String restaurant_state, String restaurant_country, String restaurant_pincode, String created_at, String updated_at, String is_active) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("update/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}/{10}/{11}", new Object[]{restaurant_id, restaurant_name, restaurant_address, restaurant_contactno, restaurant_email, restaurant_city, restaurant_state, restaurant_country, restaurant_pincode, created_at, updated_at, is_active})).request().put(null);
    }

    public <T> T get_tablesbooking_by_restaurant(Class<T> responseType, String restaurant_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("get_tablesbooking_by_restaurant/{0}", new Object[]{restaurant_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void delete_transaction_to_staff(String staff_transaction_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("delete_transaction_to_staff/{0}", new Object[]{staff_transaction_id})).request().delete();
    }

    public <T> T get_all_transaction_by_restaurant(Class<T> responseType, String restaurant_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("get_all_transaction_by_restaurant/{0}", new Object[]{restaurant_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void update_menu(String menu_id, String restaurant_id, String category_id, String item_name, String item_price, String description, String is_avalaible, String updated_at, String item_type) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("update_menu/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{menu_id, restaurant_id, category_id, item_name, item_price, description, is_avalaible, updated_at, item_type})).request().put(null);
    }

    public void delete_order(String orderid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("delete_order/{0}", new Object[]{orderid})).request().delete();
    }

    public void update_table_by_restaurant(String table_booking_id, String table_id, String restaurant_id, String booking_time, String dine_in_time, String booking_date, String dine_in_date, String no_of_peoples, String contact_no, String customer_name) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("update_table_by_restaurant/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}", new Object[]{table_booking_id, table_id, restaurant_id, booking_time, dine_in_time, booking_date, dine_in_date, no_of_peoples, contact_no, customer_name})).request().post(null);
    }

    public void add_category(String restaurant_id, String category_name, String updated_at) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("add_category/{0}/{1}/{2}", new Object[]{restaurant_id, category_name, updated_at})).request().post(null);
    }

    public <T> T get_tables_by_restaurant(Class<T> responseType, String restaurant_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("get_tables_by_restaurant/{0}", new Object[]{restaurant_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void update_category(String category_id, String restaurant_id, String category_name, String updated_at) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("update_category/{0}/{1}/{2}/{3}", new Object[]{category_id, restaurant_id, category_name, updated_at})).request().put(null);
    }

    public void add_item_in_inventory(String restaurant_id, String quantity, String amount, String date, String time, String description, String transaction_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("add_item_in_inventory/{0}/{1}/{2}/{3}/{4}/{5}/{6}", new Object[]{restaurant_id, quantity, amount, date, time, description, transaction_id})).request().post(null);
    }

    public <T> T search_restaurant(Class<T> responseType, String restaurant_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("search/{0}", new Object[]{restaurant_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void update_item_to_inventory(String inventoryid, String restaurant_id, String quantity, String amount, String date, String time, String description, String transaction_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("update_item_to_inventory/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{inventoryid, restaurant_id, quantity, amount, date, time, description, transaction_id})).request().put(null);
    }

    public <T> T search_table(Class<T> responseType, String rid, String table_no) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("search_table/{0}/{1}", new Object[]{rid, table_no}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void delete_table_booking_by_restaurant(String table_booking_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("delete_table_booking_by_restaurant/{0}", new Object[]{table_booking_id})).request().delete();
    }

    public void delete_staff_to_restaurant(String Staff_id, String restaurant_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("delete_staff_to_restaurant/{0}/{1}", new Object[]{Staff_id, restaurant_id})).request().delete();
    }

    public <T> T get_all_staff_transaction_by_restaurant(Class<T> responseType, String restaurant_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("get_all_staff_transaction_by_restaurant/{0}", new Object[]{restaurant_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void delete_menu(String menu_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("delete_menu/{0}", new Object[]{menu_id})).request().delete();
    }

    public <T> T get_all_menuitems_by_restaurant(Class<T> responseType, String restaurant_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("get_all_menuitems_by_restaurant/{0}", new Object[]{restaurant_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void add_table_to_restaurant(String restaurant_id, String table_number, String capacity) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("add_table_to_restaurant/{0}/{1}/{2}", new Object[]{restaurant_id, table_number, capacity})).request().post(null);
    }

    public void delete_item_in_inventory(String inventory_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("delete_item_in_inventory/{0}", new Object[]{inventory_id})).request().delete();
    }

    public void add_menu(String restaurant_id, String category_id, String item_name, String item_price, String description, String is_avalaible, String updated_at, String item_type) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("add_menu/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{restaurant_id, category_id, item_name, item_price, description, is_avalaible, updated_at, item_type})).request().post(null);
    }

    public <T> T get_all_staff_by_restaurant(Class<T> responseType, String restaurant_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("get_all_staff_by_restaurant/{0}", new Object[]{restaurant_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void delete_restaurant(String restaurant_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("delete/{0}", new Object[]{restaurant_id})).request().delete();
    }

    public void add_user_of_restaurant(String username, String password, String restaurant_id, String role) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("add_user_of_restaurant/{0}/{1}/{2}/{3}", new Object[]{username, password, restaurant_id, role})).request().post(null);
    }

    public void add_transaction_to_restaurant(String restaurant_id, String amount, String transaction_type, String description, String Date, String time) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("add_transaction_to_restaurant/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{restaurant_id, amount, transaction_type, description, Date, time})).request().post(null);
    }

    public <T> T get_categories_by_restaurant(Class<T> responseType, String restaurant_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("get_categories_by_restaurant/{0}", new Object[]{restaurant_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void update_table_to_restaurant(Object requestEntity, String table_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("update_table_to_restaurant/{0}", new Object[]{table_id})).request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).put(jakarta.ws.rs.client.Entity.entity(requestEntity, jakarta.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void update_staff_to_restaurant(String Staff_id, String restaurant_id, String name, String surname, String age, String salary, String id_number, String date_of_joining, String image, String id_image) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("update_staff_to_restaurant/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}", new Object[]{Staff_id, restaurant_id, name, surname, age, salary, id_number, date_of_joining, image, id_image})).request().put(null);
    }

    public void close() {
        client.close();
    }
    
}
