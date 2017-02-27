import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by akhil on 2/25/17.
 * This is the Main class, where i will call ingest() method and topXSimpleLTVCustomer method for analysing. I am assuming the input to the ingest method as a string which contains one or many events.
 */
public class ShutterflyCLV {
    public static void main(String[] args) throws ParseException, IOException {
        //testing the Data class
        //data
        DateFormat dateformat = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");


       Ingestion ingestion = new Ingestion();
        String eventdata = "[{\"type\": \"CUSTOMER\",  \"key\": \"96f55c7d8f42\", \"event_time\": \"2017-01-06T12:46:46.384Z\", \"last_name\": \"Smith\", \"adr_city\": \"Middletown\", \"adr_state\": \"AK\"},\n" +
                "{\"type\": \"SITE_VISIT\", \"verb\": \"NEW\", \"key\": \"ac05e815502f\", \"event_time\": \"2017-01-06T12:45:52.041Z\", \"customer_id\": \"96f55c7d8f42\", \"tags\": [{\"some key1\": \"some value1\"},{\"some key2\": \"some value2\"}]},\n" +
                "{\"type\": \"IMAGE\", \"verb\": \"UPLOAD\", \"key\": \"d8ede43b1d9f\", \"event_time\": \"2017-01-06T12:47:12.344Z\", \"customer_id\": \"96f55c7d8f42\", \"camera_make\": \"Canon\", \"camera_model\": \"EOS 80D\"},\n" +
                "{\"type\": \"ORDER\", \"verb\": \"NEW\", \"key\": \"68d84e5d1a43\", \"event_time\": \"2017-01-06T12:55:55.555Z\", \"customer_id\": \"96f55c7d8f42\", \"total_amount\": \"12.34 USD\"}]";
        ingestion.ingest(eventdata);
        String eventdata1 = "\n" +
                "[{\"type\": \"CUSTOMER\", \"verb\": \"NEW\", \"key\": \"10000akhil\", \"event_time\": \"2017-01-06T12:46:46.384Z\", \"last_name\": \"Katpal\", \"adr_city\": \"Middletown\", \"adr_state\": \"AK\"},\n" +
                "{\"type\": \"SITE_VISIT\", \"verb\": \"NEW\", \"key\": \"1000sitevisit\", \"event_time\": \"2017-01-06T12:45:52.041Z\", \"customer_id\": \"10000akhil\", \"tags\": {\"some key\": \"some value\"}},\n" +
                "{\"type\": \"IMAGE\", \"verb\": \"UPLOAD\", \"key\": \"1000image\", \"event_time\": \"2017-01-06T12:47:12.344Z\", \"customer_id\": \"10000akhil\", \"camera_make\": \"Canon\", \"camera_model\": \"EOS 80D\"},\n" +
                "{\"type\": \"ORDER\", \"verb\": \"NEW\", \"key\": \"1000order\", \"event_time\": \"2017-01-06T12:55:55.555Z\", \"customer_id\": \"10000akhil\", \"total_amount\": \"12.34 USD\"},\n" +
                "{\"type\": \"CUSTOMER\", \"verb\": \"UPDATE\", \"key\": \"10000akhil\", \"event_time\": \"2017-01-06T12:46:46.384Z\", \"last_name\": \"Katpally\", \"adr_city\": \"Middletown\", \"adr_state\": \"AK\"},\n" +
                "{\"type\": \"SITE_VISIT\", \"verb\": \"NEW\", \"key\": \"1001sitevisit\", \"event_time\": \"2017-01-06T12:45:52.041Z\", \"customer_id\": \"10000akhil\", \"tags\": {\"some key\": \"some value\"}},\n" +
                "{\"type\": \"IMAGE\", \"verb\": \"UPLOAD\", \"key\": \"1001image\", \"event_time\": \"2017-01-06T12:47:12.344Z\", \"customer_id\": \"10000akhil\", \"camera_make\": \"Canon\", \"camera_model\": \"EOS 80D\"},\n" +
                "{\"type\": \"ORDER\", \"verb\": \"UPDATE\", \"key\": \"1001order\", \"event_time\": \"2017-01-06T12:55:55.555Z\", \"customer_id\": \"10000akhil\", \"total_amount\": \"5555.34 USD\"},\n" +
                "{\"type\": \"CUSTOMER\", \"verb\": \"NEW\", \"key\": \"10000akhil\", \"event_time\": \"2017-02-06T12:46:46.384Z\", \"last_name\": \"reddy\", \"adr_city\": \"Middletown\", \"adr_state\": \"AK\"},\n" +
                "{\"type\": \"SITE_VISIT\", \"verb\": \"NEW\", \"key\": \"1002sitevisit\", \"event_time\": \"2017-01-06T12:45:52.041Z\", \"customer_id\": \"10000akhil\", \"tags\": {\"some key\": \"some value\"}},\n" +
                "{\"type\": \"IMAGE\", \"verb\": \"UPLOAD\", \"key\": \"1002image\", \"event_time\": \"2017-01-06T12:47:12.344Z\", \"customer_id\": \"10000akhil\", \"camera_make\": \"Canon\", \"camera_model\": \"EOS 80D\"},\n" +
                "{\"type\": \"ORDER\", \"verb\": \"NEW\", \"key\": \"1002order\", \"event_time\": \"2017-01-06T12:55:55.555Z\", \"customer_id\": \"10000akhil\", \"total_amount\": \"122.34 USD\"},\n" +
                "{\"type\": \"CUSTOMER\", \"verb\": \"NEW\", \"key\": \"10002akhil\", \"event_time\": \"2017-01-06T12:46:46.384Z\", \"last_name\": \"akhil\", \"adr_city\": \"Middletown\", \"adr_state\": \"PI\"},\n" +
                "{\"type\": \"SITE_VISIT\", \"verb\": \"NEW\", \"key\": \"1003sitevisit\", \"event_time\": \"2017-01-06T12:45:52.041Z\", \"customer_id\": \"10002akhil\", \"tags\": {\"some key\": \"some value\"}},\n" +
                "{\"type\": \"IMAGE\", \"verb\": \"UPLOAD\", \"key\": \"1003image\", \"event_time\": \"2017-01-06T12:47:12.344Z\", \"customer_id\": \"10002akhil\", \"camera_make\": \"Canon\", \"camera_model\": \"EOS 80D\"},\n" +
                "{\"type\": \"ORDER\", \"verb\": \"NEW\", \"key\": \"1003order\", \"event_time\": \"2017-01-06T12:55:55.555Z\", \"customer_id\": \"10002akhil\", \"total_amount\": \"102.34 USD\"},\n" +
                "{\"type\": \"CUSTOMER\", \"verb\": \"NEW\", \"key\": \"10003akhil\", \"event_time\": \"2017-01-06T12:46:46.384Z\", \"last_name\": \"shutterfly\", \"adr_city\": \"Middletown\", \"adr_state\": \"NY\"},\n" +
                "{\"type\": \"SITE_VISIT\", \"verb\": \"NEW\", \"key\": \"1004sitevisit\", \"event_time\": \"2017-01-06T12:45:52.041Z\", \"customer_id\": \"10003akhil\", \"tags\": {\"some key\": \"some value\"}},\n" +
                "{\"type\": \"IMAGE\", \"verb\": \"UPLOAD\", \"key\": \"1004image\", \"event_time\": \"2017-01-06T12:47:12.344Z\", \"customer_id\": \"10003akhil\", \"camera_make\": \"Canon\", \"camera_model\": \"EOS 80D\"},\n" +
                "{\"type\": \"ORDER\", \"verb\": \"NEW\", \"key\": \"1004order\", \"event_time\": \"2017-01-06T12:55:55.555Z\", \"customer_id\": \"10003akhil\", \"total_amount\": \"202.34 USD\"},\n" +
                "{\"type\": \"CUSTOMER\", \"verb\": \"NEW\", \"key\": \"10004akhil\", \"event_time\": \"2017-01-06T12:46:46.384Z\", \"last_name\": \"ram\", \"adr_city\": \"Middletown\", \"adr_state\": \"SC\"},\n" +
                "{\"type\": \"SITE_VISIT\", \"verb\": \"NEW\", \"key\": \"1005sitevisit\", \"event_time\": \"2017-01-06T12:45:52.041Z\", \"customer_id\": \"10004akhil\", \"tags\": {\"some key\": \"some value\"}},\n" +
                "{\"type\": \"IMAGE\", \"verb\": \"UPLOAD\", \"key\": \"1005image\", \"event_time\": \"2017-01-06T12:47:12.344Z\", \"customer_id\": \"10004akhil\", \"camera_make\": \"Canon\", \"camera_model\": \"EOS 80D\"},\n" +
                "{\"type\": \"ORDER\", \"verb\": \"NEW\", \"key\": \"1005order\", \"event_time\": \"2017-02-06T12:55:55.555Z\", \"customer_id\": \"10004akhil\", \"total_amount\": \"5.34 USD\"},\n" +
                "{\"type\": \"CUSTOMER\", \"verb\": \"NEW\", \"key\": \"10005akhil\", \"event_time\": \"2017-01-06T12:46:46.384Z\", \"last_name\": \"krishna\", \"adr_city\": \"Middletown\", \"adr_state\": \"CO\"},\n" +
                "{\"type\": \"SITE_VISIT\", \"verb\": \"NEW\", \"key\": \"1006sitevisit\", \"event_time\": \"2017-01-06T12:45:52.041Z\", \"customer_id\": \"10005akhil\", \"tags\": {\"some key\": \"some value\"}},\n" +
                "{\"type\": \"IMAGE\", \"verb\": \"UPLOAD\", \"key\": \"1006image\", \"event_time\": \"2017-01-06T12:47:12.344Z\", \"customer_id\": \"10005akhil\", \"camera_make\": \"Canon\", \"camera_model\": \"EOS 80D\"},\n" +
                "{\"type\": \"ORDER\", \"verb\": \"NEW\", \"key\": \"1006order\", \"event_time\": \"2017-01-06T12:55:55.555Z\", \"customer_id\": \"10005akhil\", \"total_amount\": \"22.34 USD\"},\n" +
                "{\"type\": \"CUSTOMER\", \"verb\": \"NEW\", \"key\": \"10006akhil\", \"event_time\": \"2017-01-06T12:46:46.384Z\", \"last_name\": \"shiva\", \"adr_city\": \"Middletown\", \"adr_state\": \"CA\"},\n" +
                "{\"type\": \"SITE_VISIT\", \"verb\": \"NEW\", \"key\": \"1007sitevisit\", \"event_time\": \"2017-01-06T12:45:52.041Z\", \"customer_id\": \"10006akhil\", \"tags\": {\"some key\": \"some value\"}},\n" +
                "{\"type\": \"IMAGE\", \"verb\": \"UPLOAD\", \"key\": \"1007image\", \"event_time\": \"2017-01-06T12:47:12.344Z\", \"customer_id\": \"10006akhil\", \"camera_make\": \"Canon\", \"camera_model\": \"EOS 80D\"},\n" +
                "{\"type\": \"ORDER\", \"verb\": \"NEW\", \"key\": \"1007order\", \"event_time\": \"2017-01-06T12:55:55.555Z\", \"customer_id\": \"10006akhil\", \"total_amount\": \"12.34 USD\"}]";
        ingestion.ingest(eventdata1);

        String eventdata2 = "[{\"type\": \"CUSTOMER\",  \"key\": \"96f55c7d8f42\", \"event_time\": \"2017-02-06T12:46:46.384Z\", \"last_name\": \"akhil\", \"adr_city\": \"Middletown\", \"adr_state\": \"AK\"},\n" +
                "{\"type\": \"SITE_VISIT\", \"verb\": \"NEW\", \"key\": \"ac05e815502f\", \"event_time\": \"2017-02-06T12:45:52.041Z\", \"customer_id\": \"96f55c7d8f42\", \"tags\": [{\"some key1\": \"some value1\"},{\"some key2\": \"some value2\"}]},\n" +
                "{\"type\": \"IMAGE\", \"verb\": \"UPLOAD\", \"key\": \"d8ede43b1d9f\", \"event_time\": \"2017-02-06T12:47:12.344Z\", \"customer_id\": \"96f55c7d8f42\", \"camera_make\": \"Canon\", \"camera_model\": \"EOS 80D\"},\n" +
                "{\"type\": \"ORDER\", \"verb\": \"NEW\", \"key\": \"68d84e5d1a43\", \"event_time\": \"2017-02-06T12:55:55.555Z\", \"customer_id\": \"96f55c7d8f42\", \"total_amount\": \"1222.34 USD\"}]";
        ingestion.ingest(eventdata2);

        String eventdata3 = "[{\"type\": \"CUSTOMER\",  \"key\": \"96f55c7d8f42\", \"event_time\": \"2017-03-06T12:46:46.384Z\", \"last_name\": \"akhil\", \"adr_city\": \"Middletown\", \"adr_state\": \"AK\"},\n" +
                "{\"type\": \"SITE_VISIT\", \"verb\": \"NEW\", \"key\": \"ac05e815502f\", \"event_time\": \"2017-03-06T12:45:52.041Z\", \"customer_id\": \"96f55c7d8f42\", \"tags\": [{\"some key1\": \"some value1\"},{\"some key2\": \"some value2\"}]},\n" +
                "{\"type\": \"IMAGE\", \"verb\": \"UPLOAD\", \"key\": \"d8ede43b1d9f\", \"event_time\": \"2017-03-06T12:47:12.344Z\", \"customer_id\": \"96f55c7d8f42\", \"camera_make\": \"Canon\", \"camera_model\": \"EOS 80D\"},\n" +
                "{\"type\": \"ORDER\", \"verb\": \"NEW\", \"key\": \"68d84e5d1a43\", \"event_time\": \"2017-03-06T12:55:55.555Z\", \"customer_id\": \"96f55c7d8f42\", \"total_amount\": \"2.34 USD\"}]";
        ingestion.ingest(eventdata3);

        Analyze.topXSimpleLTVCustomer(7,ingestion.data);

       /*
        // Converting the date string to weeknumber. We get the epoch time and from that we find the week number
        String eventtime = "2017-01-06T12:45:52.054Z";
        DateFormat dateformat = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = dateformat.parse(eventtime);
        long epochtime = date.getTime();
        long weeknumber =  (epochtime/604800000);
        System.out.print(weeknumber);
        */

    }
}
