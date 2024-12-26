package Model;

import java.util.ArrayList;
import java.util.List;

public class PurchaseHistory {

	private List<Item> history;
	
    // constructor
    public PurchaseHistory() {
        history = new ArrayList<Item>();
    }
	
	public void appendCart(Cart cart) {
		history.addAll(cart.getItems());
	}
	
   // Return all the Items in the cart
   public List<Item> getItems() {
      return history;
   }
	
   public void clear() {
	    history.clear();
    }
}
