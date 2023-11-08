package com.Projekt.quizzbackend.Dao;

import com.Projekt.quizzbackend.User.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//User daten werden hier gespeichert oder abgerufen. Datenbankanbindung. Möglicherweise benötigen wir dafür eine Oberklasse wenn wir ein 2. @Repository anfangen für z.b. Quizz fragen
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {


    // List<User> findUserByLoggedInTrue();

    User findUserByUserNameAndPassword(String username, String password);

    User findUserByUserIDAndUserName(int userId, String username);

    User findByUserName(String username);


    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findUserByUserID(int userId);


    User findByRole(String role);
}