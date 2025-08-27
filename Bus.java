package red_bus_clone;


class Bus {
    int busNo;
    String source;
    String destination;
    int totalSeats;
    int bookedSeats;
    boolean[] seats; // To track which seats are booked

    Bus(int busNo, String source, String destination, int totalSeats) {
        this.busNo = busNo;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
        this.seats = new boolean[totalSeats]; // false = available, true = booked
    }

    boolean isAvailable(int seatsToBook) {
        return (totalSeats - bookedSeats) >= seatsToBook;
    }

    void bookSeat(int seatNo) {
        if (!seats[seatNo - 1]) {
            seats[seatNo - 1] = true;
            bookedSeats++;
        }
    }

    void cancelSeat(int seatNo) {
        if (seats[seatNo - 1]) {
            seats[seatNo - 1] = false;
            bookedSeats--;
        }
    }

    void displayBusDetails() {
        System.out.println("Bus No: " + busNo + " | From: " + source + " | To: " + destination +
                " | Total Seats: " + totalSeats + " | Booked: " + bookedSeats + " | Available: " + (totalSeats - bookedSeats));
    }

    // ----------------- DISPLAY SEAT LAYOUT -----------------
    void displaySeatLayout() {
        System.out.println("\n🚌 Seat Layout for Bus " + busNo);
        int cols = 4; // Assuming 4 seats per row like RedBus
        for (int i = 0; i < totalSeats; i++) {
            if (seats[i]) {
                System.out.print("[BOOKED]\t");
            } 
            
            else {
                System.out.print("[" + (i + 1) + "]");
            }
            
            if(i%2!=0)
            {
            	 System.out.print("    ");
            }
            // New line after every row of 4 seats
            if ((i + 1) % cols == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}