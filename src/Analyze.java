import java.awt.*;
import java.util.*;

/**
 * Created by akhil on 2/25/17.
 *topXSimpleLTVCustomer method will print the results in the same mehtod.
 */
public class Analyze {

    public static void topXSimpleLTVCustomer(int x,Data data){
        //System.out.println("in analyze method");
        //dataStructure for storing output
        ArrayList<OutPut> outputlist = new ArrayList<OutPut>();
        HashMap<String, HashMap<Long, ArrayList<Order>>> hashmap = data.getOrderdata();;

        //getting all customers output and storing them in the arraylist
        for(String customers: hashmap.keySet()){
            //System.out.println("customers : "+customers);
            HashMap<Long, ArrayList<Order>> hashmapweeknumber = hashmap.get(customers);
            double sum =0;
            double a = 0;
            int weekNumbersSize = hashmapweeknumber.size();
            double sumCustomerExpenditurePerVisit = 0;
            for(long weeknumbers: hashmapweeknumber.keySet()){
                //System.out.println("weeknumbers : "+weeknumbers);
                ArrayList<Order> orders = hashmapweeknumber.get(weeknumbers);
                int size = orders.size();
                int i=0;
                double customerExpendituresPerVisit = 0;
                int totalNumberOfVisits= size;
                while(i<size) {
                    sum = sum + orders.get(i).getTotal_amount();
                    i++;
                }
                customerExpendituresPerVisit = sum/size;
                sumCustomerExpenditurePerVisit = sumCustomerExpenditurePerVisit + customerExpendituresPerVisit;
                //System.out.println(sum/size);
            }
            //a contains the average customer value per week
            a = sumCustomerExpenditurePerVisit/weekNumbersSize;
            //simpleLTF contains the life time customer value
            double simpleLTF = (52*a)*10;
            OutPut output = new OutPut();
            output.setCustomer_id(customers);
            output.setCustomer_ltf(simpleLTF);
            outputlist.add(output);
        }

        //Sorts all the custoemrs in the arraylist.
       Collections.sort(outputlist, new OutputSorter());
        //getting the top x customers from all the customers
        if(outputlist.size()<x){
            System.out.println("number of of customers in the data are less than x value, please provide smaller x value");
        }else{
            System.out.println("");
            System.out.println("Top "+x+" customers with highest Simple LTV are : ");

            for(int i=0; i<x;i++){
                System.out.println(outputlist.get(i).getCustomer_id()+" : "+outputlist.get(i).getCustomer_ltf());
            }
        }

    }





}
// Used for sorting the Outputlist
class OutputSorter implements Comparator<OutPut>{

    @Override
    public int compare(OutPut o1, OutPut o2) {

        return (int) (o2.getCustomer_ltf() - o1.getCustomer_ltf());
    }
}

