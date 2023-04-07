package com.jankes.tendersApp.security;

import com.jankes.tendersApp.tenders.TenderDto;

public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public static Builder builder() {return new Builder();}

    public UserDto(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
    }

    public static class Builder {

        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String role;

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRole(String role){
            this.role = role;
            return this;
        }

        public UserDto build() {return new UserDto(this);}
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
