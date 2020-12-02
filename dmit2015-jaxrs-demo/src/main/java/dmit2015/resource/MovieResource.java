package dmit2015.resource;

import dmit2015.model.Movie;
import dmit2015.service.MovieRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**

 curl -i -X GET http://localhost:8080/webapi/movies

 curl -i -X GET http://localhost:8080/webapi/movies/2

 curl -i -X POST http://localhost:8080/webapi/movies \
    -H 'Content-Type:application/json' \
    -d '{"title":"COVID-19 Strikes Again","releaseDate":"2020-12-01","price":99.99,"genre":"Horror","rating":"PG"}'

 curl -i -X POST http://localhost:8080/webapi/movies \
 -H 'Content-Type:application/json' \
 -d '{"title":"COVID-19 Strikes Again 2","releaseDate":"2020-12-01","price":66.99,"genre":"Horror","rating":"PG"}'

 curl -i -X DELETE http://localhost:8080/webapi/movies/6

 curl -i -X PUT http://localhost:8080/webapi/movies/5 \
 -H 'Content-Type:application/json' \
 -d '{"id":5, "title":"COVID-19 is gone","releaseDate":"2020-12-01","price":9.99,"genre":"Comedy","rating":"PG"}'



 *
 */
@Path("movies")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject
    private MovieRepository currentMovieRepository;

    @Context
    private UriInfo currrentUriInfo;

    @GET    // /webapi/movies
    public Response getAllMovies() {
        List<Movie> movies = currentMovieRepository.findAll();

        return Response.ok(movies).build();
    }

    @GET
    @Path("{id}")
    public Response getOneMovie(@PathParam("id") Long movieId) {
        if (movieId == null) {
            throw new BadRequestException();
        }
        Optional<Movie> optionalMovie = currentMovieRepository.findById(movieId);
        if (optionalMovie.isEmpty()) {
            throw new NotFoundException();
        }
        Movie currentMovie = optionalMovie.get();

        return Response.ok(currentMovie).build();
    }

    @POST
    public Response createMovie(@Valid Movie newMovie) {
        if (newMovie == null) {
            throw new BadRequestException();
        }
        currentMovieRepository.add(newMovie);
        URI locationURI = currrentUriInfo.getAbsolutePathBuilder()
                .path(newMovie.getId().toString())
                .build();

        return Response.created(locationURI).build();
    }

    @PUT
    @Path("{id}")
    public Response updateMovie(@PathParam("id") Long movieId, Movie updatedMovie) {
        if (movieId == null || !movieId.equals(updatedMovie.getId())) {
            throw new BadRequestException();
        }
        currentMovieRepository.update(updatedMovie);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteMovie(@PathParam("id") Long movieId) {
        if (movieId == null) {
            throw new BadRequestException();
        }
        Optional<Movie> optionalMovie = currentMovieRepository.findById(movieId);
        if (optionalMovie.isEmpty()) {
            throw new NotFoundException();
        }
        currentMovieRepository.remove(movieId);
        return Response.noContent().build();
    }

}
