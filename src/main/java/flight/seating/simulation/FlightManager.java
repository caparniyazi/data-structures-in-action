package flight.seating.simulation;

import java.util.*;

/**
 * The simulation driver.
 * The hierarchy is:
 * FlightManager -> Flight -> Section -> Set<Seat>
 */
public class FlightManager {
    static final Set<Flight> flights = new HashSet<>();

    public static void main(String[] args) throws NoSeatAvailableException {
        // Create two business grade seats of rows.

        Set<Seat> seats = (new SeatsGenerator()).createSeats(2, 4, 1, Grade.BUSINESS);
        Map<Grade, Set<Seat>> map = new HashMap<>();
        map.put(Grade.BUSINESS, seats);
        // Create three economy grade seat of rows.
        seats = (new SeatsGenerator()).createSeats(3, 4, 3, Grade.ECONOMY);
        map.put(Grade.ECONOMY, seats);

        // Create a flight for the specified origin, destination, and a class of seats.
        Flight flight = new Flight("TK-3444", "SAW", "MLX", map);
        boolean flightAdded = addNewFlight(flight);

        if (flightAdded) {
            findFlight(flight.getFlightNo()).ifPresent(Flight::printSeating);
            // Reserve the first available economy class seat.
            flight.reserveSeat(Grade.ECONOMY);
            // Reserve the first available business class seat.
            flight.reserveSeat(Grade.BUSINESS);

            flight.printSeating();
            System.out.println("The number of business class seats available: " + flight.countAvailableSeats(Grade.BUSINESS));
            System.out.println("The number of economy class seats available: " + flight.countAvailableSeats(Grade.ECONOMY));
        }
    }


    public static boolean addNewFlight(Flight flight) {
        return flights.add(flight);
    }

    public static Optional<Flight> findFlight(String flightNo) {
        return flights.stream().filter(f -> f.getFlightNo().equals(flightNo)).findFirst();
    }
}
