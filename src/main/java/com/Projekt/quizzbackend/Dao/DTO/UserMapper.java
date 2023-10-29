package com.Projekt.quizzbackend.Dao.DTO;

import com.Projekt.quizzbackend.Dao.DTO.Templates.UserDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.UserScoreListDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.UserToTeamListDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.UserWithScore;
import com.Projekt.quizzbackend.User.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO entityToDto(User entity) {
        UserDTO dto = new UserDTO();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setUserName(entity.getUserName());
        dto.setMatrikelNr(entity.getMatrikelNr());
        dto.setCourseOfStudy(entity.getCourseOfStudy());
        dto.setEmail(entity.getEmail());
        dto.setRole(entity.getRole());
        if (entity.getTeam() != null) {
            dto.setTeamName(entity.getTeam().getName());
        }
        dto.setDateOfRegistration(entity.getDateOfRegistration());
        dto.setUserID(entity.getUserID());
        return dto;
    }

    public List<UserDTO> convertToDTO(List<User> users) {
        List<UserDTO> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(entityToDto(user));
        }
        return dtos;
    }

    public UserWithScore entityWithScoreToDto(User entity) {
        UserWithScore dto = new UserWithScore();
        UserDTO tempDto = entityToDto(entity);
        BeanUtils.copyProperties(tempDto, dto);
        dto.setScoresId(entity.getScoreUser().getScoresId());
        dto.setPunkteGesamt(entity.getScoreUser().getPunkteGesamt());
        dto.setPunkteMonat(entity.getScoreUser().getPunkteMonat());
        dto.setPunkteWoche(entity.getScoreUser().getPunkteWoche());
        dto.setFrageRichtig(entity.getScoreUser().getFrageRichtig());
        dto.setFragenGesamt(entity.getScoreUser().getFragenGesamt());
        return dto;
    }

    public List<UserWithScore> convertWithScoreToDTO(List<User> users) {
        List<UserWithScore> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(entityWithScoreToDto(user));
        }
        return dtos;
    }




    public UserToTeamListDTO userToTeamDto(User entity) {
            UserToTeamListDTO dto = new UserToTeamListDTO();
            dto.setUserName(entity.getUserName());
            dto.setCourseOfStudy(entity.getCourseOfStudy());

            // dto.setEmail(entity.getEmail());
            dto.setUserID(entity.getUserID());
            return dto;
        }

        public List<UserToTeamListDTO> convertToTeamDTO(List<User> users) {
            List<UserToTeamListDTO> dtos = new ArrayList<>();
            for (User user : users) {
                dtos.add(userToTeamDto(user));
            }
            return dtos;
        }


    public List<UserScoreListDTO> convertUserScoresToDTO(List<User> users, String scoreType) {
        return users.stream()
                .map(user -> {
                    UserScoreListDTO dto = new UserScoreListDTO();
                    dto.setUsername(user.getUserName());

                    switch (scoreType.toLowerCase()) {
                        case "woche":
                            dto.setScoreWoche(user.getScoreUser().getPunkteWoche());
                            break;
                        case "monat":
                            dto.setScoreMonat(user.getScoreUser().getPunkteMonat());
                            break;
                        case "gesamt":
                            dto.setScoreGesamt(user.getScoreUser().getPunkteGesamt());
                            break;
                        case "all":
                            dto.setScoreWoche(user.getScoreUser().getPunkteWoche());
                            dto.setScoreMonat(user.getScoreUser().getPunkteMonat());
                            dto.setScoreGesamt(user.getScoreUser().getPunkteGesamt());
                            break;
                        default:
                            dto.setScoreWoche(user.getScoreUser().getPunkteWoche());
                            dto.setScoreMonat(user.getScoreUser().getPunkteMonat());
                            dto.setScoreGesamt(user.getScoreUser().getPunkteGesamt());
                            break;
                    }
                    dto.setFrageRichtig(user.getScoreUser().getFrageRichtig());
                    dto.setFragenGesamt(user.getScoreUser().getFragenGesamt());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    }



