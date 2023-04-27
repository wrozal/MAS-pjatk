import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@ToString
@Builder
public class Booking implements Serializable {

    @Getter
    private static List<Booking> extent = new ArrayList<>();

    private static final long serialVersionUID = -6448559927141637904L;

    private Set<Passenger> passengers;
    private Flight flight;
    private BigDecimal price;
    private boolean isPaid;

    public Booking(Set<Passenger> passengers, Flight flight, BigDecimal price, boolean isPaid) {
        setPassengers(passengers);
        this.flight = flight;
        this.price = price;
        this.isPaid = isPaid;
        addToExtent();
    }

    public void addToExtent() {
        extent.add(this);
    }

    public static void saveExtent(ObjectOutputStream stream) {
        try {
            stream.writeObject(extent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadExtent(ObjectInputStream stream) {
        try {
            extent = (ArrayList<Booking>) stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getTotalPriceWithVAT() {
        return BigDecimal.valueOf(passengers.size())
                .multiply(price)
                .multiply(BigDecimal.valueOf(1.23));
    }

    public BigDecimal getTotalPriceWithVAT(BigDecimal vat) {
        return BigDecimal.valueOf(passengers.size())
                .multiply(price)
                .multiply(vat);
    }

    public static BigDecimal getAveragePrice() {
        if (extent.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return extent.stream()
                .map(Booking::getTotalPriceWithVAT)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(extent.size()), 2, RoundingMode.HALF_UP);
    }

    public static List<Booking> getOnlyPaidBookings() {
        return extent.stream()
                .filter(b -> b.isPaid)
                .collect(Collectors.toList());
    }

    public void setPassengers(Set<Passenger> passengers) {
        if (passengers.size() == 0) {
            try {
                throw new Exception("Booking must be for at least one person!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.passengers = passengers;
    }

}
