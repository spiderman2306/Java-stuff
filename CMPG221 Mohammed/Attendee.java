public class Attendee implements Comparable<Attendee> {
    String id;
    String name;
    String surname;
    String contact;
    String email;
    String seat;
    String prize;

    public Attendee(String id, String name, String surname, String contact, String email, String seat) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.contact = contact;
        this.email = email;
        this.seat = seat;
        this.prize = null;
    }

    public String toString() {
        return name + " " + surname + " (" + id + ")";
    }

    public int compareTo(Attendee other) {
        return this.name.compareToIgnoreCase(other.name);
    }
}
