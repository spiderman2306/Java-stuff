import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FestivalManager manager = new FestivalManager();
        Scanner sc = new Scanner(System.in);

        // adding one event for the test run
        manager.addEvent(new Event("SP2099", "01 January 2025", "Mahikeng", "Main Stage"));
        manager.addEvent(new Event("MY2004","25 December 2025", "Bangladesh", "Backstage"));

        while (true) {
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Booking");
            System.out.println("3. Check Booking");
            System.out.println("4. Display Attendees");
            System.out.println("5. Exit");
            int choice = sc.nextInt();


            if (choice == 1) {
                // Booking a ticket
                System.out.print("Event ID: ");
                String eventId = sc.nextLine();
                Event e = manager.findEvent(eventId);
                if (e == null) {
                    System.out.println("The Event isn't found. Please try again.");
                    continue;
                }
                System.out.print("ID: ");
                String id = sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Surname: ");
                String surname = sc.nextLine();
                System.out.print("Contact: ");
                String contact = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Seat: ");
                String seat = sc.nextLine();

                Attendee a = new Attendee(id, name, surname, contact, email, seat);
                if (manager.bookTicket(eventId, a)) {
                    System.out.println("Booking successful! Prize: " + a.prize); // giving them a new prize randomly
                } else {
                    System.out.println("Your Booking has failed (event not found or seat already booked).");
                }

            } else if (choice == 2) {
                // Cancel Booking
                System.out.print("Event ID: ");
                String eventId = sc.nextLine();
                System.out.print("Seat Number: ");
                String seat = sc.nextLine();
                if (manager.cancelBooking(eventId, seat)) {
                    System.out.println("Booking cancelled.");
                } else {
                    System.out.println("Booking not found. Would you like to book instead? (y/n)");
                    if (sc.nextLine().equalsIgnoreCase("y")) {

                    }
                }

            } else if (choice == 3) {
                // Checking for booking
                System.out.print("Event ID: ");
                String eventId = sc.nextLine();
                System.out.print("ID (you can leave this blank if you dont know it): ");
                String id = sc.nextLine();
                System.out.print("Name (you can leave this blank if you dont know it): ");
                String name = sc.nextLine();
                System.out.print("Contact (you can leave this blank if you dont know it): ");
                String contact = sc.nextLine();
                System.out.print("Email (you can leave this blank if you dont know it): ");
                String email = sc.nextLine();

                Attendee found = manager.checkBooking(eventId, id, name, contact, email);
                if (found != null) {
                    System.out.println("Booking found: " + found);
                } else {
                    System.out.println("Booking not found. Would you like to book instead? (y/n)");
                    if (sc.nextLine().equalsIgnoreCase("y")) {
                        // Optionally, you could call the booking logic here
                    }
                }

            } else if (choice == 4) {
                // Display Attendees
                System.out.print("Event ID: ");
                String eventId = sc.nextLine();
                manager.displayAttendees(eventId);
                System.out.println("Do you want to return to menu or make a booking? (menu/book)");
                String next = sc.nextLine();
                if (next.equalsIgnoreCase("book")) {

                }

            } else {
                break;
            }
        }

        sc.close();
    }
}