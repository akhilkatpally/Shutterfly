import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by akhil on 2/25/17.
 * All the SiteVist events will be identified and stored in the SiteVisit data.
 */
public class SiteVisit {
    private String type;
    private String verb;
    private String key;
    private Date event_time;
    private String customer_id;
    //Array of name value properties. I am assuming that name(key) in hasmap will be unique.. so i have just considered the HashMap data Structure.
    private HashMap<String, String> tags;

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

    public HashMap<String, String> getTags() {
        return tags;
    }

    public void setTags(HashMap<String, String> tags) {
        this.tags = tags;
    }

    public Date getEvent_time() {
        return event_time;
    }

    public void setEvent_time(Date event_time) {
        this.event_time = event_time;
    }
}
