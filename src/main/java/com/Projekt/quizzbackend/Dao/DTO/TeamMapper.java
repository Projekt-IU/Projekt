package com.Projekt.quizzbackend.Dao.DTO;

import com.Projekt.quizzbackend.Dao.DTO.Templates.ScoreTeamDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.TeamDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.UserToTeamListDTO;
import com.Projekt.quizzbackend.Team.ScoresTeam;
import com.Projekt.quizzbackend.Team.Teams;
import com.Projekt.quizzbackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamMapper {
    @Autowired
    private UserMapper userMapper;
    //erfordert ein true für die Ausgabe Team + score, bei einem false nur Team

    public TeamDTO entityToDTO(Teams team, boolean includeScoreTeam) {
        TeamDTO dto = new TeamDTO();
        dto.setTeamsId(team.getTeamsId());
        dto.setName(team.getName());
        dto.setStudiengang(team.getStudiengang());
        dto.setAdminUserId(team.getAdmin().getUserID());
        dto.setAdminUsername(team.getAdmin().getUserName());
        dto.setMembers(userMapper.convertToTeamDTO(team.getMembers()));


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

        List<User> members = team.getMembers();
        List<UserToTeamListDTO> memberDTOs = new ArrayList<>();
        for(User member : members) {
            UserToTeamListDTO memberDTO = new UserToTeamListDTO();
            memberDTO.setUserName(member.getUserName());
            memberDTO.setUserID(member.getUserID());
            memberDTO.setCourseOfStudy(member.getCourseOfStudy());
            if (dto.getAdminUsername().equals(member.getUserName()))
            {
                memberDTO.setAdmin_team(true);
            }
            // ... (setzen Sie alle anderen Eigenschaften)
            memberDTOs.add(memberDTO);
        }
        dto.setMembers(memberDTOs);

        return dto;
    }


    public List<TeamDTO> convertToDTO(Iterable<Teams> teams) {
        List<TeamDTO> dtos = new ArrayList<>();
        for (Teams team : teams) {
            dtos.add(entityToDTO(team, true));
        }
        return dtos;
    }


}
