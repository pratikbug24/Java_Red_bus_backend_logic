package red_bus_clone;

class Passenger {
    String name;
    int age;
    String mobile;
    String Gender;
    int busNo;
    int[] bookedSeats;

    Passenger(String name, int age,String Gender ,String mobile, int busNo, int[] bookedSeats) {
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.Gender=Gender;
        this.busNo = busNo;
        this.bookedSeats = bookedSeats;
    }

    void displayPassenger() {
        System.out.print("Name: " + name + " | Age: " + age + " | Mobile: " + mobile +
                " | Bus No: " + busNo + " | Seats: ");
        for (int seat : bookedSeats) {
            System.out.print(seat + " ");
        }
        System.out.println();
    }
}

