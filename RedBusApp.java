package red_bus_clone;

import java.util.Scanner;
public class RedBusApp {
    static Scanner sc = new Scanner(System.in);
    static Bus[] buses = new Bus[5];
    static Passenger[] passengers = new Passenger[5];
    static int busCount = 0;
    static int passengerCount = 0;

    // -------------------- ADMIN METHODS ----------------------
    static void addBus() {
        if (busCount >= buses.length) {
            resizeBusArray();
        }

        System.out.print("Enter Bus Number: ");
        int busNo = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Source: ");
        String source = sc.nextLine();
        System.out.print("Enter Destination: ");
        String destination = sc.nextLine();
        System.out.print("Enter Total Seats: ");
        int seats = sc.nextInt();

        buses[busCount] = new Bus(busNo, source, destination, seats);
        busCount++;

        System.out.println("✅ Bus added successfully!");
    }

    // -------------------- ADD DEFAULT 10 BUSES ----------------------
    static void loadDefaultBuses() {
        String[][] defaultBuses = {
            {"101", "Mumbai", "Pune", "40"},
            {"102", "Pune", "Nagpur", "45"},
            {"103", "Nagpur", "Nashik", "50"},
            {"104", "Nashik", "Aurangabad", "35"},
            {"105", "Aurangabad", "Kolhapur", "40"},
            {"106", "Kolhapur", "Satara", "30"},
            {"107", "Satara", "Solapur", "48"},
            {"108", "Solapur", "Thane", "42"},
            {"109", "Thane", "Ratnagiri", "38"},
            {"110", "Ratnagiri", "Mumbai", "55"}
        };

        for (int i = 0; i < defaultBuses.length; i++) {
            if (busCount >= buses.length) {
                resizeBusArray();
            }
            int busNo = Integer.parseInt(defaultBuses[i][0]);
            String source = defaultBuses[i][1];
            String destination = defaultBuses[i][2];
            int seats = Integer.parseInt(defaultBuses[i][3]);

            buses[busCount] = new Bus(busNo, source, destination, seats);
            busCount++;
        }
        System.out.println("🚍 10 Maharashtra buses loaded successfully!");
    }

    // -------------------- ARRAY RESIZE METHODS ----------------------
    static void resizeBusArray() {
        Bus[] newBuses = new Bus[buses.length * 2];
        for (int i = 0; i < buses.length; i++) {
            newBuses[i] = buses[i];
        }
        buses = newBuses;
        System.out.println("⚡ Bus storage expanded! New capacity: " + buses.length);
    }

    static void resizePassengerArray() {
        Passenger[] newPassengers = new Passenger[passengers.length * 2];
        for (int i = 0; i < passengers.length; i++) {
            newPassengers[i] = passengers[i];
        }
        passengers = newPassengers;
        System.out.println("⚡ Passenger storage expanded! New capacity: " + passengers.length);
    }

    // -------------------- VIEW ALL BUSES ----------------------
    static void viewAllBuses() {
        if (busCount == 0) {
            System.out.println("No buses available.");
            return;
        }
        System.out.println("\n---- Available Buses ----");
        for (int i = 0; i < busCount; i++) {
            buses[i].displayBusDetails();
        }
    }

