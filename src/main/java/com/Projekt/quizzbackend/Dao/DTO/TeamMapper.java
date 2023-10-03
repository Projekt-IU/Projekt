package com.Projekt.quizzbackend.Dao.DTO;

import com.Projekt.quizzbackend.Dao.DTO.Templates.ScoreTeamDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.TeamDTO;
import com.Projekt.quizzbackend.Team.ScoresTeam;
import com.Projekt.quizzbackend.Team.Teams;

import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    //erfordert ein true für die Ausgabe Team + score, bei einem false nur Team

    public TeamDTO convertToDTO(Teams team, boolean includeScoreTeam) {
        TeamDTO dto = new TeamDTO();
        dto.setTeamsId(team.getTeamsId());
        dto.setName(team.getName());
        dto.setStudiengang(team.getStudiengang());
        dto.setAdminUserId(team.getAdmin().getUserID());
        dto.setAdminUsername(team.getAdmin().getUserName());

        if (includeScoreTeam) {
            ScoresTeam scoreTeam = team.getScoreTeam();
            ScoreTeamDTO scoreTeamDTO = new ScoreTeamDTO();
            scoreTeamDTO.setPunkteGesamt(scoreTeam.getPunkteGesamt());
            scoreTeamDTO.setPunkteMonat(scoreTeam.getPunkteMonat());
            scoreTeamDTO.setPunkteWoche(scoreTeam.getPunkteWoche());
            scoreTeamDTO.setFrageRichtig(scoreTeam.getFrageRichtig());
            scoreTeamDTO.setFragenGesamt(scoreTeam.getFragenGesamt());
            dto.setScoreTeam(scoreTeamDTO);
        }

        return dto;
    }

}
