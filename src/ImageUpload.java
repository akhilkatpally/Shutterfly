import java.util.Date;

/**
 * Created by akhil on 2/25/17.
 * All the ImageUpload events will be identified and stored in the ImageUpload data.
 */
public class ImageUpload {
    private String type;
    private String verb;
    private String key;
    private Date event_time;
    private String customer_id;
    private String camera_make;
    private String camera_model;

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

    public String getCamera_make() {
        return camera_make;
    }

    public void setCamera_make(String camera_make) {
        this.camera_make = camera_make;
    }

    public String getCamera_model() {
        return camera_model;
    }

    public void setCamera_model(String camera_model) {
        this.camera_model = camera_model;
    }

    public Date getEvent_time() {
        return event_time;
    }

    public void setEvent_time(Date event_time) {
        this.event_time = event_time;
    }
}
