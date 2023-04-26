import java.io.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static String fileName = Objects.requireNonNull(Main.class.getClassLoader().getResource("flightBookings")).getPath();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("===================================");
            System.out.println("1) Save extents");
            System.out.println("2) Load extents");
            System.out.println("3) Create bookings");
            System.out.println("4) Print bookings");
            System.out.println("5) Print total prices with VAT included");
            System.out.println("6) Print average booking price");
            System.out.println("7) Print only paid bookings");
            System.out.println("8) Print extent length");
            System.out.println("9) Print flight times");
            System.out.println("10) Print average flight time");
            System.out.println("0) Exit");

            int option = in.nextInt();
            switch (option) {
                case 1 -> saveExtents();
                case 2 -> loadExtents();
                case 3 -> createBookings();
                case 4 -> printBookings();
                case 5 -> printTotalPricesWithVAT();
                case 6 -> printAveragePrice();
                case 7 -> printOnlyPaidBookings();
                case 8 -> printExtentLength();
                case 9 -> printFlightTimes();
                case 10 -> printAverageFlightTime();
                case 0 -> System.exit(0);
            }
        }
    }

    private static void saveExtents() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            Booking.saveExtent(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadExtents() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Booking.loadExtent(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printBookings() {
        for (Booking booking : Booking.getExtent()) {
            System.out.println(booking);
        }
    }

    private static void printTotalPricesWithVAT() {
        System.out.println("Total prices with VAT:");
        for (Booking booking : Booking.getExtent()) {
            System.out.println("Price: " + booking.getTotalPriceWithVAT());
        }
    }

    private static void printAveragePrice() {
        System.out.println("Average booking price: " + Booking.getAveragePrice());
    }

    private static void printOnlyPaidBookings() {
        System.out.println("Paid bookings:");
        System.out.println(Booking.getOnlyPaidBookings());
    }

    private static void printExtentLength() {
        System.out.println("Extent length: " + Booking.getExtent().size());
    }

    private static void printFlightTimes() {
        for (Flight flight : Flight.getExtent()) {
            System.out.println(String.format("Flight number: %s, flight time: %s",
                    flight.getFlightNumber(), flight.getFlightTime()));
        }
    }

    private static void printAverageFlightTime() {
        System.out.println("Average flight time: " + Flight.getAverageFlightTime());
    }

    public static void createBookings() {
        Booking.builder()
                .passengers(Set.of(
                        Passenger.builder()
                                .name("Sofia")
                                .surname("Martinez")
                                .phoneNumber("555-123-456")
                                .email("sofia.martinez@gmail.com")
                                .build()
                ))
                .flight(Flight.builder()
                        .flightNumber("AB123")
                        .departureDate(LocalDateTime.of(2023, Month.MAY, 21, 10, 35))
                        .arrivalDate(LocalDateTime.of(2023, Month.MAY, 21, 18, 25))
                        .departurePoint("London")
                        .arrivalPoint("New York")
                        .build()
                )
                .price(1950)
                .isPaid(false)
                .build();


        Booking.builder()
                .passengers(Set.of(
                        Passenger.builder()
                                .name("John")
                                .surname("Smith")
                                .email("john.smith@gmail.com")
                                .build()
                ))
                .flight(Flight.builder()
                        .flightNumber("CD421")
                        .departureDate(LocalDateTime.of(2023, Month.MAY, 27, 9, 0))
                        .arrivalDate(LocalDateTime.of(2023, Month.MAY, 27, 22, 35))
                        .departurePoint("Paris")
                        .arrivalPoint("Tokyo")
                        .build()
                )
                .price(4460)
                .isPaid(true)
                .build();


        Booking.builder()
                .passengers(Set.of(
                        Passenger.builder()
                                .name("Maria")
                                .surname("Rodriguez")
                                .phoneNumber("555-345-987")
                                .email("maria.rodriguez@gmail.com")
                                .build()
                ))
                .flight(Flight.builder()
                        .flightNumber("EF755")
                        .departureDate(LocalDateTime.of(2023, Month.JUNE, 1, 14, 20))
                        .arrivalDate(LocalDateTime.of(2023, Month.JUNE, 1, 20, 35))
                        .departurePoint("New York")
                        .arrivalPoint("Los Angeles")
                        .build()
                )
                .price(810)
                .isPaid(true)
                .build();


        Booking.builder()
                .passengers(Set.of(
                        Passenger.builder()
                                .name("James")
                                .surname("Wilson")
                                .phoneNumber("555-333-111")
                                .email("james.wilson@gmail.com")
                                .build()
                ))
                .flight(Flight.builder()
                        .flightNumber("GH135")
                        .departureDate(LocalDateTime.of(2023, Month.JUNE, 1, 20, 0))
                        .arrivalDate(LocalDateTime.of(2023, Month.JUNE, 2, 19, 0))
                        .departurePoint("Sydney")
                        .arrivalPoint("Warsaw")
                        .build()
                )
                .price(7000)
                .isPaid(false)
                .build();
    }

}