    // -------------------- BOOK TICKET WITH SEAT UI ----------------------
    static void bookTicket() {
        System.out.print("Enter Bus Number to book ticket: ");
        int busNo = sc.nextInt();
        int index = findBus(busNo);
        if (index == -1) {
            System.out.println("Bus not found!");
            return;
        }

        Bus selectedBus = buses[index];
        selectedBus.displaySeatLayout();

        System.out.print("Enter Number of Seats to Book: ");
        int seatsToBook = sc.nextInt();
        sc.nextLine();

        if (!selectedBus.isAvailable(seatsToBook)) {
            System.out.println("❌ Not enough seats available!");
            return;
        }

        int[] bookedSeats = new int[seatsToBook];
        for (int i = 0; i < seatsToBook; i++) {
            System.out.print("Enter Seat Number to Book: ");
            int seatNo = sc.nextInt();
            if (seatNo < 1 || seatNo > selectedBus.totalSeats) {
                System.out.println("❌ Invalid Seat Number!");
                i--;
                continue;
            }
            if (selectedBus.seats[seatNo - 1]) {
                System.out.println("❌ Seat already booked! Choose another.");
                i--;
                continue;
            }
            selectedBus.bookSeat(seatNo);
            bookedSeats[i] = seatNo;
        }
        sc.nextLine();

        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Passenger Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Passenger Mobile: ");
        String mobile = sc.nextLine();
        System.out.print("Enter Passenger  Gender");
        String Gender=sc.nextLine();

        if (passengerCount >= passengers.length) {
            resizePassengerArray();
        }

        passengers[passengerCount] = new Passenger(name, age,Gender,mobile, busNo, bookedSeats);
        passengerCount++;

        System.out.println("✅ Booking Successful for " + name);
        selectedBus.displaySeatLayout();
    }

    // -------------------- CANCEL TICKET ----------------------
    static void cancelTicket() {
        System.out.print("Enter Bus Number to cancel ticket: ");
        int busNo = sc.nextInt();
        int index = findBus(busNo);
        if (index == -1) {
            System.out.println("Bus not found!");
            return;
        }

        System.out.print("Enter Seat Number to Cancel: ");
        int seatNo = sc.nextInt();
        Bus selectedBus = buses[index];

        if (seatNo < 1 || seatNo > selectedBus.totalSeats || !selectedBus.seats[seatNo - 1]) {
            System.out.println("❌ Invalid or unbooked seat!");
            return;
        }

        selectedBus.cancelSeat(seatNo);
        System.out.println("✅ Seat " + seatNo + " canceled successfully!");
        selectedBus.displaySeatLayout();
    }

    // -------------------- SEARCH BUS ----------------------
    static void searchBus() {
        System.out.print("Enter Source: ");
        sc.nextLine();
        String source = sc.nextLine();
        System.out.print("Enter Destination: ");
        String destination = sc.nextLine();

        boolean found = false;
        System.out.println("\n---- Search Results ----");
        for (int i = 0; i < busCount; i++) {
            if (buses[i].source.equalsIgnoreCase(source) && buses[i].destination.equalsIgnoreCase(destination)) {
                buses[i].displayBusDetails();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No buses found for this route.");
        }
    }

    // -------------------- VIEW ALL PASSENGERS ----------------------
    static void viewAllPassengers() {
        if (passengerCount == 0) {
            System.out.println("No bookings available.");
            return;
        }
        System.out.println("\n---- Passenger Details ----");
        for (int i = 0; i < passengerCount; i++) {
            passengers[i].displayPassenger();
        }
    }

    // -------------------- FIND BUS ----------------------
    static int findBus(int busNo) {
        for (int i = 0; i < busCount; i++) {
            if (buses[i].busNo == busNo) {
                return i;
            }
        }
        return -1;
    }

    // -------------------- MAIN MENU ----------------------
    public static void main(String[] args) {
        loadDefaultBuses();

        int choice;
        do {
            System.out.println("\n===== WELCOME TO REDBUS =====");
            System.out.println("1. Add Bus (Admin)");
            System.out.println("2. View All Buses");
            System.out.println("3. Search Bus by Route");
            System.out.println("4. Book Ticket");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. View All Passengers");
            System.out.println("7. Exit");
            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addBus();
                    break;
                case 2:
                    viewAllBuses();
                    break;
                case 3:
                    searchBus();
                    break;
                case 4:
                    bookTicket();
                    break;
                case 5:
                    cancelTicket();
                    break;
                case 6:
                    viewAllPassengers();
                    break;
                case 7:
                    System.out.println("Thank you for using RedBus App!");
                    break;
                default:
                    System.out.println("❌ Invalid Choice! Please try again.");
            }
        } while (choice != 7);
    }
}