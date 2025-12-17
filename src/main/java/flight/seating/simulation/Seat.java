package flight.seating.simulation;

import java.util.Objects;

/**
 * Requires proper equals() and hashCode().
 * The ordering must be handled explicitly.
 * <p>
 * Seat of "1A", "1B", etc.
 */
public class Seat {
    // Data fields
    private final String number;
    private Status status = Status.AVAILABLE;
    private final Grade grade;

    // Constructor
    public Seat(String number, Grade grade) {
        this.number = number;
        this.grade = grade;
    }

    // Methods
    public String getNumber() {
        return number;
    }

    public Status getStatus() {
        return status;
    }

    public Grade getGrade() {
        return grade;
    }

    public boolean isAvailable() {
        return status == Status.AVAILABLE;
    }

    public void reserve() {
        status = Status.RESERVED;
    }

    public enum Status {
        AVAILABLE, RESERVED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null != o && o.getClass() == this.getClass()) {
            Seat seat = (Seat) o;
            return Objects.equals(number, seat.number) && status == seat.status;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, status);
    }

    @Override
    public String toString() {
        return "Seat{number='%s', status=%s}".formatted(number, status);
    }
}
