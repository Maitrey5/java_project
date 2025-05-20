/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.Billmaster;
import entity.Menumaster;
import entity.OrderMenuJointable;
import entity.Ordermaster;
import entity.Restaurantmaster;
import entity.Tablemaster;
import entity.Transactionmaster;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
@Stateless
public class ejbforwaiters implements ejbforwaitersLocal {

    @PersistenceContext(unitName = "restaurant_pu")
    EntityManager em;

    @Override
    public Menumaster search_menu(String itemname, Integer restaurantId) {

        return (Menumaster) em.createNamedQuery("Menumaster.findByItemNameandrestaurantid").setParameter("itemName", itemname).setParameter("restaurantId", restaurantId).getSingleResult();

    }

    @Override
    public Collection<Menumaster> get_all_menuitems_by_restaurant(Integer restaurant_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add_order_to_restaurant(Integer restaurant_id, Date oreder_date, Integer table_id, Integer noofpeople, List<Integer> menuids, List<Integer> quantity) {

        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class, restaurant_id);
        Tablemaster t = (Tablemaster) em.find(Tablemaster.class, table_id);

        Ordermaster o = new Ordermaster();

        o.setRestaurantId(r);
        o.setNoofpeople(noofpeople);
        o.setTableId(t);
        o.setOrederDate(oreder_date);

        Collection<OrderMenuJointable> coll_menu_ord = new ArrayList<>();

        Integer i = 0;
        Integer quantityofitem = 0;

        System.err.println("ooooooooooooooooooooooooooooooooooo");
        System.out.println(menuids);
        System.out.println(quantity);

        for (Integer id : menuids) {
            Menumaster m = (Menumaster) em.find(Menumaster.class, id);
            System.err.println("oppppppppppppppp");

            if (m != null) {

                System.err.println("wwwwwwwwwwwwwwwwwwww");

                //coll_menu_ord.add(m);
                quantityofitem = quantity.get(i);

                OrderMenuJointable om = new OrderMenuJointable();

                om.setMenuId(m);
                om.setOrderId(o);
                om.setQuantity(quantityofitem);
                Integer totalprice = (quantityofitem) * (m.getItemPrice());

                om.setTotalPrice(totalprice);

                em.persist(om);

//              Collection<OrderMenuJointable> coll_order_menu = o.getOrderMenuJointableCollection();
                coll_menu_ord.add(om);
                o.setOrderMenuJointableCollection(coll_menu_ord);
                //em.merge(t);

                Collection<OrderMenuJointable> p = m.getOrderMenuJointableCollection();
                p.add(om);
                m.setOrderMenuJointableCollection(coll_menu_ord);

            }
        }

        em.persist(o);

        Collection<Ordermaster> coll_ord_rst = r.getOrdermasterCollection();
        coll_ord_rst.add(o);
        r.setOrdermasterCollection(coll_ord_rst);

        Collection<Ordermaster> coll_ord_tbl = t.getOrdermasterCollection();
        coll_ord_tbl.add(o);
        t.setOrdermasterCollection(coll_ord_rst);

    }

    @Override
    public void update_item_to_order(Integer ordermenuid, Integer menuid, Integer qunatity) {

        Menumaster m = (Menumaster) em.find(Menumaster.class, menuid);

        OrderMenuJointable om = (OrderMenuJointable) em.createNamedQuery("OrderMenuJointable.findByOrdermenuid").setParameter("ordermenuid", ordermenuid).getSingleResult();

        om.setQuantity(qunatity);

        Integer i = 0;
        i = (m.getItemPrice()) * (qunatity);
        om.setTotalPrice(i);

        em.merge(om);

    }

