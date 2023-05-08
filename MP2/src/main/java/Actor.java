import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"movies"})
public class Actor {

    @Setter(AccessLevel.NONE)
    private List<Movie> movies;

    private String name;
    private String surname;

    public Actor(String name, String surname) {
        setUp();
        setName(name);
        setSurname(surname);
    }

    public void setUp() {
        this.movies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        if (!movies.contains(movie)) {
            movies.add(movie);
            movie.setActor(this);
        }
    }

    public void removeMovie(Movie movie) {
        if (movies.contains(movie)) {
            movies.remove(movie);
            movie.removeActor(this);
        }
    }

}
