package org.selenium.pom.bodies;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequestBody {
        @JsonProperty("email")
        private String email;
        @JsonProperty("password")
        private String password;
        @JsonProperty("type")
        private String type;
        @JsonProperty("source")
        private String source;

        // Constructor vacío necesario para la deserialización JSON
        public LoginRequestBody() {
        }

        // Constructor con parámetros
        public LoginRequestBody(String email, String password, String type, String source) {
                this.email = email;
                this.password = password;
                this.type = type;
                this.source = source;
        }

        // Getters y setters
        public String getEmail() {
                return email;
        }
        public void setEmail(String email) {
                this.email = email;
        }
        public String getPassword() {
                return password;
        }
        public void setPassword(String password) {
                this.password = password;
        }
        public String getType() {
                return type;
        }
        public void setType(String type) {
                this.type = type;
        }
        public String getSource() {
                return source;
        }
        public void setSource(String source) {
                this.source = source;
        }
}
