
import java.time.LocalDate;
import java.util.List; 

/**
 * This class is the main engine of the application.
 * Which gonna handle all the Functionalities
 * It manages the list of products  by using MyLinkedList 
 * and handles other all the main  functionalities (F1-F5).

 */
public class ProductHandler {
    

    // ATTRIBUTES
    
    // This is our custom-built data structure,Which is  holding all the Product objects in it.
    
    private MyLinkedList<Product> productList;
    
    
    // These are used to auto-generate unique IDs for each activity and product.
    
    private int getProductID;
    private int getActivityID;

    //CONSTRUCTOR
    
    
    public ProductHandler() {
        
        this.productList = new MyLinkedList<>();
        this.getProductID = 1;     // Product IDs will be starts from 1
        this.getActivityID = 100; // Activity IDs will be  starts from 100
        
    }

    // FUNCTIONALITY METHODS

    /**
     * Functionality 1 = Create a new product 
     */
    public void AddProduct(String name, int initialQuantity) {
        
       // IF Initial qty is less than 0 it shows error
       
        if (initialQuantity < 0) {
            
            System.out.println("ERROR: Cannot create product with negative quantity.");
            return; 
        }
        // here Product class make new variable in which we get the data it stored in product attributes.

        Product newProduct = new Product(this.getProductID, name, initialQuantity);
        
        // Same thing going on with the activity class.
        
        Activity initialActivity = new Activity(
                
            this.getActivityID, 
            "AddToStock", 
            initialQuantity, 
            LocalDate.now()
        );
        
        // When we give the data next line storing them after they store one activity or product
        // they gonna update both ids as well.
        // as well as Imp to note when we make new product always its store first their initial activity in them.
        
        
        newProduct.addActivity(initialActivity);
        
        //after when we sucessfully make product it gonna store in list.

        this.productList.add(newProduct); 

        // Increment the IDs
        
        this.getProductID++;
        this.getActivityID++;
        
        System.out.println("SUCCESS: Product '" + name + "' created with ID " + (getProductID-1));
    }

    /**
     * Functionality 2: Display all products ---
     */
    
    public void displayAllProducts() {
        
        System.out.println("--- Displaying All Products ---");
        
        // Our 'MyLinkedList' is "Iterable", so we can use a for-each loop without any problem.
        
        for (Product p : this.productList) {
            
            System.out.println(p); // This Calls the Product.toString() method which is at the end of the code of product class.
        }
        System.out.println("---------------------------------");
    }

    /**
     *  Functionality 3: Delete a product 
     */
    
    public boolean deleteProductByID(int productId) {
        
        
        // This gonna calls our custom MyLinkedList.removeByProductId()which we make with the sole purpose of delete product by ID
        // which Also contains the  required linear search in them
        
        boolean wasRemoved = this.productList.removeByProductId(productId);
        
        
        //If the system find the id and remove the product with id by linear search it shows success either not found.
        
        if (wasRemoved) {
            System.out.println("SUCCESS: Product " + productId + " was deleted.");
            
        } else {
            System.out.println("ERROR: Product " + productId + " not found.");
        }
        
        return wasRemoved;
    }

    /**
     * Functionality 4: Update system with new activity.
     */
    
    // This Update stock method is little complex but intresting as well we use product classes in this. 
    
    // Find product by ID etc.
    
    public void updateStock(int productId, String activityName, int quantity) {
        
         //Find the product using our custom search
         
        Product product = this.productList.findProductById(productId);

        if (product == null) {
            
            System.out.println("ERROR: Product " + productId + " not found.");
            return;
        }

        //  Check the quantity means Quantity not be in minus or negative.
        
        if (quantity < 0) {
            System.out.println("ERROR: Activity quantity cannot be negative.");
            return;
        }

        //Check  "RemoveFromStock" if there is already some stock 
        
        if (activityName.equals("RemoveFromStock")) {
            
            // This line is saying if product is not enough to remove give the error.
            if (!product.CheckStock(quantity)) {
                
                
                System.out.println("ERROR: Not enough stock. " +
                                   "Available: " + product.getQuantity() + 
                                   ", Tried to remove: " + quantity);
                return;
            }
        }

        // Step 4: All checks passed. Create and add the activity. after every time we create a product we add new activity
        Activity newActivity = new Activity(
                
            this.getActivityID, 
            activityName, 
            quantity, 
            LocalDate.now()
                
        );

        product.addActivity(newActivity);// This is imp line which gonna store new Add activity in add Activity and update Activity ID.
        this.getActivityID++;
        
        System.out.println("SUCCESS: Activity " + activityName + " for Product " + productId + " processed.");
        
        System.out.println("New stock quantity: " + product.getQuantity());
    }

    /**
     *  Functionality 5: Display a product's sorted activities by using srting which we used in Product.java
     */
    public void displayProductActivities(int productId) {
        
        Product product = this.productList.findProductById(productId);
        
        // Agin in this we gonna find product id by using find product by ID method.

        if (product == null) {
            System.out.println("ERROR: Product " + productId + " not found.");
            return;
        }

        // a. Going to Get the sorted list of activities
        // b. This  going to calls the getSortedRecentActivities() method in the Product class
        // c. which contains our custom Insertion Sort.
        
        List<Activity> activities = product.getSortedRecentActivities();
        
        System.out.println("--- Activities for Product: " + product.getProductName() + " (Sorted by Quantity) ---");
        
        if (activities.isEmpty()) {
            
            System.out.println("No activities found for this product.");
        } else {
            
            for (Activity act : activities) {
                
                System.out.println(act); // This gonna Calls the Activity.toString() method to print 
            }
        }
        System.out.println("--------------------------------------------------");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // ... existing code ...

    // NEW: Allow the Helper class to see the inventory
    public MyLinkedList<Product> getInventory() {
        return this.productList;
    }
    
} // End of class
    
    
    
    
    
    
    
    
    
    
    