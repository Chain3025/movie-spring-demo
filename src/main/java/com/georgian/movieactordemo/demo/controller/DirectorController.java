package com.georgian.movieactordemo.demo.controller;

import com.georgian.movieactordemo.demo.model.Actor;
import com.georgian.movieactordemo.demo.model.Director;
import com.georgian.movieactordemo.demo.service.ActorService;
import com.georgian.movieactordemo.demo.service.DirectorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/directors")
public class DirectorController {

  @Autowired
  private DirectorService directorService;


  @PostMapping()
  public ResponseEntity<Director> createDirector(@RequestBody Director director){
    ResponseEntity<Director> directorResponseEntity = directorService.addNewDirector(director);
    return directorResponseEntity;
  }

  @PutMapping()
  public ResponseEntity<Director> updateDirector(@RequestBody Director director, @RequestParam(value = "id")Long id){
    ResponseEntity<Director> directorResponseEntity = directorService.updateDirector(director, id);
    return directorResponseEntity;
  }

  @GetMapping()
  public ResponseEntity<List<Director>> retrieveAllDirector()
  {        return directorService.retierveAllDirector(); }

  @GetMapping("/id/{id}")
  public ResponseEntity<Director> retrieveDirectorById(@PathVariable(value = "id") Long directorId){
    return directorService.retierveDirectorById(directorId);
  }



  @DeleteMapping("/id/{id}")
  public ResponseEntity<Director> deleteDirectorById(@PathVariable("id") Long directorId){
    //return authorRepository.findById(id  );
    ResponseEntity<Director> directorResponseEntity = directorService.deleteById(directorId);
    return directorResponseEntity;

  }
}
