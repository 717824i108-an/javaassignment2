package assignment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sale {
    int saleId;
    Customer customer;
    Pharmacist pharmacist;
    List<SaleDetail> details = new ArrayList<>();
    Date date;

    public Sale(int saleId, Customer customer, Pharmacist pharmacist) {
        this.saleId = saleId;
        this.customer = customer;
        this.pharmacist = pharmacist;
        this.date = new Date();
    }

    public void addSaleDetail(Medicine med, int qty) {
        if (med.stock >= qty && !med.isExpired()) {
            med.stock -= qty;  // update stock
            details.add(new SaleDetail(med, qty));
        } else {
            System.out.println("❌ Medicine unavailable or expired: " + med.name);
        }
    }

    public void printBill() {
        System.out.println("\n=== SALE BILL ===");
        System.out.println("Customer: " + customer.name);
        System.out.println("Pharmacist: " + pharmacist.name);
        double total = 0;
        for (SaleDetail sd : details) {
            System.out.println(sd.medicine.name + " x" + sd.qty + " = " + sd.subtotal);
            total += sd.subtotal;
        }
        System.out.println("TOTAL: " + total);
    }
}
