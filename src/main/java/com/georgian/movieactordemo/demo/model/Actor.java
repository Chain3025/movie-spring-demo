package com.georgian.movieactordemo.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "actorId"
)
public class Actor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long actorId;
  private String firstName;
  private String lastName;
  private String nationality;
  private LocalDate DOB;

  @ManyToMany(targetEntity = Movie.class,cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
  @JoinTable(
      name="actor_movies",
      joinColumns=
      @JoinColumn( name="actor_id", referencedColumnName="actorId"),
      inverseJoinColumns=@JoinColumn(name="movie_id", referencedColumnName="movieId"))
  @JsonIgnore
  private List<Movie> movies;


}