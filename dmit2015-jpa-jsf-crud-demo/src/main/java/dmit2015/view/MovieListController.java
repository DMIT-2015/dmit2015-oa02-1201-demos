package dmit2015.view;

import dmit2015.model.Movie;
import dmit2015.service.MovieRepository;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("currentMovieListController")
@ViewScoped
public class MovieListController implements Serializable {

    @Inject
    private MovieRepository movieRepository;

    private List<Movie> movies;

    @PostConstruct  // After @Inject is complete
    public void init() {
        // Fetch the list of movies only once
        movies = movieRepository.findAll();
    }

    public List<Movie> list() {
        return movies;
    }

}