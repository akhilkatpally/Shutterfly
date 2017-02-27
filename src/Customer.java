import java.util.Date;

/**
 * Created by akhil on 2/25/17.
 * All the Customer events will be identified and stored according in the Customer data. It is an arraylist,
 * we add the customer events to the customer array list if they are new otherwise the existing customer is updated
 */
public class Customer {
    private String type;
    private String verb;
    private String key;
    private Date event_time;
    private String last_name;
    private String adr_city;
    private String adr_state;

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


    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAdr_city() {
        return adr_city;
    }

    public void setAdr_city(String adr_city) {
        this.adr_city = adr_city;
    }

    public String getAdr_state() {
        return adr_state;
    }

    public void setAdr_state(String adr_state) {
        this.adr_state = adr_state;
    }

    public Date getEvent_time() {
        return event_time;
    }

    public void setEvent_time(Date event_time) {
        this.event_time = event_time;
    }
}
