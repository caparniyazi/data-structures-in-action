package codingpractice;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The class finds the duration between two times given as a string in 12-hour format.
 *
 * @pre The input string is in the following format:
 * hh:mm AM/PM-hh:mm AM/PM
 */
public class TimeDifference {
    /**
     * @param theTime The start time and end time separated with a hyphen.
     * @return The duration in minutes.
     */
    public static long findTheTimeDifference(String theTime) {
        String[] times = theTime.split("-");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a").localizedBy(Locale.US);

        LocalTime startTime = LocalTime.parse(times[0], format);
        LocalTime endTime = LocalTime.parse(times[1], format);
        Duration duration = Duration.between(startTime, endTime);

        return duration.toMinutes();
    }

    public static void main(String[] args) {
        System.out.println(findTheTimeDifference("07:00 AM-11:00 PM"));
    }
}
