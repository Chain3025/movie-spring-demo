package com.georgian.movieactordemo.demo.repository;

import com.georgian.movieactordemo.demo.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director,Long> {

}
