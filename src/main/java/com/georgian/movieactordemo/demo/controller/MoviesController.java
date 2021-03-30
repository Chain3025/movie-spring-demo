package com.georgian.movieactordemo.demo.controller;

import com.georgian.movieactordemo.demo.model.Director;
import com.georgian.movieactordemo.demo.model.Movie;
import com.georgian.movieactordemo.demo.model.MovieRequest;
import com.georgian.movieactordemo.demo.service.MovieService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MoviesController {

  private final MovieService movieService;

  public MoviesController(MovieService movieService) {
    this.movieService = movieService;
  }

  @PostMapping()
  public ResponseEntity<Object> createMovie(@RequestBody MovieRequest movie){
    ResponseEntity<Object> movieResponseEntity = movieService.addNewMovie(movie);
    return movieResponseEntity;
  }

  @PutMapping()
  public ResponseEntity<Movie> updateMovie(@RequestBody MovieRequest movie, @RequestParam(value = "id")Long id){
    ResponseEntity<Movie> movieResponseEntity = movieService.updateMovie(movie, id);
    return movieResponseEntity;
  }

  @GetMapping()
  public ResponseEntity<List<Movie>> retrieveAllMovie()
  {        return movieService.retierveAllMovie(); }

  @GetMapping("/id/{id}")
  public ResponseEntity<Movie> retrieveDirectorById(@PathVariable(value = "id") Long movieId){
    return movieService.retierveMovieById(movieId);
  }



  @DeleteMapping("/id/{id}")
  public ResponseEntity<Object> deleteMovieById(@PathVariable("id") Long movieId){
    //return authorRepository.findById(id  );
    ResponseEntity<Object> movieResponseEntity = movieService.deleteById(movieId);
    return movieResponseEntity;

  }
}
