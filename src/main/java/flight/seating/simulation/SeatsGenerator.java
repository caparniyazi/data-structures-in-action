package flight.seating.simulation;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class SeatsGenerator {
    // Example usage:
    // seatsGenerator.createSeats(2, 4, 1) => [Seat(1A), Seat(1B), Seat(1C), Seat(1D), Seat(2A), Seat(2B), Seat(2C), Seat(2D)]

    public Set<Seat> createSeats(int rows, int seatsInRow, int firstRowNumber, Grade grade) {
        // Enforces uniqueness automatically and prevents duplicate seat IDs.
        // There cannot be two seats with the same seat number on a flight.
        Set<Seat> seats = new LinkedHashSet<>();    // Keeps insertion order, best for seating.
        char seq = 'A';

        IntStream.range(firstRowNumber, firstRowNumber + rows)
                .forEach(i -> {
                    for (int j = 0; j < seatsInRow; j++) {
                        Seat seat = new Seat(("" + i) + (char) (seq + j), grade);
                        seats.add(seat);
                    }
                });

        return seats;
    }
}
