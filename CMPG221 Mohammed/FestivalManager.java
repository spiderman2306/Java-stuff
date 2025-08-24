import java.util.Random;

public class FestivalManager {
    MyLinkedList<Event> events = new MyLinkedList<>();
    String[] prizes = {"VIP Pass", "Free T-Shirt", "Backstage Access", "Food Voucher"};

    public void addEvent(Event e) {
        events.append(e);
    }

    // Find event by eventId
    public Event findEvent(String eventId) {
        int size = getListSize(events);
        Event found = null;
        for (int i = 0; i < size; i++) {
            Event e = events.removeFirst();
            if (e.eventId.equalsIgnoreCase(eventId)) {
                found = e;
            }
            events.append(e);
        }
        return found;
    }

    // Book a ticket for an attendee
    public boolean bookTicket(String eventId, Attendee attendee) {
        Event e = findEvent(eventId);
        if (e == null) return false;

        // Check if seat is already booked
        int size = getListSize(e.attendees);
        for (int i = 0; i < size; i++) {
            Attendee a = e.attendees.removeFirst();
            if (a.seat.equalsIgnoreCase(attendee.seat)) {
                e.attendees.append(a); // restore
                // restore rest
                for (int j = i + 1; j < size; j++) e.attendees.append(e.attendees.removeFirst());
                return false; // Seat already booked
            }
            e.attendees.append(a);
        }

        e.attendees.append(attendee);
        sortAttendeesByName(e);
        attendee.prize = spinPrize();
        return true;
    }

    // To Cancel booking by eventId and seat number
    public boolean cancelBooking(String eventId, String seat) {
        Event e = findEvent(eventId);
        if (e == null){
            return false;
        }

        int size = getListSize(e.attendees);
        boolean deleted = false;
        for (int i = 0; i < size; i++) {
            Attendee a = e.attendees.removeFirst();
            if (!deleted && a.seat.equalsIgnoreCase(seat)) {
                deleted = true;
                continue;
            }
            e.attendees.append(a);
        }
        return deleted;
    }

    // Check booking by attendee details
    public Attendee checkBooking(String eventId, String id, String name, String contact, String email) {
        Event e = findEvent(eventId);
        if (e == null) return null;

        int size = getListSize(e.attendees);
        Attendee found = null;
        for (int i = 0; i < size; i++) {
            Attendee a = e.attendees.removeFirst();
            if ((a.id.equalsIgnoreCase(id) && !id.isEmpty()) ||
                    (a.name.equalsIgnoreCase(name) && !name.isEmpty()) ||
                    (a.contact.equalsIgnoreCase(contact) && !contact.isEmpty()) ||
                    (a.email.equalsIgnoreCase(email) && !email.isEmpty())) {
                found = a;
            }
            e.attendees.append(a);
        }
        return found;
    }

    // Display attendees alphabetically
    public void displayAttendees(String eventId) {
        Event e = findEvent(eventId);
        if (e != null) {
            System.out.println("Attendees for " + e);
            sortAttendeesByName(e);
            int size = getListSize(e.attendees);
            for (int i = 0; i < size; i++) {
                Attendee a = e.attendees.removeFirst();
                System.out.println(a);
                e.attendees.append(a);
            }
        }
    }

    // sorting the attendees by their alphabetucals
    private void sortAttendeesByName(Event e) {
        int n = getListSize(e.attendees);
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Attendee a1 = e.attendees.removeFirst();
                Attendee a2 = e.attendees.removeFirst();
                if (a1.compareTo(a2) > 0) {
                    e.attendees.append(a2);
                    e.attendees.append(a1);
                } else {
                    e.attendees.append(a1);
                    e.attendees.append(a2);
                }
            }
            //
            for (int k = 0; k < i + 1; k++){
                e.attendees.append(e.attendees.removeFirst());
            }
        }
    }

    // size for my linked list?
    private <T> int getListSize(MyLinkedList<T> list) {
        int count = 0;
        T first = list.removeFirst();
        if (first == null) return 0;
        list.append(first);
        count++;
        while (list.getFirst() != first) {
            T t = list.removeFirst();
            list.append(t);
            count++;
        }
        return count;
    }

    // the wheel "spins"for prizes
    private String spinPrize() {
        Random rand = new Random();
        return prizes[rand.nextInt(prizes.length)];
    }
}