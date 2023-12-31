package com.Projekt.quizzbackend.User.Login;

public class FilterLogin {

    public static AuthRequest filterLogin(AuthRequest authRequest) {
        if (authRequest == null) {
            return null; // Wenn das Eingabeobjekt null ist, gebe null zurück
        }

        // Filtere ungewöhnliche Zeichen aus den Anmeldedaten
        String filteredUserName = filterString(authRequest.getUsername());
        String filteredPassword = filterPassword(authRequest.getPassword());
        Integer anfrageId = authRequest.getAnfrageId();
        String anfrageName =authRequest.getAnfrageName();
        // Wenn ungewöhnliche Zeichen gefunden wurden, gebe null zurück
        if (filteredUserName == null || filteredPassword == null) {
            return null;
        }

        // Erstelle ein neues LoginRequest-Objekt mit den gefilterten Daten
        AuthRequest filteredLogin = new AuthRequest();
        filteredLogin.setUsername(filteredUserName);
        filteredLogin.setPassword(filteredPassword);
        filteredLogin.setAnfrageId(anfrageId);
        filteredLogin.setAnfrageName(anfrageName);
        return filteredLogin;
    }

    private static String filterString(String input) {
        if (input == null) {
            return null;
        }

        // Erlaubt alphanumerische Zeichen, deutsche Sonderzeichen, Leerzeichen und das "@"-Zeichen
        if (!input.matches("^[a-zA-Z0-9äöüÄÖÜß\\s@]*$")) {
            System.out.println("illegales zeichen gefunden " + input);
            return null;
        }

        return input;
    }

    private static String filterPassword(String input) {
        if (input == null) {
            return null;
        }

        // Erlaubt alphanumerische Zeichen, deutsche Sonderzeichen, einige spezielle Zeichen und das "@"-Zeichen
        if (!input.matches("^[a-zA-Z0-9äöüÄÖÜß\\s!?@+=]*$")) {
            System.out.println("illegales zeichen gefunden " + input);
            return null;
        }
        System.out.println("FilterPW: Alles i.O. " + input);
        return input;
    }
}
