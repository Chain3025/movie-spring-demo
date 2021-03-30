package com.georgian.movieactordemo.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Actor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long actorId;
  private String firstName;
  private String lastName;
  private String nationality;
  private LocalDate DOB;

//  @ManyToMany(fetch = FetchType.LAZY,mappedBy = "actors",cascade = CascadeType.REMOVE)

//  @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "actor")
//  @JsonBackReference
//  private List<MovieActor> movieActors;

}