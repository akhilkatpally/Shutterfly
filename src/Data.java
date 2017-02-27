import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by akhil on 2/25/17.
 * Customer, SiteVisit and ImageUpload datastructures are just an arraylist, all the corresponding events are simply appended to the arraylist.
 * Since the analysis is primarily on the orderdata i am storing them in the hashmap data structure, Where the key is customer_id and value is another hashmap.
 * In the second hashmap the key is the weeknumbers, and the values will be orders. Orders will be added according to the customer_id and weeknumber. So that it will be very efficient while retriving the data.
 * This particular design will work excellently for this analysis, but for other analysis this many not be effiecient.
 * Since i don't know what kind of analysis to be performed on this data later, i am taking the liberty and using this design.
 */
public class Data {

    private ArrayList<Customer> customerdata = new ArrayList<Customer>();
    private ArrayList<SiteVisit> sitedata = new ArrayList<SiteVisit>();
    private ArrayList<ImageUpload> imagedata = new ArrayList<ImageUpload>();
    private HashMap<String, HashMap<Long, ArrayList<Order>>> orderdata = new HashMap<String, HashMap<Long, ArrayList<Order>>>();


    //<editor-fold desc="Setters and Getters">
    public ArrayList<Customer> getCustomerdata() {
        return customerdata;
    }

    public void setCustomerdata(Customer customerdata) {
        this.customerdata.add(customerdata);
    }

    public ArrayList<SiteVisit> getSitedata() {
        return sitedata;
    }

    public void setSitedata(SiteVisit sitedata)
    {
        this.sitedata.add(sitedata);
    }

    public ArrayList<ImageUpload> getImagedata() {
        return imagedata;
    }

    public void setImagedata(ImageUpload imagedata)
    {
        this.imagedata.add(imagedata);
    }

    public HashMap<String, HashMap<Long, ArrayList<Order>>> getOrderdata() {
        return orderdata;
    }

    public void setOrderdata(Order order) {
        //getweeknumber();
        HashMap<Long, ArrayList<Order>> orderdata_value;
        ArrayList<Order> orderdata_list;

        String customer_id = order.getCustomer_id();
        long epochtime = order.getEvent_time().getTime();
        long weeknumber = (epochtime / 604800000);

        if (orderdata.containsKey(customer_id)) {
            // Enters into the if block only if the customer_id is already present in the orderdata hashmap
            orderdata_value = orderdata.get(customer_id);

            if (orderdata_value.containsKey(weeknumber)) {
                // Enters into the if block only if the weeknumber is present in the hashmap of that customer
                orderdata_list = orderdata_value.get(weeknumber);
                orderdata_list.add(order);
            } else {
                // Enters into the else block only if the weeknumber is not present in the hashmap of that customer.
                // We create a new ArrayList of Order and add the current order into that arraylist.
                // Finally we insert the array list into the hashmap.
                orderdata_list = new ArrayList<Order>();
                orderdata_list.add(order);
                orderdata_value.put(weeknumber, orderdata_list);
            }

        } else {
            // Enters into the else block only if that customer_id is not present in the hashmap. We insert a new customer_id and place the corresponding value into it.
            orderdata_value = new HashMap<Long, ArrayList<Order>>();
            orderdata_list = new ArrayList<Order>();
            orderdata_list.add(order);
            orderdata_value.put(weeknumber, orderdata_list);
        }
        //System.out.println("customer_id : "+customer_id);
        orderdata.put(customer_id, orderdata_value);
    }
    //</editor-fold>


}
