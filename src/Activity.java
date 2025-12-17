import java.time.LocalDate;
/**
 * This Activity class do single activity of AddToStock or RemoveFromStock.
 */

 // ATTRIBUTES 

    // These store the data for each activity.

public class Activity{
    
    // ATTRIBUTES
    
    // These gonna store data for each of the Activity.**//
    // Once we create an activity, the details shouldn't be changed from outside.
    // Because we use private variables here to keep the data safe.**//
    
    private int activityID;
    private String activityType;
    private int quantitycount;
    private LocalDate activitydate;
    
    
        //  This is Constructor to initialize a new product
    
        public Activity(int activityId, String activityType, int quantitycount, LocalDate activitydate) {
            
        this.activityID = activityId;
        this.activityType = activityType;
        this.quantitycount = quantitycount;
        this.activitydate = activitydate;
        
    }
        
        // These getter methods let the other classes to read the private data
    // without need to modify it directly.
        
        public int getactivityID() {
    
        return activityID;
    }
        
        public String getactivityType(){
            return activityType;
                    
                    }
        
        public int getquantitycount(){
            
            return quantitycount;
        
}
        
        public LocalDate getactivitydate(){
            return activitydate;
        }
        
        // I use override to chnage existing tostring method to my one if got any error compiler shows
        //eg same like changing templete given by the teaacher.
        
       @Override
       
    public String toString() {
        
        String status;
        // Logic: simple if else logic 
        
        if (activityType.equals("AddToStock")) {
            status = " (Added)";
        } else {
            status = " (Removed)";
        }
        
        //  Thhis Return the formatted pattern which needed in for each activity eg= 67 , added,  89, 7/9/25. ,.\
        
        return "Activity ID: " + activityID + 
               ", Type: " + activityType + 
               ", Quantity: " + quantitycount + status +
               ", Date: " + activitydate;
    }
   
}
    

 
   
