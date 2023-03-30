package com.pvkhai.gearpandabackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private Long age;
    private String address;

    public UserUpdateDTO(String firstName, String lastName, String gender, String email, Long age, String address) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.age = age;
        this.address = address;
    }

}
