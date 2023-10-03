package com.Projekt.quizzbackend.Dao;

import com.Projekt.quizzbackend.Quiz.Fragen;
import com.Projekt.quizzbackend.Team.Teams;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FragenRepository extends CrudRepository<Fragen, Integer> {

}