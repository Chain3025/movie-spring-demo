package com.georgian.movieactordemo.demo.repository;

import com.georgian.movieactordemo.demo.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {

}
