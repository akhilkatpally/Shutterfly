import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

//import static JsonKeys.*;


/**
 * Created by akhil on 2/25/17.
 * Validations for required fields are present. Events are added only if all the required fields are present, otherwise an error is thrown and the event is not ingested into the data.
 * According to the Verb operations are performed. If it is NEW it is appended otherwise it is Updated.
 * This code works even if the order of the fields are different in the events and also if the order of the events are different.
 * Assuming that the total_amount field in the order event is always in the format "12.34 USD",(number followd by space), not handeled other cases.
 */

public class Ingestion {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    Data data = new Data();
    final String TYPE = "type";
    final String VERB = "verb";
    final String KEY = "key";
    final String EVENT_TIME = "event_time";
    final String LAST_NAME = "last_name";
    final String ADR_CITY = "adr_city";
    final String ADR_STATE = "adr_state";
    final String CUSTOMER_ID = "customer_id";
    final String TAGS_KEY = "tags_key";
    final String TAGS_VALUE = "tags_value";
    final String CAMERA_MAKE = "camera_make";
    final String CAMERA_MODEL = "camera_model";
    final String TOTAL_AMOUNT = "total_amount";

    public void ingest(final String json) throws IOException, ParseException {
        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(json);


            if (jsonNode.isArray()) {
                int size = jsonNode.size();
                int i = 0;
                while (i < size) {

                    ArrayNode node = (ArrayNode) jsonNode;
                    JsonNode jsonNode1 = node.get(i);
                    if (jsonNode1.isObject()) {
                        JsonNode type = jsonNode1.get(TYPE);
                        if(type!=null){
                            switch (type.getTextValue()) {
                                case "CUSTOMER":
                                    if(checkCustomerRequiredFields(jsonNode1)){
                                        Customer customer = processCustomer(jsonNode1);
                                        if (customer != null) {
                                            data.setCustomerdata(customer);
                                        }
                                    }else{
                                        System.out.println("ERROR: Required Fields in the Customer event are not present");
                                    }

                                    break;
                                case "SITE_VISIT":
                                    if(checkSiteVisitRequiredFields(jsonNode1)){
                                        SiteVisit siteVisit = processSiteVisit(jsonNode1);
                                        data.setSitedata(siteVisit);
                                    }else{
                                        System.out.println("ERROR: Required Fields in the SiteVisit event are not present");
                                    }

                                    break;
                                case "IMAGE":
                                    if(checkImageRequiredFields(jsonNode1)){
                                        ImageUpload imageUpload = processImage(jsonNode1);
                                        data.setImagedata(imageUpload);
                                    }else{
                                        System.out.println("ERROR: Required Fields in the Image event are not present");
                                    }

                                    break;
                                case "ORDER":
                                    if(checkOrderRequiredFields(jsonNode1)){
                                        Order order = processOrder(jsonNode1);
                                        if (order != null) {
                                            data.setOrderdata(order);
                                        }
                                    }else{
                                        System.out.println("ERROR: Required Fields in the Order event are not present");
                                    }


                                    break;
                                default:
                                    System.out.println("ERROR: Unknown event type");
                            }
                        }else{
                            System.out.println("ERROR: Type filed is not present in the event");
                        }

                    }

                    i++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean checkCustomerRequiredFields(JsonNode jsonNode1) {

        if ((jsonNode1.findPath(VERB).asText()!=null) & (jsonNode1.findPath(KEY).asText()!=null) & (jsonNode1.findPath(EVENT_TIME).asText()!=null)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkImageRequiredFields(JsonNode jsonNode1) {
        if ((jsonNode1.findPath(VERB).asText()!=null) & (jsonNode1.findPath(KEY).asText()!=null) & (jsonNode1.findPath(EVENT_TIME).asText()!=null) & (jsonNode1.findPath(CUSTOMER_ID).asText()!=null)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkSiteVisitRequiredFields(JsonNode jsonNode1) {
        if ((jsonNode1.findPath(VERB).asText()!=null) & (jsonNode1.findPath(KEY).asText()!=null) & (jsonNode1.findPath(EVENT_TIME).asText()!=null) & (jsonNode1.findPath(CUSTOMER_ID).asText()!=null)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkOrderRequiredFields(JsonNode jsonNode1) {
        if ((jsonNode1.findPath(VERB).asText()!=null) & (jsonNode1.findPath(KEY).asText()!=null) & (jsonNode1.findPath(EVENT_TIME).asText()!=null) & (jsonNode1.findPath(CUSTOMER_ID).asText()!=null) & (jsonNode1.findPath(TOTAL_AMOUNT).asText()!=null)) {
            return true;
        } else {
            return false;
        }
    }

    private Order processOrder(JsonNode jsonNode) {
        // When an order event is received instead of directly appending, i am checking for its verb and based on that either
        // i am appending or updating the existing order.

        if (jsonNode.findPath(VERB).getTextValue().equals("NEW")) {
            return processNewOrder(jsonNode);
        } else if (jsonNode.findPath(VERB).getTextValue().equals("UPDATE")) {
            updateOldOrder(jsonNode);

        } else {
            System.out.println("Error: verb filed in order event unknown");
        }
        return null;
    }



    private Order processNewOrder(JsonNode jsonNode) {
        final Order order = new Order();
        order.setType(jsonNode.findPath(TYPE).getTextValue());
        order.setVerb(jsonNode.findPath(VERB).getTextValue());
        order.setKey(jsonNode.findPath(KEY).getTextValue());
        order.setEvent_time(toDate(jsonNode.findPath(EVENT_TIME).getTextValue()));
        order.setCustomer_id(jsonNode.findPath(CUSTOMER_ID).getTextValue());
        order.setTotal_amount(Double.parseDouble(jsonNode.findPath(TOTAL_AMOUNT).getTextValue().split(" ")[0]));
        return order;
    }

    // Assumption: Since difference between an New and Update order is not given exactly i am considering them that they would be within the same week, so accordingly i am updating the existing order.
    private Order updateOldOrder(JsonNode jsonNode) {
        HashMap<String, HashMap<Long, ArrayList<Order>>> orderdata = data.getOrderdata();
        String customer_id = jsonNode.findPath(CUSTOMER_ID).getTextValue();
        if (orderdata.containsKey(customer_id)) {
            long weeknumber = (toDate(jsonNode.findPath(EVENT_TIME).getTextValue()).getTime()) / 604800000;
            if (orderdata.get(customer_id).containsKey(weeknumber)) {
                ArrayList<Order> orders = orderdata.get(customer_id).get(weeknumber);
                int size = orders.size();
                int i = 0;
                while (i < size) {
                    String keycustomerid = jsonNode.findPath(CUSTOMER_ID).getTextValue();
                    if (orders.get(i).getCustomer_id().equals(keycustomerid)) {
                        //System.out.print("updating Existing Customer");
                        orders.get(i).setVerb(jsonNode.findPath(VERB).getTextValue());
                        orders.get(i).setVerb(jsonNode.findPath(KEY).getTextValue());
                        orders.get(i).setEvent_time(toDate(jsonNode.findPath(EVENT_TIME).getTextValue()));
                        // I am assuming that the total_amount filed contains first the amount followed by space and followed by Currency
                        orders.get(i).setTotal_amount(Double.parseDouble(jsonNode.findPath(TOTAL_AMOUNT).getTextValue().split(" ")[0]));
                        return null;
                    }
                    i++;
                }
            }
        } else {
            System.out.println("Error: Customer_id used for updating the order is wrong");
        }

        return null;
    }

    private ImageUpload processImage(JsonNode jsonNode) {
        ImageUpload imageupload = new ImageUpload();
        imageupload.setType(jsonNode.findPath(TYPE).getTextValue());
        imageupload.setVerb(jsonNode.findPath(VERB).getTextValue());
        imageupload.setKey(jsonNode.findPath(KEY).getTextValue());
        imageupload.setEvent_time(toDate(jsonNode.findPath(EVENT_TIME).getTextValue()));
        imageupload.setCustomer_id(jsonNode.findPath(CUSTOMER_ID).getTextValue());
        imageupload.setCamera_make(jsonNode.findPath(CAMERA_MAKE).getTextValue());
        imageupload.setCamera_model(jsonNode.findPath(CAMERA_MODEL).getTextValue());
        return imageupload;
    }

    private SiteVisit processSiteVisit(JsonNode jsonNode) throws IOException {
        final SiteVisit sitevisit = new SiteVisit();
        sitevisit.setType(jsonNode.findPath(TYPE).getTextValue());
        sitevisit.setVerb(jsonNode.findPath(VERB).getTextValue());
        sitevisit.setKey(jsonNode.findPath(KEY).getTextValue());
        sitevisit.setCustomer_id(jsonNode.findPath(CUSTOMER_ID).getTextValue());
        sitevisit.setEvent_time(toDate(jsonNode.findPath(EVENT_TIME).getTextValue()));

        JsonNode tagsjsonNode = OBJECT_MAPPER.readTree(jsonNode.findPath("tags").toString());
        HashMap<String, String> hashmap = new HashMap<String, String>();
        if (tagsjsonNode.isArray()) {
            int size = tagsjsonNode.size();
            int i = 0;
            while (i < size) {
                //System.out.println(tagsjsonNode.get(i).toString());
                //System.out.println(tagsjsonNode.get(i).getFieldNames().next());
                //System.out.println(tagsjsonNode.get(i).getElements().next().getTextValue());
                hashmap.put(tagsjsonNode.get(i).getFieldNames().next(), tagsjsonNode.get(i).getElements().next().getTextValue());
                i++;
            }
        }
        sitevisit.setTags(hashmap);
        return sitevisit;
    }

    private Customer processCustomer(JsonNode jsonNode) {
        // When an customer event is received instead of directly appending, i am checking for its verb and based on that either
        // i am appending or updating the existing customer.
        if (jsonNode.findPath(VERB).getTextValue().equals("NEW")) {
            return processNewCustomer(jsonNode);
        } else if (jsonNode.findPath(VERB).getTextValue().equals("UPDATE")) {
            updateExistingCustomer(jsonNode);

        } else {
            System.out.println("type filed in Customer event unknown");
        }
        return null;
    }

    private Customer processNewCustomer(JsonNode jsonNode) {
        final Customer customer = new Customer();
        customer.setType(jsonNode.findPath(TYPE).getTextValue());
        customer.setVerb(jsonNode.findPath(VERB).getTextValue());
        customer.setKey(jsonNode.findPath(KEY).getTextValue());
        customer.setEvent_time(toDate(jsonNode.findPath(EVENT_TIME).getTextValue()));
        customer.setAdr_city(jsonNode.findPath(ADR_CITY).getTextValue());
        customer.setAdr_state(jsonNode.findPath(ADR_STATE).getTextValue());
        customer.setLast_name(jsonNode.findPath(LAST_NAME).getTextValue());
        return customer;
    }


    private Customer updateExistingCustomer(JsonNode jsonNode) {
        ArrayList<Customer> customerdata = data.getCustomerdata();
        //Searching in the Customer data if found it will update the existing customer record
        int size = customerdata.size();
        int i = 0;
        while (i < size) {
            String key = jsonNode.findPath(KEY).getTextValue();
            if (customerdata.get(i).getKey().equals(key)) {
                //System.out.print("updating Existing Customer");
                customerdata.get(i).setVerb(jsonNode.findPath(VERB).getTextValue());
                customerdata.get(i).setEvent_time(toDate(jsonNode.findPath(EVENT_TIME).getTextValue()));
                customerdata.get(i).setAdr_city(jsonNode.findPath(ADR_CITY).getTextValue());
                customerdata.get(i).setAdr_state(jsonNode.findPath(ADR_STATE).getTextValue());
                customerdata.get(i).setLast_name(jsonNode.findPath(LAST_NAME).getTextValue());
                return null;
            }
            i++;
        }
        System.out.print("Error: Customer Not found");
        return null;

    }

    //parse the date from string to date type
    private Date toDate(String textValue) {
        try {
            return DATE_FORMAT.parse(textValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}









































