package com.example.poshell.model;

import com.example.poshell.db.PosDB;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.remote.rmi._RMIConnection_Stub;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Cart {

    private List<Item> items = new ArrayList<>();


    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }

    public boolean addItem(Item item) {
        return items.add(item);
    }

//    public String modify(int index, String productID, int amount){
//        // 根据index修改Cart，index是items中的下标。
//        if(index>=items.size() || index<0){ // 包含items.isEmpty()==true的情况
//            return "Error: Illegal index";
//        }
//        Item item = items.get(index);
//        Product product = posDB.getProduct(productID);
//        if(product == null){
//            return "Error: Illegal productID";
//        }
//        item.setProduct(product);
//        item.setAmount(amount);
//
//        items.set(index, item);
//        return "Success";
//    }

    @Override
    public String toString() {
        if (items.size() ==0){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getAmount() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }
}
