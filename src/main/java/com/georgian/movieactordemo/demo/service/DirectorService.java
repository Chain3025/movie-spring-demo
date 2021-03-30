package com.georgian.movieactordemo.demo.service;

import com.georgian.movieactordemo.demo.model.Actor;
import com.georgian.movieactordemo.demo.model.Director;
import com.georgian.movieactordemo.demo.repository.DirectorRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {

  private final DirectorRepository directorRepository;

  public DirectorService(DirectorRepository directorRepository) {
    this.directorRepository = directorRepository;
  }

  public ResponseEntity<Director> addNewDirector(Director director) {
    Director save = directorRepository.save(director);
    return new ResponseEntity<>(save, HttpStatus.ACCEPTED);
  }

  public ResponseEntity<Director> updateDirector(Director reqDirector, Long id) {
    Optional<Director> byId = directorRepository.findById(id);
    if(!byId.isPresent()){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Director dbDirector = byId.get();
    if(reqDirector.getFirstName()!=null){
      dbDirector.setFirstName(reqDirector.getFirstName());
    }
    if(reqDirector.getLastName()!=null){
      dbDirector.setLastName(reqDirector.getLastName());
    }
    if(reqDirector.getDOB()!=null){
      dbDirector.setDOB(reqDirector.getDOB());
    }
    if(reqDirector.getNationality()!=null){
      dbDirector.setNationality(reqDirector.getNationality());
    }
    if(reqDirector.getDirectorId()!=null){
      dbDirector.setDirectorId(reqDirector.getDirectorId());
    }
    Director savedDirector = directorRepository.save(dbDirector);

    return new ResponseEntity<>(savedDirector,HttpStatus.ACCEPTED);

  }

  public ResponseEntity<List<Director>> retierveAllDirector() {
    return new ResponseEntity<>(directorRepository.findAll(),HttpStatus.OK);
  }

  public ResponseEntity<Director> deleteById(Long directorId) {
    Optional<Director> byId = directorRepository.findById(directorId);
    if(!byId.isPresent()){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    directorRepository.delete(byId.get());
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  public ResponseEntity<Director> retierveDirectorById(Long directorId) {
    Optional<Director> byId = directorRepository.findById(directorId);
    if(!byId.isPresent()){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(byId.get(),HttpStatus.ACCEPTED);

  }
}
