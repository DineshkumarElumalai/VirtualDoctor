package com.freshworks.virtualdoctor.payload;


import javax.validation.constraints.NotBlank;

public class DashBoardPatientRequest {
    @NotBlank
    private String usernameOrEmail;


}
