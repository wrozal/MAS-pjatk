import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Booking implements Serializable {

    private static List<Booking> extent = new ArrayList<>();

    private Set<Passenger> passengers;
    private Flight flight;
    private float price;
    private boolean isPaid;

    public Booking(Set<Passenger> passengers, Flight flight, float price, boolean isPaid) {
        setPassengers(passengers);
        this.flight = flight;
        this.price = price;
        this.isPaid = isPaid;

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

    @Override
    public String toString() {
        return String.format("Booking {\n\tpassengers=%s, \n\tflight=%s, \n\tprice=%s, \n\tisPaid=%s, \n}",
                this.passengers, this.flight, getTotalPriceWithVAT(), this.isPaid);
    }

    public static List<Booking> getExtent() {
        return extent;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
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

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
