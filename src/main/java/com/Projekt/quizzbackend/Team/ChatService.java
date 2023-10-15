package com.Projekt.quizzbackend.Team;

import com.Projekt.quizzbackend.Dao.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public void addMessage(Chat chat) {
        Date date = new Date();
        Timestamp created;
        created = new Timestamp(System.currentTimeMillis());
        chat.setCreated(created);
        chatRepository.save(chat);
    }

    public List<Chat> getMessages(Teams team) {
        return chatRepository.findAllByTeam(team);
    }
}