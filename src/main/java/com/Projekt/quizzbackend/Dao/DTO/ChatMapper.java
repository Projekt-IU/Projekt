package com.Projekt.quizzbackend.Dao.DTO;

import com.Projekt.quizzbackend.Dao.DTO.Templates.ChatDTO;
import com.Projekt.quizzbackend.Team.Chat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatMapper {

    public ChatDTO entityToDTO(Chat chat) {
        ChatDTO dto = new ChatDTO();
        dto.setCreated(chat.getCreated());
        dto.setNachricht(chat.getNachricht());
        dto.setUsername(chat.getUser().getUserName());
        return dto;
    }

    public List<ChatDTO> entitiesToDTOs(List<Chat> chats) {
        return chats.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
}