import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"moviesSal"})
public class Sal {

    @Setter(AccessLevel.NONE)
    private List<MovieSal> moviesSal;

    private int number;
    private int numberOfSeats;

    public Sal(int number, int numberOfSeats) {
        setUp();
        setNumber(number);
        setNumberOfSeats(numberOfSeats);
    }

    public void setUp() {
        this.moviesSal = new ArrayList<>();
    }

    public void addMovieSal(MovieSal movieSal) {
        if (!moviesSal.contains(movieSal)) {
            moviesSal.add(movieSal);
            movieSal.setSal(this);
        }
    }

    public void removeMovieSal(MovieSal movieSal) {
        if (moviesSal.contains(movieSal)) {
            moviesSal.remove(movieSal);
            movieSal.remove();
        }
    }

}
