package com.Projekt.quizzbackend.Dao;

import com.Projekt.quizzbackend.Team.Chat;
import com.Projekt.quizzbackend.Team.Teams;
import com.Projekt.quizzbackend.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    List<Chat> findAllByTeam(Teams team);

    List<Chat> findByUser(User user);
}