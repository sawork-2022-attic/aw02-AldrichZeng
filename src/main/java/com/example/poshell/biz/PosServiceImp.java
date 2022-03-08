package com.example.poshell.biz;

import com.example.poshell.db.PosDB;
import com.example.poshell.model.Cart;
import com.example.poshell.model.Item;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PosServiceImp implements PosService {

    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }

    @Override
    public Cart getCart() {
        return posDB.getCart();
    }

    @Override
    public Cart newCart() {
        return posDB.saveCart(new Cart());
    }

    @Override
    public void checkout(Cart cart) {

    }

    @Override
    public void total(Cart cart) {

    }

    @Override
    public boolean add(Product product, int amount) {
        return false;
    }

    @Override
    public boolean add(String productId, int amount) {

        Product product = posDB.getProduct(productId);
        if (product == null) return false;
        if (this.getCart() == null) {
            return false;
        }
        this.getCart().addItem(new Item(product, amount));
        return true;
    }

    @Override
    public List<Product> products() {
        return posDB.getProducts();
    }

//    @Override
//    public Product getProduct(String productID){
//        return posDB.getProduct(productID);
//    }

//    @Override
//    public String modify(int index, String productID, int amount){
//        // 根据index修改Cart，index是items中的下标。
//        if(index>=items.size() || index<0){ // 包含items.isEmpty()==true的情况
//            return "Error: Illegal index";
//        }
//        Item item = items.get(index);
//
//
////        Product product = posDB.getProduct(productID);
//        if(product == null){
//            return "Error: Illegal productID";
//        }
//        item.setProduct(product);
//        item.setAmount(amount);
//
//        items.set(index, item);
//        return "Success";
//    }

    public String modify(int index, String productID, int amount) {
        return posDB.modify(index, productID, amount);
    }
}
