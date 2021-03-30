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

  public ResponseEntity<Actor> addNewActor(ActorRequest actorRequest) {
    Actor actor = new Actor();
    actor.setFirstName(actorRequest.getFirstName());
    actor.setLastName(actorRequest.getLastName());
    actor.setDOB(actorRequest.getDOB());
    actor.setNationality(actorRequest.getNationality());
    Actor save = actorRepository.save(actor);
    return new ResponseEntity<>(save, HttpStatus.ACCEPTED);
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

  public ResponseEntity<Actor> deleteById(Long actorId) {
    Optional<Actor> byId = actorRepository.findById(actorId);
    if(!byId.isPresent()){
      return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
    Actor actor = byId.get();
    //actor.setMovies(null);
    actorRepository.save(actor);
    actorRepository.delete(byId.get());
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
