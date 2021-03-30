package com.georgian.movieactordemo.demo.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
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
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long movieId;
  private Integer directorId;
  private String movieTitle;
  private LocalDate releaseYear;
  private Integer rating;

//  @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//  @JoinTable(name = "movie_actor",
//      joinColumns ={@JoinColumn(name = "movie_id")},
//      inverseJoinColumns = {@JoinColumn(name = "actor_id")}
//  )

//  @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "movie")
//  @JsonBackReference
//  private List<MovieActor> movieActors;
  @JoinColumn(name = "movie_id")
  @OneToMany(targetEntity = Actor.class,cascade = CascadeType.ALL)
  private List<Actor> actors;
}