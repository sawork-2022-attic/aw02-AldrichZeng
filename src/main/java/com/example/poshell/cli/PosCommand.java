package com.example.poshell.cli;

import com.example.poshell.biz.PosService;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class PosCommand {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @ShellMethod(value = "List Products", key = "p")
    public String products() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Product product : posService.products()) {
            stringBuilder.append("\t").append(++i).append("\t").append(product).append("\n");
        }
        return stringBuilder.toString();
    }

    @ShellMethod(value = "New Cart", key = "n")
    public String newCart() {
        return posService.newCart() + " OK";
    }

    @ShellMethod(value = "Add a Product to Cart", key = "a")
    public String addToCart(String productId, int amount) {
        if (posService.getCart() != null) {
            if (posService.add(productId, amount)) {
                return posService.getCart().toString();
            }else{
                return "Error: no such product";
            }
        } else {
            return "ERROR: you dont have a cart";
        }
    }


    @ShellMethod(value = "Print the Cart now", key = {"print", "l"})
    public String print() {
        if (posService.getCart() != null) {
            return posService.getCart().toString();
        } else {
            return "Error: you didn't create a cart!";
        }
    }

    @ShellMethod(value = "Empty the Cart immediately", key = {"empty", "e"})
    public String empty() {
        if (posService.getCart() != null) {
            if (posService.emptyCart()) {
                return "Empty Cart Successfully.";
            } else {
                return "Warning: Cart is empty already.";
            }

        } else {
            return "Error: you didn't create a cart!";
        }
    }

    @ShellMethod(value = "Modify the Product", key = {"modify", "m"})
    public String modify(int index, String productID, int amount) {
        if (posService.getCart() != null) {
            return posService.modify(index, productID, amount);
        } else {
            return "Error: you didn't create a cart!";
        }
    }
}
