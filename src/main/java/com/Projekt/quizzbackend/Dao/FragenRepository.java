package com.Projekt.quizzbackend.Dao;

import com.Projekt.quizzbackend.Quiz.Fragen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FragenRepository extends CrudRepository<Fragen, Integer> {

    List<Fragen> findByModul(String modul);

}