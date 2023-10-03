package com.Projekt.quizzbackend.Team;

import com.Projekt.quizzbackend.User.User;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Chat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "chat_id", nullable = false)
    private Integer chatId;
    @Basic
    @Column(name = "nachricht", nullable = false, length = 500)
    private String nachricht;
    @Basic
    @Column(name = "created", nullable = false)
    private Timestamp created;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private Teams team;

    @ManyToOne
    @JoinColumn(name = "benutzer_id", referencedColumnName = "benutzer_id")
    private User user;
    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getNachricht() {
        return nachricht;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(chatId, chat.chatId) && Objects.equals(nachricht, chat.nachricht) && Objects.equals(created, chat.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, nachricht, created);
    }
}
