import java.time.LocalDate;
import java.util.LinkedList; // This is a Built-in Java Collections Framework which is asked to use in Coursework.
import java.util.List;
import java.util.Arrays; // This library is Needed to convert the sorted array back to a List.

// This class represents a product in the store.
// This class not only store details of product but also stores product last 4 activities as mentioned in coursework.

public class Product {

    // ATTRIBUTES
    // These gonna store data for each of the Activity.**//
    
    private int productID;
    private String productCategory;
    private LocalDate entryDate;  
    private int Productquantity;         

    // This is the Java Collections Framework data structure.
    // I used a LinkedList here because I need to frequently add new items
    // and remove old ones. However,we add new items always from  the end of the list.
    
    private LinkedList<Activity> activities;

    
    // Constructor to make new  product
    
    public Product(int Id, String Name, int initialQuantity) {
        this.productID = Id;
        this.productCategory = Name;
        this.entryDate = LocalDate.now(); 
        
        
        // I initialize stock to 0 because the first activity will handle the math.
        // If i set it to initialQuantity the final value which we get is double i found this bug during testing of my code.
     
        
        
        this.Productquantity = 0; 

        // Creating the  new empty list to store activities records.
        
        this.activities = new LinkedList<>(); 
    }

    // Again using getter methods in product class as well.
    
    public int getProductId() {
        return productID;
    }

    public String getProductName() {
        return productCategory;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public int getQuantity() {
        return Productquantity;
    }

    
     // This method adds a new activity and updates the product's quantity in the program.
     // It also enforces the "last four activities" rulewhich is mentioned in the CourseWork.
   
     
    public void addActivity(Activity NewActivities) {
        
        //  Adds  the new activity to the END of the list.
        
        this.activities.addLast(NewActivities);

        // This gonna  Update the product's quantity based on the activity either its + or - .
        
        if (NewActivities.getactivityType().equals("AddToStock")) {
            this.Productquantity += NewActivities.getquantitycount(); 
            
//Here if its add to stock than this gonna do addition
  //For eg The getquantity contains the previous value when user add new value it adds in getquantitycount or..             
            
            
        } else if (NewActivities.getactivityType().equals("RemoveFromStock")) {
            
            this.Productquantity -= NewActivities.getquantitycount();//For eg The getquantity contains the previous value when user add new value it Subtracts in getquantitycount.             
            
        }
        
        // ENFORCE THE "LAST 4" RULE 
        while (this.activities.size() > 4) {
            
            //If list is > 4, remove the OLDEST activity from the FRONT.
            // In this whenever we add new activity add from last but subtract from starting.
            
            this.activities.removeFirst(); 
        }
    }

    /**
     * Method to check we have enough stuff or not.
     * Just because we dont want value in minus in supermarket which indicates Out of Stock
     */
    
    public boolean CheckStock(int quantityToRemove) {
        
        return this.Productquantity >= quantityToRemove;
        
    }
    
     
   //CUSTOM SORTING ALGORITHM
    
    //Functionality 5 get sorted Activities
    // This gonna sort the activities based on their quantity for eg if ist input is 50 added and 2nd is 40 than in list 
    // after this function it looks like 40, 50.

    // This takes the values from activity class and product private LinkedList<Activity> activities; this line as well as when
    // managers call out this activity get filled when manager calls product.addActivity(initialActivity)
    //and in product it runs public void addActivity(Activity NewActivities
    
    public List<Activity> getSortedRecentActivities() {
        
        // 1. Copy LinkedList to an Array (easier to sort)
        Activity[] activityAray = this.activities.toArray(new Activity[0]);

        // 2. Our Custom Insertion Sort Algorithm
        
        // This Starts from the second element (i=1)
        
        for (int i = 1; i < activityAray.length; i++) {
            
            // 'key' is the item we're trying to place(Here we make activityArray = key)
            Activity key = activityAray[i];
            
            // 'j' is the at the starting of the index.
            
            int j = i - 1;

           
            // in this we Move items that are BIGGER than our 'key' one spot to the right of them
            
            while (j >= 0 && activityAray[j].getquantitycount() > key.getquantitycount()) {
                
                activityAray[j + 1] = activityAray[j]; // Shift right
                //In this when we did J+1 = activity array at J for eg valuee gonna looks like 50, 50, 70,90. at index 1 we have 
                // value 30 which is know going to left in next 2 steps.
                
                j = j - 1; // Move left
            }
            // By doing J-1 we get a spot to put 30 in the space
            
            // Know we Insert the 'key'.
            
            activityAray[j + 1] = key;
        }

        // Return the sorted array (wrapped as a List)
        
        return Arrays.asList(activityAray);
    }

    //  Thhis  going to Return the formatted string with all details which the attributes store in it.\
    // Gonna work in Functionality 2.
    
    @Override
    
    public String toString() {
        return "Product ID: " + productID + 
               ", Name: " + productCategory + 
               ", Quantity in Stock: " + Productquantity +
               ", Entry Date: " + entryDate;
    }
}