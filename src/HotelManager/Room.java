package HotelManager;

public class Room {
    protected String category;
    protected int price;

    public Room(String category, int price){
        this.category = category;
        this.price = price;
    }


    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
