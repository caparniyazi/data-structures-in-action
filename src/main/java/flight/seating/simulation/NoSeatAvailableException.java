package flight.seating.simulation;

public class NoSeatAvailableException extends Exception {
    public NoSeatAvailableException() {
        super("No seat available for reservation");
    }

    public NoSeatAvailableException(String message) {
        super(message);
    }
}
