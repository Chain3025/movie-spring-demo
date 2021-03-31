package com.georgian.movieactordemo.demo.service;

import com.georgian.movieactordemo.demo.model.Actor;
import com.georgian.movieactordemo.demo.model.ActorRequest;
import com.georgian.movieactordemo.demo.repository.ActorRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

  private final ActorRepository actorRepository;

  public ActorService(ActorRepository actorRepository) {
    this.actorRepository = actorRepository;
  }

  public ResponseEntity<Object> addNewActor(Actor actorRequest) {

    Actor actor = new Actor();
    actor.setFirstName(actorRequest.getFirstName());
    actor.setLastName(actorRequest.getLastName());
    actor.setDOB(actorRequest.getDOB());
    actor.setNationality(actorRequest.getNationality());
    actor.setMovies(actorRequest.getMovies());
    Actor save = actorRepository.save(actor);

    if (actorRepository.findById(save.getActorId()).isPresent())
      return ResponseEntity.accepted().body("actor created successfully");
    else
      return ResponseEntity.unprocessableEntity().body("Failed create the user specified");
  }

  public ResponseEntity<Actor> updateActor(Actor reqActor, Long id) {
    Optional<Actor> byId = actorRepository.findById(id);
    if(!byId.isPresent()){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Actor dbActor = byId.get();
    if(reqActor.getFirstName()!=null){
      dbActor.setFirstName(reqActor.getFirstName());
    }
    if(reqActor.getLastName()!=null){
      dbActor.setLastName(reqActor.getLastName());
    }
    if(reqActor.getDOB()!=null){
      dbActor.setDOB(reqActor.getDOB());
    }
    if(reqActor.getNationality()!=null){
      dbActor.setNationality(reqActor.getNationality());
    }
    if(reqActor.getActorId()!=null){
      dbActor.setActorId(reqActor.getActorId());
    }
    dbActor.setMovies(reqActor.getMovies());
    Actor saveActor = actorRepository.save(dbActor);

    return new ResponseEntity<>(saveActor,HttpStatus.ACCEPTED);
  }

  public ResponseEntity<List<Actor>> retierveAllActor() {
    List<Actor> all = actorRepository.findAll();
    return new ResponseEntity<>(all,HttpStatus.OK);
  }

  public ResponseEntity<Actor> retierveActorById(Long actorId) {
    Optional<Actor> byId = actorRepository.findById(actorId);
    if(!byId.isPresent()){
      return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(byId.get(),HttpStatus.OK);
  }

  public ResponseEntity<Object> deleteById(Long actorId) {
    if (actorRepository.findById(actorId).isPresent()) {
      actorRepository.deleteById(actorId);
      if (actorRepository.findById(actorId).isPresent())
        return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified User");
      else return ResponseEntity.ok().body("Successfully deleted the specified user");
    } else
      return ResponseEntity.badRequest().body("Cannot find the user specified");
  }
}
