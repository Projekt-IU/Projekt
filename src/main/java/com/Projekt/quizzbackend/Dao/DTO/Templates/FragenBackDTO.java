package com.Projekt.quizzbackend.Dao.DTO.Templates;

import com.Projekt.quizzbackend.User.Login.AuthRequest;

public class FragenBackDTO extends AuthRequest{

        private Integer fragenId;
        private Integer antwort;


        public Integer getFragenId() {
                return fragenId;
        }

        public void setFragenId(Integer fragenId) {
                this.fragenId = fragenId;
        }

        public Integer getAntwort() {
                return antwort;
        }

        public void setAntwort(Integer antwort) {
                this.antwort = antwort;
        }



        // Getter und Setter


}
