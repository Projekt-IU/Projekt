package com.Projekt.quizzbackend.Dao.DTO.Templates;

public class FragenSendDTO {

        private Integer fragenId;
        private String frage;
        private String antwortEins;
        private String antwortZwei;
        private String antwortDrei;
        private String antwortVier;

        public Integer getFragenId() {
                return fragenId;
        }

        public void setFragenId(Integer fragenId) {
                this.fragenId = fragenId;
        }

        public String getFrage() {
                return frage;
        }

        public void setFrage(String frage) {
                this.frage = frage;
        }

        public String getAntwortEins() {
                return antwortEins;
        }

        public void setAntwortEins(String antwortEins) {
                this.antwortEins = antwortEins;
        }

        public String getAntwortZwei() {
                return antwortZwei;
        }

        public void setAntwortZwei(String antwortZwei) {
                this.antwortZwei = antwortZwei;
        }

        public String getAntwortDrei() {
                return antwortDrei;
        }

        public void setAntwortDrei(String antwortDrei) {
                this.antwortDrei = antwortDrei;
        }

        public String getAntwortVier() {
                return antwortVier;
        }

        public void setAntwortVier(String antwortVier) {
                this.antwortVier = antwortVier;
        }
}
