import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

public class Main {

    public static final String RESET = "\033[0m";
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";

    public static void main(String[] args) throws Exception {

        System.out.println(YELLOW_BOLD_BRIGHT + "Association \"Basic\"" + RESET);

        var movie1 = new Movie("Murder Mystery 2", "Comedy");
        var actor1 = new Actor("Adam", "Sandler");

        actor1.addMovie(movie1);

        System.out.println(actor1.getMovies());
        System.out.println(movie1.getActors());

        System.out.println(YELLOW_BOLD_BRIGHT + "Association with an attribute" + RESET);

        var movie2 = new Movie("Bullet Train", "Action");
        var sal1 = new Sal(1, 200);
        var movieSal1 = new MovieSal(movie2, sal1, LocalDateTime.of(2023, Month.APRIL, 20, 21, 30));

        System.out.println(movieSal1.getMovie());
        System.out.println(movieSal1.getSal());

        System.out.println(YELLOW_BOLD_BRIGHT + "---------------------------------------" + RESET);

        System.out.println(movie2.getMoviesSal());
        System.out.println(sal1.getMoviesSal());
        System.out.println(movieSal1);

        System.out.println(YELLOW_BOLD_BRIGHT + "Composition" + RESET);

        var viewer1 = new Viewer("Michael", "97867564530");
        Ticket.createTicket(viewer1, BigDecimal.valueOf(25));
        var ticket1 = Ticket.createTicket(viewer1, BigDecimal.valueOf(45));

        var viewer2 = new Viewer("Oscar", "13242123189");
        Ticket.createTicket(viewer2, BigDecimal.valueOf(30));

        System.out.println(viewer1);
        System.out.println(viewer2);

        try {
            viewer1.addTicket(ticket1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            viewer2.addTicket(ticket1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(YELLOW_BOLD_BRIGHT + "Qualified association" + RESET);

        var movie3 = new Movie("Pulp Fiction", "Crime");
        var movie4 = new Movie("Death Proof", "Action");
        var director1 = new Director("Quentin", "Tarantino");

        director1.addMovie(movie3);
        director1.addMovie(movie4);

        System.out.println(movie3.getDirector());
        System.out.println(movie4.getDirector());

        System.out.println(
                Arrays.toString(
                        director1.
                                getMovies().
                                entrySet().
                                stream().
                                map(movie -> movie.getKey() + ": " + movie.getValue().getGenre()).
                                toArray()
                )
        );

    }
}
