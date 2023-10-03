package com.Projekt.quizzbackend.Dao.DTO;

import com.Projekt.quizzbackend.Dao.DTO.Templates.UserDTO;
import com.Projekt.quizzbackend.User.User;
import org.springframework.context.annotation.Bean;

public class UserMapper {
@Bean
    public static UserDTO entityToDto(User entity) {
        UserDTO dto = new UserDTO();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setUserName(entity.getUserName());
        dto.setMatrikelNr(entity.getMatrikelNr());
        dto.setCourseOfStudy(entity.getCourseOfStudy());
        dto.setEmail(entity.getEmail());
        dto.setRole(entity.getRole());
        dto.setFullAccess();
        dto.setDateOfRegistration(entity.getDateOfRegistration());
        dto.setUserID(entity.getUserID());
        return dto;
    }
}
