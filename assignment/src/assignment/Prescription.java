package assignment;

import java.util.ArrayList;
import java.util.List;

public class Prescription {
    int prescriptionId;
    Customer customer;
    String doctorName;
    List<PrescriptionItem> items = new ArrayList<>();

    public Prescription(int prescriptionId, Customer customer, String doctorName) {
        this.prescriptionId = prescriptionId;
        this.customer = customer;
        this.doctorName = doctorName;
    }

    public void addItem(Medicine med, int qty) {
        items.add(new PrescriptionItem(med, qty));
    }
}
