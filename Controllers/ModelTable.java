/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.market;

/**
 *
 * @author Salma
 */
public class ModelTable {
        String item, quantity, price;

    public ModelTable(String item, String quantity, String price) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
