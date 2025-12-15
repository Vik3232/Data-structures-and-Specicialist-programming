import java.util.Scanner;
import java.util.InputMismatchException;


// THis is our main.java in which user give their commands and the
// commands going to product handler after the product handler do work in cordination with 
// other classes with their logics and we going to get the final results.

public class Main{

    private static final ProductHandler system = new ProductHandler();
    
    private static final Scanner InputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println("Supermarket Inventory System");
        
        boolean running = true;
// here we use while loop so it repeats again and again and again untill we exit.

        while (running) {
            
            showMenu();
            try {
                
                int choice = InputScanner.nextInt(); 
                
                InputScanner.nextLine(); 

                
                if (choice == 1) {
                    handleCreateProduct();
                }
                
                else if (choice == 2) {
                    system.displayAllProducts();
                }
                
                else if (choice == 3) {
                    handleDeleteProduct();
                }
                
                else if (choice == 4) {
                    handleUpdateStock();
                }
                
                else if (choice == 5) {
                    handleDisplayActivities();
                }
                
                else if (choice == 6) {
                    running = false; 
                    System.out.println("Goodbye! See you soon");
                }
                
                else {
                    System.out.println("Invalid choice. Please enter a number between 1 to 6 only.");
                }
                // ------------------------------------------------
// this is the library which we using when user enter something else than number.

            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input. You must enter a number.");
                InputScanner.nextLine(); 
            }
        }
        InputScanner.close();
    }

    //HELPER METHODS

    private static void showMenu() {
        System.out.println("\n--- Supermarket Inventory Management System---");
        
        System.out.println("1. Add a new product");
        
        System.out.println("2. View all products");
        
        System.out.println("3. Delete a product (by ID)");
        
        System.out.println("4. Update product inventory (Added/Removed)");
        
        System.out.println("5. View a product's activities (Using Sorting)");
        
        System.out.println("6. Exit Program");
        
        System.out.print("Please enter your choice from (1-6): ");
    }
    
    // This is the first functionality user enter details and it store in it after 
    // with the values AddProduct Function runs and if Value is invalid it shows error.

    private static void handleCreateProduct() {
        
        try {
            System.out.print("Enter Product Name: ");
            
            String name = InputScanner.nextLine();
            System.out.print("Enter Initial Quantity: ");
            int quantity = InputScanner.nextInt();
            InputScanner.nextLine(); 
            system.AddProduct(name, quantity);
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Quantity must be in a number.");
            InputScanner.nextLine();
        }
    }

    // 2nd functinality directly work through ProductHandler.java
    // This is the Third functionality user enter details and it store in it after 
    // with the values handleDeleteProduct Function runs and if Value is invalid it shows error.
    
    
    private static void handleDeleteProduct() {
        try {
            System.out.print("Enter Product ID to delete: ");
            
            int id = InputScanner.nextInt();
            
            InputScanner.nextLine(); 
            system.deleteProductByID(id);
            
        } catch (InputMismatchException e) {
            
            System.out.println("ERROR: ID must be a number.");
            InputScanner.nextLine();
        }
    }
    
    
    // This is the Fourth functionality user enter details and it store in it after 
    // with the values  handleUpdateStock() Function runs and if Value is invalid it shows error.
    
   

    private static void handleUpdateStock() {
        try {
            System.out.print("Enter Product ID: ");
            
            int id = InputScanner.nextInt();
            InputScanner.nextLine(); // GOing next Line

            System.out.print("Type (1 for Addition, 2 for Removal): ");
            int typeChoice = InputScanner.nextInt();
            InputScanner.nextLine(); // user type their choice in number
            
            String activityName;
            if (typeChoice == 1) {
                activityName = "AddToStock";
                
            } else {
                activityName = "RemoveFromStock";
            }

            System.out.print("Quantity: ");
            int quantity = InputScanner.nextInt();
            
            InputScanner.nextLine(); 

            system.updateStock(id, activityName, quantity);
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Invalid input.");
            
            InputScanner.nextLine(); 
            
            
        }
    }
    
    // This is the Fifth functionality user enter details and it store in it after 
    // with the values handleDisplayActivities() Function runs and if Value is invalid it shows error.

    private static void handleDisplayActivities() {
        
        try {
            System.out.print("Enter Product ID: ");
            
            int id = InputScanner.nextInt();
            
            InputScanner.nextLine(); 
            system.displayProductActivities(id);
            
        } catch (InputMismatchException e) {
            System.out.println("ERROR: ID must be a number.");
            
            InputScanner.nextLine();
        }
    }
}