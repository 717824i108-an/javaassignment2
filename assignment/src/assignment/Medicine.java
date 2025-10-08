package assignment;

import java.util.Date;

public class Medicine {
    int id;
    String name;
    int stock;
    Date expiryDate;
    double price;

    public Medicine(int id, String name, int stock, Date expiryDate, double price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.expiryDate = expiryDate;
        this.price = price;
    }

    public boolean isExpired() {
        return new Date().after(expiryDate);
    }
}
