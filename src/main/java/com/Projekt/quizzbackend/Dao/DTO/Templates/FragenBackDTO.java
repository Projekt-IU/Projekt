package com.Projekt.quizzbackend.Dao.DTO.Templates;

import com.Projekt.quizzbackend.User.Login.AuthRequest;

public class FragenBackDTO extends AuthRequest{

        private Integer fragenId;
        private String antwort;
        private String richtigeAntwort;

        public Integer getFragenId() {
                return fragenId;
        }

        public void setFragenId(Integer fragenId) {
                this.fragenId = fragenId;
        }

        public String getAntwort() {
                return antwort;
        }

        public void setAntwort(String antwort) {
                this.antwort = antwort;
        }

        public String getRichtigeAntwort() {
                return richtigeAntwort;
        }

        public void setRichtigeAntwort(String richtigeAntwort) {
                this.richtigeAntwort = richtigeAntwort;
        }


        // Getter und Setter


}
