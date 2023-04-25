import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight implements Serializable {

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

    @Override
    public String toString() {
        return String.format("{flightNumber=%s, departureDate=%s, arrivalDate=%s, departurePoint=%s, arrivalPoint=%s}",
                this.flightNumber, this.departureDate, this.arrivalDate, this.departurePoint, this.arrivalPoint);
    }

    public static List<Flight> getExtent() {
        return extent;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }
}
