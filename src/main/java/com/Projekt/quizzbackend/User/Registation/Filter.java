package com.Projekt.quizzbackend.User.Registation;

import com.Projekt.quizzbackend.User.User;

public class Filter {

    public static User filterUser(User user) {
        if (user == null) {
            return null; // Wenn das Eingabeobjekt null ist, gebe null zurück
        }

        // Filtere ungewöhnliche Zeichen aus den Benutzerdaten
        String filteredFirstName = filterString(user.getFirstName());
        String filteredLastName = filterString(user.getLastName());
        String filteredUserName = filterString(user.getUserName());
        String filteredEmail = filterEmail(user.getEmail());
        String filteredPassword = filterPassword(user.getPassword());
        String filteredCourseOfStudy = filterString(user.getCourseOfStudy());

        // Wenn ungewöhnliche Zeichen gefunden wurden, gebe null zurück
        if (filteredFirstName == null || filteredLastName == null ||
                filteredUserName == null || filteredEmail == null ||
                filteredPassword == null || filteredCourseOfStudy == null) {
            return null;
        }

        // Erstelle ein neues User-Objekt mit den gefilterten Daten
        User filteredUser = new User();
        filteredUser.setFirstName(filteredFirstName);
        filteredUser.setLastName(filteredLastName);
        filteredUser.setUserName(filteredUserName);
        filteredUser.setEmail(filteredEmail);
        filteredUser.setPassword(filteredPassword);
        filteredUser.setCourseOfStudy(filteredCourseOfStudy);
        filteredUser.setMatrikelNr(user.getMatrikelNr());

        return filteredUser;
    }

    private static String filterString(String input) {
        if (input == null) {
            return null;
        }
        // Erlaubt alphanumerische Zeichen, deutsche Sonderzeichen und Leerzeichen
        if (!input.matches("^[a-zA-Z0-9äöüÄÖÜß\\s]*$")) {
            System.out.println("Filter: illegales zeichen gefunden " + input);
            return null;
        }
        return input;
    }

    private static String filterPassword(String input) {
        if (input == null) {
            return null;
        }
        // Erlaubt alphanumerische Zeichen, deutsche Sonderzeichen und einige spezielle Zeichen
        if (!input.matches("^[a-zA-Z0-9äöüÄÖÜß\\s!?]*$")) {
            System.out.println("Filter: illegales zeichen gefunden " + input);
            return null;
        }
        return input;
    }

    private static String filterEmail(String input) {
        if (input == null) {
            return null;
        }
        // Dieser Regex prüft auf das Vorhandensein des "@"-Zeichens und erlaubt alphanumerische Zeichen,
        // deutsche Sonderzeichen, Punkte und Bindestriche in der E-Mail-Adresse.
        if (!input.matches("^[a-zA-Z0-9äöüÄÖÜß._-]+@[a-zA-Z0-9äöüÄÖÜß.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("Filter: illegale Zeichen in der E-Mail gefunden " + input);
            return null;
        }
        return input;
    }
}
