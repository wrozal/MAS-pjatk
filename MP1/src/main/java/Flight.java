import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@Builder
public class Flight implements Serializable {

    @Getter
    private static List<Flight> extent = new ArrayList<>();

    private static final long serialVersionUID = -1089169310081912521L;

    private String flightNumber;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String departurePoint;
    private String arrivalPoint;

    public Flight(String flightNumber, LocalDateTime departureDate, LocalDateTime arrivalDate, String departurePoint, String arrivalPoint) {
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
        addToExtent();
    }

    public void addToExtent() {
        extent.add(this);
    }

    public Duration getFlightTime() {
        return Duration.between(departureDate, arrivalDate);
    }

    public static Duration getAverageFlightTime() {
        return extent.stream()
                .map(Flight::getFlightTime)
                .reduce(Duration.ZERO, Duration::plus)
                .dividedBy(extent.size());
    }

}
