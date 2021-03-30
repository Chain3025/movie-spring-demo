package com.georgian.movieactordemo.demo.service;

import com.georgian.movieactordemo.demo.model.Actor;
import com.georgian.movieactordemo.demo.model.Movie;
import com.georgian.movieactordemo.demo.model.MovieRequest;
import com.georgian.movieactordemo.demo.repository.ActorRepository;
import com.georgian.movieactordemo.demo.repository.MovieRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

  private final MovieRepository movieRepository;
  private final ActorRepository actorRepository;
  public MovieService(MovieRepository movieRepository,
      ActorRepository actorRepository) {
    this.movieRepository = movieRepository;
    this.actorRepository = actorRepository;
  }


  public ResponseEntity<Object> addNewMovie(MovieRequest movie) {
    Movie movie1 = new Movie();

    movie1.setMovieTitle(movie.getMovieTitle());
    movie1.setDirectorId(movie.getDirectorId());
    movie1.setRating(movie.getRating());
    movie1.setReleaseYear(movie.getReleaseYear());

    for(Actor actor:movie.getActorList())
    {
      Actor save = actorRepository.save(actor);
      if(!actorRepository.findById(save.getActorId()).isPresent()){
        return ResponseEntity.unprocessableEntity().body("failed");
      }
    }


    movie1.setActors(movie.getActorList());
    //movie1.setActors(movie.getActorList());

    //movie.getActorList().forEach(actor1 -> actor1.setMovies(Arrays.asList(movie1)));
    Movie save = movieRepository.save(movie1);
    if(movieRepository.findById(save.getMovieId()).isPresent()){
      return ResponseEntity.accepted().body("Successfuly created movie  and actors");

    }else
      return ResponseEntity.unprocessableEntity().body("failed to create movie");

  }

  public ResponseEntity<Movie> updateMovie(MovieRequest movieRequest, Long movieId) {
    Optional<Movie> byId = movieRepository.findById(movieId);
    if(!byId.isPresent()){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Movie movie = byId.get();
    if(movieRequest.getMovieTitle()!=null){
      movie.setMovieTitle(movieRequest.getMovieTitle());
    }
    if(movieRequest.getDirectorId()!=null){
      movie.setDirectorId(movieRequest.getDirectorId());
    }
    if(movieRequest.getRating()!=null){
      movie.setRating(movieRequest.getRating());
    }
    if(movieRequest.getReleaseYear()!=null){
      movie.setReleaseYear(movieRequest.getReleaseYear());
    }
//    if(movieRequest.getActorList()!=null){
////      movie.setActors(movieRequest.getActorList());
////      movieRequest.getActorList().forEach(actor -> actor.setMovies(Arrays.asList(movie)));
////    }
    Movie save = movieRepository.save(movie);
    return new ResponseEntity<>(save,HttpStatus.ACCEPTED);
  }

  public ResponseEntity<List<Movie>> retierveAllMovie() {
    return new ResponseEntity<>(movieRepository.findAll(),HttpStatus.OK);
  }

  public ResponseEntity<Movie> retierveMovieById(Long movieId) {
    Optional<Movie> byId = movieRepository.findById(movieId);
    if(!byId.isPresent()){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(byId.get(),HttpStatus.OK);
  }

  public ResponseEntity<Object> deleteById(Long movieId) {

    if(movieRepository.findById(movieId).isPresent()){
      movieRepository.deleteById(movieId);
      if(movieRepository.findById(movieId).isPresent()){
        return ResponseEntity.unprocessableEntity().body("failed to delete movie");
      }else
        return ResponseEntity.ok().body("succefully deleted");
    }else
      return ResponseEntity.unprocessableEntity().body("no record found");

//    Optional<Movie> byId = movieRepository.findById(movieId);
//    if(!byId.isPresent()){
//      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//    movieRepository.delete(byId.get());
//    return new ResponseEntity<>(HttpStatus.OK);
  }
}
