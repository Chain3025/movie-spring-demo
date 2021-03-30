package com.georgian.movieactordemo.demo.model;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class MovieRequest {

  private Integer directorId;
  private String movieTitle;
  private LocalDate releaseYear;
  private Integer rating;
  private List<Actor> actorList;
}