    @Override
    public void add_item_to_order(Integer orderid, Integer menuid, Integer qunatity) {

        Menumaster m = (Menumaster) em.find(Menumaster.class, menuid);
        Ordermaster oo = (Ordermaster) em.find(Ordermaster.class, orderid);

        OrderMenuJointable orr = new OrderMenuJointable();
        orr.setMenuId(m);
        orr.setOrderId(oo);
        orr.setQuantity(qunatity);

        Integer i = 0;
        i = (m.getItemPrice()) * (qunatity);
        orr.setTotalPrice(i);

        em.persist(orr);

        Collection<OrderMenuJointable> mm = oo.getOrderMenuJointableCollection();
        mm.add(orr);
        oo.setOrderMenuJointableCollection(mm);

        Collection<OrderMenuJointable> p = m.getOrderMenuJointableCollection();
        p.add(orr);
        m.setOrderMenuJointableCollection(p);

    }

    @Override
    public void delete_item_to_order(Integer ordermenuid, Integer menuid) {

        Menumaster m = (Menumaster) em.find(Menumaster.class, menuid);

        OrderMenuJointable om = (OrderMenuJointable) em.createNamedQuery("OrderMenuJointable.findByOrdermenuid").setParameter("ordermenuid", ordermenuid).getSingleResult();

        em.remove(om);

        Collection<OrderMenuJointable> Coll_item_menu = m.getOrderMenuJointableCollection();
        Coll_item_menu.remove(om);

        em.merge(m);

    }

    @Override
    public void delete_order(Integer orderid) {

        Ordermaster om = em.find(Ordermaster.class, orderid);

        Restaurantmaster r = om.getRestaurantId();
        Collection<Ordermaster> rr = r.getOrdermasterCollection();
        rr.remove(om);
        em.merge(r);

        Tablemaster tr = om.getTableId();
        Collection<Ordermaster> rrr = tr.getOrdermasterCollection();
        rrr.remove(om);
        em.merge(tr);

        Collection<OrderMenuJointable> ee = om.getOrderMenuJointableCollection();

        for (OrderMenuJointable k : ee) {
            em.remove(k);
        }

        em.remove(om);

    }

    @Override
    public Collection<Ordermaster> get_orders_by_restaurant(Integer restaurant_id) {

        Restaurantmaster r = (Restaurantmaster) em.find(Restaurantmaster.class, restaurant_id);

        return r.getOrdermasterCollection();
    }

    @Override
    public void add_bill_to_restaurant(Integer order_id, Integer restaurant_id, Integer total_amount, Integer discount, Integer final_amount, Integer final_payble_amount_with_tax, Date datetime, String modeofpayment, Integer transaction_id) {

        Ordermaster om = em.find(Ordermaster.class, order_id);
        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);
        Transactionmaster tr = em.find(Transactionmaster.class, transaction_id);

        Billmaster b = new Billmaster();
        b.setRestaurantId(r);
        b.setOrderId(om);
        b.setTotalAmount(total_amount);
        b.setDiscount(discount);
        b.setFinalAmount(final_amount);
        b.setFinalPaybleAmountWithtax(final_payble_amount_with_tax);
        b.setDateTime(datetime);
        b.setModeofpayment(modeofpayment);
        b.setTransactionId(tr);

        em.persist(b);

        Collection<Billmaster> bm = om.getBillmasterCollection();
        bm.add(b);
        om.setBillmasterCollection(bm);

        Collection<Billmaster> bmr = r.getBillmasterCollection();
        bmr.add(b);
        r.setBillmasterCollection(bmr);

        Collection<Billmaster> trc = tr.getBillmasterCollection();
        trc.add(b);
        tr.setBillmasterCollection(trc);

    }

    @Override
    public Collection<Billmaster> get_bills_by_restaurant(Integer restaurant_id) {

        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);
        return r.getBillmasterCollection();

    }

    @Override
    public void add_transaction_to_restaurant(Integer restaurant_id, Integer amount, String transaction_type, String description, Date Date, Time time) {

        Restaurantmaster r = em.find(Restaurantmaster.class, restaurant_id);

        Transactionmaster t = new Transactionmaster();

        t.setAmount(amount);
        t.setRestaurantId(r);
        t.setDate(Date);
        t.setDescription(description);
        t.setTransactionType(transaction_type);
        t.setTime(time);

        em.persist(t);

        Collection<Transactionmaster> faf = r.getTransactionmasterCollection();
        faf.add(t);
        r.setTransactionmasterCollection(faf);

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
