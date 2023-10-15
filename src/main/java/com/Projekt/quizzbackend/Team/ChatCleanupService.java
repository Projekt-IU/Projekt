package com.Projekt.quizzbackend.Team;

import com.Projekt.quizzbackend.Dao.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ChatCleanupService {
    @Autowired
    private ChatRepository chatRepository;

    @Scheduled(cron = "0 0 0 * * *") // Jeden Tag um Mitternacht
    public void cleanupOldMessages() {
        Timestamp oneWeekAgo = new Timestamp(System.currentTimeMillis() - 7L * 24 * 60 * 60 * 1000);
        List<Chat> oldMessages = chatRepository.findAll(); // Ihre Methode, um alle Nachrichten zu erhalten
        oldMessages.removeIf(chat -> chat.getCreated().after(oneWeekAgo));
        chatRepository.deleteAll(oldMessages);
    }
}