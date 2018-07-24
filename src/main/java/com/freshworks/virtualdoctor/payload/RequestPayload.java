package com.freshworks.virtualdoctor.payload;

import java.util.HashMap;

public class RequestPayload {

    private HashMap<String,SignUpRequest> signup;

    private HashMap<String,LoginRequest> login;

    private int id;

    public HashMap<String, SignUpRequest> getSignup() {
        return signup;
    }

    public void setSignup(HashMap<String, SignUpRequest> signup) {
        this.signup = signup;
    }

    public HashMap<String, LoginRequest> getLogin() {
        return login;
    }

    public void setLogin(HashMap<String, LoginRequest> login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
