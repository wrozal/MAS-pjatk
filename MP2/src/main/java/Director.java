import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString(exclude = {"movies"})
public class Director {

    @Setter(AccessLevel.NONE)
    private Map<String, Movie> movies;

    private String name;
    private String surname;

    public Director(String name, String surname) {
        setUp();
        setName(name);
        setSurname(surname);
    }

    public void setUp() {
        this.movies = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        if (!movies.containsKey(movie.getTitle())) {
            movies.put(movie.getTitle(), movie);
            movie.setDirector(this);
        }
    }

    public void removeMovie(Movie movie) {
        if (movies.containsKey(movie.getTitle())) {
            movies.remove(movie.getTitle());
            movie.removeDirector();
        }
    }

}
