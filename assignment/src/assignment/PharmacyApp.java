package assignment;

import java.util.*;

public class PharmacyApp {
    static Scanner sc = new Scanner(System.in);
    static List<Medicine> medicines = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static List<Prescription> prescriptions = new ArrayList<>();

    public static void main(String[] args) {
        Pharmacist pharmacist = new Pharmacist(1, "John Doe", "LIC123");

        while (true) {
            System.out.println("\n=== Pharmacy Management ===");
            System.out.println("1. Add Medicine");
            System.out.println("2. Add Customer");
            System.out.println("3. Add Prescription");
            System.out.println("4. Make Sale");
            System.out.println("5. Display Medicines");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addMedicine();
                case 2 -> addCustomer();
                case 3 -> addPrescription();
                case 4 -> makeSale(pharmacist);
                case 5 -> displayMedicines();
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addMedicine() {
        System.out.print("Enter Medicine ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Stock: ");
        int stock = sc.nextInt();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 6); // expiry after 6 months
        medicines.add(new Medicine(id, name, stock, cal.getTime(), price));
        System.out.println("✅ Medicine added.");
    }

    static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Contact: ");
        String contact = sc.nextLine();
        customers.add(new Customer(id, name, contact));
        System.out.println("✅ Customer added.");
    }

    static void addPrescription() {
        System.out.print("Enter Prescription ID: ");
        int pid = sc.nextInt();
        System.out.print("Enter Customer ID: ");
        int cid = sc.nextInt();
        sc.nextLine();
        Customer cust = customers.stream().filter(c -> c.id == cid).findFirst().orElse(null);
        if (cust == null) {
            System.out.println("❌ Customer not found.");
            return;
        }
        System.out.print("Enter Doctor Name: ");
        String doctor = sc.nextLine();
        Prescription p = new Prescription(pid, cust, doctor);
        prescriptions.add(p);
        System.out.println("✅ Prescription added.");
    }

    static void makeSale(Pharmacist pharmacist) {
        System.out.print("Enter Sale ID: ");
        int sid = sc.nextInt();
        System.out.print("Enter Customer ID: ");
        int cid = sc.nextInt();
        Customer cust = customers.stream().filter(c -> c.id == cid).findFirst().orElse(null);
        if (cust == null) {
            System.out.println("❌ Customer not found.");
            return;
        }
        Sale sale = new Sale(sid, cust, pharmacist);
        while (true) {
            System.out.print("Enter Medicine ID to sell (0 to stop): ");
            int mid = sc.nextInt();
            if (mid == 0) break;
            Medicine med = medicines.stream().filter(m -> m.id == mid).findFirst().orElse(null);
            if (med == null) {
                System.out.println("❌ Medicine not found.");
                continue;
            }
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();
            sale.addSaleDetail(med, qty);
        }
        sale.printBill();
    }

    static void displayMedicines() {
        System.out.println("\n--- Medicines ---");
        for (Medicine m : medicines) {
            System.out.println(m.id + " | " + m.name + " | Stock: " + m.stock + " | Exp: " + m.expiryDate);
        }
    }
}

