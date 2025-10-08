package assignment;

public class SaleDetail {
    Medicine medicine;
    int qty;
    double subtotal;

    public SaleDetail(Medicine medicine, int qty) {
        this.medicine = medicine;
        this.qty = qty;
        this.subtotal = qty * medicine.price;
    }
}
