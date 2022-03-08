package com.example.poshell.db;

import com.example.poshell.model.Cart;
import com.example.poshell.model.Product;

import java.util.List;

public interface PosDB {

    public List<Product> getProducts();

    public Cart saveCart(Cart cart);

    public Cart getCart();

    public Product getProduct(String productId);

    public String modify(int index, String productID, int amount);

    public boolean emptyCart();

}
