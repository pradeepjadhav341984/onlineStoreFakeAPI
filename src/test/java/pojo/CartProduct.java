package pojo;

import java.util.List;

public class CartProduct {
	
	private int productId;
    private int quantity;
    
    
    //constructor
    
    public CartProduct(int productId, int quantity)
    {
    	this.productId=productId;
    	this.quantity=quantity;
    }
    
 public CartProduct(int userId, String date, List<CartProduct> products) {
		// TODO Auto-generated constructor stub
	 
	}

	// Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
