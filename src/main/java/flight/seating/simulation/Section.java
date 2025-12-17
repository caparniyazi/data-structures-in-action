package flight.seating.simulation;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A section has a collection of seat objects.
 *
 * @param seats
 */
public record Section(Set<Seat> seats) {
    // Canonical constructor.
    public Section {
        if (seats == null) {
            seats = new LinkedHashSet<>();
        }
    }

    /**
     * Adds a seat.
     *
     * @param seat The seat.
     * @return false if duplicate, otherwise, true.
     */
    public boolean addSeat(Seat seat) {
        return seats.add(seat);
    }

    /**
     * It returns the first available seat.
     * In modern Java, returning null is usually avoidable.
     *
     * @return the first available seat.
     */
    public Optional<Seat> findSeat() {
        return seats.stream().filter(Seat::isAvailable).findFirst();
    }

    /**
     * Reserves the first available seat.
     *
     * @return The seat reserved.
     * @throws NoSeatAvailableException if there is no seat available.
     */
    public Seat reserveSeat() throws NoSeatAvailableException {
        Seat seat = findSeat().orElseThrow(NoSeatAvailableException::new);
        seat.reserve();
        return seat;
    }

    /**
     * Counts the number of available seats.
     *
     * @return The number of available seats that can be reserved.
     */
    public long countAvailableSeats() {
        return seats.stream().filter(Seat::isAvailable).count();
    }
}
