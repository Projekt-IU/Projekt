package com.Projekt.quizzbackend.Dao.DTO;

import com.Projekt.quizzbackend.Dao.DTO.Templates.UserDTO;
import com.Projekt.quizzbackend.Dao.DTO.Templates.UserToTeamListDTO;
import com.Projekt.quizzbackend.User.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    }



