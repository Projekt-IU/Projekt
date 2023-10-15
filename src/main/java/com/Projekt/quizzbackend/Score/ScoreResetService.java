package com.Projekt.quizzbackend.Score;

import com.Projekt.quizzbackend.Dao.TeamsRepository;
import com.Projekt.quizzbackend.Dao.UserRepository;
import com.Projekt.quizzbackend.Team.Teams;
import com.Projekt.quizzbackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class ScoreResetService {

    @Autowired
    private UserRepository userRepository;  // Ihr UserRepository

    @Autowired
    private TeamsRepository teamsRepository;  // Ihr TeamsRepository

    @Scheduled(cron = "0 0 0 * * MON")  // Jeden Montag um Mitternacht
    public void resetWeeklyScores() {
        Iterable<User> allUsers = userRepository.findAll();  // oder Ihre Methode zum Abrufen aller Benutzer

        for (User user : allUsers) {
            ScoreUser score = user.getScoreUser();
            score.setPunkteWoche(0);  // Punkte für die Woche zurücksetzen
            // Ähnlich für Monat und Jahr, falls gewünscht
        }

        userRepository.saveAll(allUsers);  // Änderungen speichern
    }

    @Scheduled(cron = "0 0 0 1 * *")  // Jeden ersten Tag des Monats um Mitternacht
    public void resetMonthlyScores() {
        Iterable<User> allUsers = userRepository.findAll();  // oder Ihre Methode zum Abrufen aller Benutzer

        for (User user : allUsers) {
            ScoreUser score = user.getScoreUser();
            score.setPunkteMonat(0);  // Punkte für den Monat zurücksetzen
        }

        userRepository.saveAll(allUsers);  // Änderungen speichern
    }


    // Für Teams
    @Scheduled(cron = "0 0 0 * * MON")  // Jeden Montag um Mitternacht
    public void resetTeamWeeklyScores() {
        Iterable<Teams> allTeams = teamsRepository.findAll();

        for (Teams team : allTeams) {
            ScoresTeam score = team.getScoreTeam();
            score.setPunkteWoche(0);
        }

        teamsRepository.saveAll(allTeams);
    }

    @Scheduled(cron = "0 0 0 1 * *")  // Jeden ersten Tag des Monats um Mitternacht
    public void resetTeamMonthlyScores() {
        Iterable<Teams> allTeams = teamsRepository.findAll();

        for (Teams team : allTeams) {
            ScoresTeam score = team.getScoreTeam();
            score.setPunkteMonat(0);
        }

        teamsRepository.saveAll(allTeams);
    }

}
