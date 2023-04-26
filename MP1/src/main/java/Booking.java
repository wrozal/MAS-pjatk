import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@ToString
public class Booking implements Serializable {

    @Getter
    private static List<Booking> extent = new ArrayList<>();

    private static final long serialVersionUID = -6448559927141637904L;

    private Set<Passenger> passengers;
    private Flight flight;
    private float price;
    private boolean isPaid;

    public Booking(Set<Passenger> passengers, Flight flight, float price, boolean isPaid) {
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

    public float getTotalPriceWithVAT() {
        return passengers.size() * price * 1.23f;
    }

    public float getTotalPriceWithVAT(float vat) {
        return passengers.size() * price * vat;
    }

    public static float getAveragePrice() {
        return (float) extent.stream()
                .mapToDouble(Booking::getTotalPriceWithVAT)
                .average()
                .orElse(0d);
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
