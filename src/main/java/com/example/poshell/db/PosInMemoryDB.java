package com.example.poshell.db;

import com.example.poshell.model.Cart;
import com.example.poshell.model.Item;
import com.example.poshell.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PosInMemoryDB implements PosDB {
    private List<Product> products = new ArrayList<>();

    private Cart cart;

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Cart saveCart(Cart cart) {
        this.cart = cart;
        return this.cart;
    }

    @Override
    public Cart getCart() {
        return this.cart;
    }

    @Override
    public Product getProduct(String productId) {
        for (Product p : getProducts()) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    private PosInMemoryDB() {
        System.out.println("Constructor PosInMemoryDB");//在容器启动过程中创建。
        this.products.add(new Product("PD1", "iPhone 13", 8999));
        this.products.add(new Product("PD2", "MacBook Pro", 29499));
    }


    public String modify(int index, String productID, int amount){
        Product product = getProduct(productID);
        if(product == null){
            return "Error: no such producID";
        }
        if(this.getCart()!=null){
            if(index>=this.getCart().size()||index<0){
                return "Error: invalid index";
            }else{

                this.getCart().getItems().set(index,new Item(product, amount));
                return "Modify successfully";
            }
        }else{
            return "Error: you dont have a cart";
        }
    }

}
