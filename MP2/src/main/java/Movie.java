import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"director", "moviesSal", "actors"})
public class Movie {

    private Director director;
    @Setter(AccessLevel.NONE)
    private List<Actor> actors;
    @Setter(AccessLevel.NONE)
    private List<MovieSal> moviesSal;

    private String title;
    private String genre;

    public Movie(String title, String genre) {
        setUp();
        setTitle(title);
        setGenre(genre);
    }

    public void setUp() {
        this.actors = new ArrayList<>();
        this.moviesSal = new ArrayList<>();
    }

    public void setActor(Actor actor) {
        if (!actors.contains(actor)) {
            actors.add(actor);
            actor.addMovie(this);
        }
    }

    public void removeActor(Actor actor) {
        if (actors.contains(actor)) {
            actors.remove(actor);
            actor.removeMovie(this);
        }
    }

    public void setDirector(Director director) {
        if (this.director == null) {
            this.director = director;
            director.addMovie(this);
        }
    }

    public void removeDirector() {
        if (director != null) {
            director.removeMovie(this);
            director = null;
        }
    }

    public void addMovieSal(MovieSal movieSal) {
        if (!moviesSal.contains(movieSal)) {
            moviesSal.add(movieSal);
            movieSal.setMovie(this);
        }
    }

    public void removeMovieSal(MovieSal movieSal) {
        if (moviesSal.contains(movieSal)) {
            moviesSal.remove(movieSal);
            movieSal.remove();
        }
    }

}

