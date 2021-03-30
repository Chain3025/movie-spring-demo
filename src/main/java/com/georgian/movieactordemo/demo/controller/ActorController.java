package com.georgian.movieactordemo.demo.controller;

import com.georgian.movieactordemo.demo.model.Actor;
import com.georgian.movieactordemo.demo.service.ActorService;
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
@RequestMapping("/actors")
public class ActorController {

  @Autowired
  private ActorService actorService;


  @PostMapping()
  public ResponseEntity<Actor> createActor(@RequestBody Actor actor){
    ResponseEntity<Actor> actorResponseEntity = actorService.addNewActor(actor);
    return actorResponseEntity;
  }

  @PutMapping()
  public ResponseEntity<Actor> updateActor(@RequestBody Actor actor, @RequestParam(value = "id")Long id){
    ResponseEntity<Actor> actorResponseEntity = actorService.updateActor(actor, id);
    return actorResponseEntity;
  }

  @GetMapping()
  public ResponseEntity<List<Actor>> retrieveAllActor()
  {        return actorService.retierveAllActor(); }

  @GetMapping("/id/{id}")
  public ResponseEntity<Actor> retrieveActorById(@PathVariable(value = "id") Long actorId){
    return actorService.retierveActorById(actorId);
  }



  @DeleteMapping("/id/{id}")
  public ResponseEntity<Actor> deleteActorById(@PathVariable("id") Long actorId){
    //return authorRepository.findById(id  );
    ResponseEntity<Actor> actorResponseEntity = actorService.deleteById(actorId);
    return actorResponseEntity;

  }

}
