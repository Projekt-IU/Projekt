package com.Projekt.quizzbackend.Dao.DTO;

import com.Projekt.quizzbackend.Dao.DTO.Templates.FrageErstellen;
import com.Projekt.quizzbackend.Quiz.Fragen;

public class FragenMapper {
 // Konvertiert ein Fragen-Objekt in ein FragenDTO-Objekt
    public static FrageErstellen toDTO(Fragen fragen) {
        FrageErstellen fragenDTO = new FrageErstellen();
        fragenDTO.setFrage(fragen.getFrage());
        fragenDTO.setModul(fragen.getModul());
        fragenDTO.setAntwortEins(fragen.getAntwortEins());
        fragenDTO.setAntwortZwei(fragen.getAntwortZwei());
        fragenDTO.setAntwortDrei(fragen.getAntwortDrei());
        fragenDTO.setAntwortVier(fragen.getAntwortVier());
        fragenDTO.setRichtigeAntwort(fragen.getRichtigeAntwort());
        fragenDTO.setUser(fragen.getUser());  // Hier setzen wir den ganzen User, eventuell möchten Sie nur die ID oder den Benutzernamen setzen
        return fragenDTO;
    }

    // Konvertiert ein FragenDTO-Objekt in ein Fragen-Objekt
    public static Fragen toEntity(FrageErstellen fragenDTO) {
        Fragen fragen = new Fragen();
        fragen.setFrage(fragenDTO.getFrage());
        fragen.setModul(fragenDTO.getModul());
        fragen.setAntwortEins(fragenDTO.getAntwortEins());
        fragen.setAntwortZwei(fragenDTO.getAntwortZwei());
        fragen.setAntwortDrei(fragenDTO.getAntwortDrei());
        fragen.setAntwortVier(fragenDTO.getAntwortVier());
        fragen.setRichtigeAntwort(fragenDTO.getRichtigeAntwort());
        fragen.setUser(fragenDTO.getUser());  // Hier setzen wir den ganzen User, eventuell möchten Sie nur die ID oder den Benutzernamen setzen
        return fragen;
    }
}
