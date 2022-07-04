package sample.market;
public enum Product {
    Tomato("tom.jpg",0.55f),Banana("ban.jpg",0.78f),Cheese("ch.jpg",0.56f),Pizza("pizza.jpg",0.78f);

    private String imageFile;
    private float price;

    Product(String imageFile,float price){
        this.imageFile = imageFile;
        this.price = price;
    }

    public String getImageFile() {
        return imageFile;
    }

    public float getPrice() {
        return price;
    }
}
