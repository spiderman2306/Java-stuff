public class Event {
    String eventId;
    String date;
    String location;
    String stage;
    MyLinkedList<Attendee> attendees;

    public Event(String eventId, String date, String location, String stage) {
        this.eventId = eventId;
        this.date = date;
        this.location = location;
        this.stage = stage;
        this.attendees = new MyLinkedList<>();
    }

    public String toString() {
        return eventId + " - " + date + " @ " + location + " (" + stage + ")";
    }
}
