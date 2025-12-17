import java.util.Iterator;

/**
 * My custom implementation of a Singly Linked List as per the coursework.
// This gonna  works by chaining the  "Node" objects together.
 *
 * @param <T> The type of data this list will hold (e.g., Product).
 */
public class MyLinkedList<T> implements Iterable<T> {
    

    //NESTED NODE CLASS 
    
    
      //This is a private helper class which gonna represents "link" or "Node" in our Linked list this is 
     //private because only Linked list needs to knew about it..
     
    private class Node {
        
        // Each node holds one piece of data and a link to the next node.
        
        T data;       // The data for this node
        Node next;    // The link to the next node

        public Node(T data) {
            this.data = data;
            this.next = null; // New nodes always start unconnected.
        }
        
    }

    // ATTRIBUTE
    
    // This is reference to the *very first node* in the list.
    // This list only needs one attribute
    
    private Node head;

    // CONSTRUCTOR
    
    public MyLinkedList() {
        
        this.head = null; // List Starts empty.
    }


    /**
     * This method gonna Adds a new item to the end of the list .
     */
    
    public void add(T data) {
        
        Node newNode = new Node(data); // Creates the new node

        //if the list is empty.
        
        if (head == null) {
            
            head = newNode; //Than  The new node is gonna be the head.
        } 
        // If the list is NOT empty.
        else {
            
            Node current = head; // Starts from the very beginning.
            
            // Walk through the full list untill we find the last node.
            // Because next to the last node is null  Because of that we can easily lisk to last.

            while (current.next != null) {
                
                current = current.next; // Move and move to the next nodes.
            }
            
            // We are now at the last.
            current.next = newNode; //Finally we  Link it to our new node.
            
        }
        
        
    }
    
    /**
     * 
     *  This  is our Custom Search Algorithm
     * 
     In whICH WE Search For Product by its ID.
     * 
     * This is our custom Linear Search which we we gonna use.
     
     * @param productId The ID of the product to find.
     * 
     * @return The  Product object if it found it, or null if it not found.
     * 
     * 
     */
    
    public Product findProductById(int productId) {
        
        Node current = head; // Starts from the  the beginning.
        //This gonna  Loop through the list, one node at a one time.
        //I have to cast the generic value to Product to check the ID in.
        
        while (current != null) {
            
            // We must "cast" the generic 'T data' to a 'Product' 
            // by doing this we just give a label to "T" data as product so we can easily see it
            // because java cant directly see the T data thats why we cast the data.
            
            // After can call the .getProductId() method.
            
            Product product = (Product) current.data;
            
            if (product.getProductId() == productId) {
                // We find the right product ID
                return product;
            }
            
            current = current.next; // If thats Not this one, move to the nex one and again next untill find it.
        }

        // Got to the end and didn't find ID than we return null.
        return null;
    }

    /**
     * Functionality 3: Delete a product by ID
     * In this we gonna Search for a product by its ID and removes it from the list This uses our Linear Search Logic..
     */
    
    
    public boolean removeByProductId(int productId) {
        
        // The list is empty.
        
        if (head == null) {
            return false;
        }

        // The node which we wanna delete is the head node.
        
        Product headProduct = (Product) head.data; 
        
        if (headProduct.getProductId() == productId) {
            
            head = head.next; // Change the head to the *next* node.
            return true;
        }

        // If The node is somewhere in the middle or at the end of the List.
        
        Node current = head;
        
        // We look  alwaus look one step ahead to see if the next node is the one who we need to delete.
        
        while (current.next != null) {
            
            
            Product nextProduct = (Product) current.next.data;
            
            if (nextProduct.getProductId() == productId) {
                
                // We found The node to delete is 'current.next'.
                // We gonna bypass the node to delete it 
                current.next = current.next.next;
                
                return true; // We Found it and removed it.
            }
            
            current = current.next; // If we unable to find it at the end of the list.
        }

       
        return false;
    }


    // ITERATOR
    
    //This iterator lets me use a standard "for-each" loop in the main app. 
    //Which makes the layout easy for display.
    
    
    @Override
    
    public Iterator<T> iterator() {
        
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<T> {
        
        private Node current = head; // Start at the beginning
    
        @Override
        
        public boolean hasNext() {
            //It checks is there any another node.
            return current != null;
        }

        @Override
        
        public T next() {
            // In iterator they grab the data from the current node...
            T data = (T) current.data; 
            // and move to the next node for next time.
            current = current.next;
            return data;
        }
    }
}
