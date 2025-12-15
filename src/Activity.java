import java.time.LocalDate;
/**
 * This Activity class gonna single activity of AddToStock or RemoveFromStock.
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
        
        // I override toString to make the output look nice for the user in the console.
    // It adds "(Added)"or "(Removed)" so the user can quickly see what is happening there.
        
        
       @Override
       
    public String toString() {
        
        String status;
        // Logic: If name IS "AddToStock", then status is "Added"
        if (activityType.equals("AddToStock")) {
            status = " (Added)";
        } else {
            status = " (Removed)";
        }
        
        //  Thhis  going to Return the formatted string with all details which the attributes store in it.\
        
        return "Activity ID: " + activityID + 
               ", Type: " + activityType + 
               ", Quantity: " + quantitycount + status +
               ", Date: " + activitydate;
    }
   
}
    

 
   
