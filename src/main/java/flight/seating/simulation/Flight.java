package flight.seating.simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("LombokGetterMayBeUsed")
public class Flight {
    private final String flightNo;
    private final String origin;
    private final String destination;
    private final Map<Grade, Section> sectionMap = new HashMap<>();

    public Flight(String flightNo, String origin, String destination, Map<Grade, Set<Seat>> seats) {
        this.flightNo = flightNo;
        this.origin = origin;
        this.destination = destination;
        seats.forEach((key, value) -> sectionMap.put(key, new Section(value)));
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Seat reserveSeat(Grade grade) throws NoSeatAvailableException {
        Section section = sectionMap.get(grade);
        return section.reserveSeat();
    }

    public long countAvailableSeats(Grade grade) {
        return sectionMap.get(grade).countAvailableSeats();
    }

    public void printSeating() {
        System.out.println("Flight " + flightNo);

        for (Section section : sectionMap.values()) {
            System.out.println(section);
        }
        System.out.println();
    }
}
