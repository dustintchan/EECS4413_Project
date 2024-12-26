package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {
 
   private List<Item> cart;  // List of Items

   // constructor
   public Cart() {
      cart = new ArrayList<Item>();
   }
 
   // Add a Item into this Cart, with qtyOrdered. If the Item id already exists, update the qty ordered
   // if not, create a new Item.
   public void add(Item newItem) {
	   
	   // Check if item already exists in the cart
	      for (Item item : cart) {
	          if (item.getId() == newItem.getId()) {
	             // If Item exists, update the quantity
	        	 int currQuantity = item.getQuantityInCart();
	        	 item.setQuantityInCart(currQuantity+1);
	        	  
	             return;
	          }
	       }
	      
      // If Item doesn't exist, create a new one and add it to the cart
      cart.add(newItem);
   }
 
   // Update the quantity for the given id
   public void update(int id, int newQty) {
	   for (Item item : cart) {
	         if (item.getId() == id) {
	            // Update the quantity of the book
	            item.setQuantity(newQty);
	            return;
	         }
	      }
   }
 
   // Remove a Item given its id
   public void remove(int id) {
      Iterator<Item> iter = cart.iterator();
      while (iter.hasNext()) {
    	  Item item = iter.next();
          if (item.getId() == id) {
             // Remove the item from the cart
             iter.remove();
             return;
          }
      }
   }
   
   // Get the subtotal of the cart's items
   public double getSubtotal() {
	   double subtotal = 0;
	   
	   for (Item item : cart) {
		   subtotal = subtotal + item.getPrice()*item.getQuantityInCart();
	   } 
	   // Round the result to 2 decimal places
	   return Math.round(subtotal * 100.0) / 100.0;
   }
   
   public double getTaxAmount() {
	   /* Note: tax rate is 13% */
	   
	   double subtotal = getSubtotal();
	   double taxAmount = subtotal * 0.13;
	   
	   // Round the result to 2 decimal places
	   return Math.round(taxAmount * 100.0) / 100.0;
   }
   
   // Get subtotal + shipping + tax
   public double getTotal() {
	   /* Note: shipping fee is a flat $5 in this scenario */
	   
	   double total = getSubtotal() + 5.00 + getTaxAmount();
	   
	   // Round the result to 2 decimal places
	   return Math.round(total * 100.0) / 100.0;
   }
   
   // Change the quantity of item in cart
   public void changeQuantity(int itemID, int newQuantity) {
      for (Item item : cart) {
          if (item.getId() == itemID) {
        	  item.setQuantityInCart(newQuantity);
          }
       }
   }
 
   // Get the number of Items in this Cart
   public int size() {
      return cart.size();
   }
 
   // Check if this Cart is empty
   public boolean isEmpty() {
      return size() == 0;
   }
 
   // Return all the Items in the cart
   public List<Item> getItems() {
      return cart;
   }
 
   // Remove all the items in this Cart
   public void clear() {
      cart.clear();
   }
}
