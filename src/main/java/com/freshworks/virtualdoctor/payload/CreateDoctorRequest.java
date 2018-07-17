package com.freshworks.virtualdoctor.payload;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateDoctorRequest {
    @NotBlank
    @Size(min = 4, max = 40)
    private String name;

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String category;

    public CreateDoctorRequest() {
    }

    public CreateDoctorRequest(@NotBlank @Size(min = 4, max = 40) String name, @NotBlank @Size(min = 3, max = 15) String username, @NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(min = 6, max = 20) String category) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
