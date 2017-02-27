import java.util.Date;

/**
 * Created by akhil on 2/25/17.
 * I am not currently checking if the order to be inserted, (whether that customer_id) is present in the customer data or not and similarly in the image data or not.
 *
 */
public class Order {
    private String type;
    private String verb;
    private String key;
    private Date event_time;
    private String customer_id;
    private double total_amount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public Date getEvent_time() {
        return event_time;
    }

    public void setEvent_time(Date event_time) {
        this.event_time = event_time;
    }
}
