import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MovieSal {

    private Movie movie;
    private Sal sal;

    private LocalDateTime screeningDate;

    public MovieSal(Movie movie, Sal sal, LocalDateTime screeningDate) {
        setMovie(movie);
        setSal(sal);
        setScreeningDate(screeningDate);
    }

    public void remove() {
        removeMovie();
        removeSal();
    }

    public void setMovie(Movie movie) {
        if (this.movie == null) {
            this.movie = movie;
            this.movie.addMovieSal(this);
        }
    }

    public void removeMovie() {
        if (movie != null) {
            movie.removeMovieSal(this);
            movie = null;
        }
    }

    public void setSal(Sal sal) {
        if (this.sal == null) {
            this.sal = sal;
            this.sal.addMovieSal(this);
        }
    }

    public void removeSal() {
        if (sal != null) {
            sal.removeMovieSal(this);
            sal = null;
        }
    }

}
