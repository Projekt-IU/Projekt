package com.Projekt.quizzbackend.Dao;

import com.Projekt.quizzbackend.Controller.TeamController;
import com.Projekt.quizzbackend.Team.Teams;
import com.Projekt.quizzbackend.User.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//User daten werden hier gespeichert oder abgerufen. Datenbankanbindung. Möglicherweise benötigen wir dafür eine Oberklasse wenn wir ein 2. @Repository anfangen für z.b. Quizz fragen
@Repository
public interface TeamsRepository extends CrudRepository<Teams, Integer> {






    Teams findByName(String name);
    Teams findTeamsByName(String name);


    Teams findByTeamsId(Integer teamsId);


}