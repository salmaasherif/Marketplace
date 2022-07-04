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
public class ItemViewer {
    String name,price,qty,pic;

    public ItemViewer(){}
    public ItemViewer(String name, String price, String qty, String pic) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.pic = pic;
    }
        public ItemViewer(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQty() {
        return qty;
    }

    public String getPic() {
        return pic;
    }
    
    
    
}
