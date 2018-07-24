package com.freshworks.virtualdoctor.controller;

import com.freshworks.virtualdoctor.exception.AppException;
import com.freshworks.virtualdoctor.model.Doctor;
import com.freshworks.virtualdoctor.model.Role;
import com.freshworks.virtualdoctor.model.RoleName;
import com.freshworks.virtualdoctor.model.User;
import com.freshworks.virtualdoctor.payload.ApiResponse;
import com.freshworks.virtualdoctor.payload.CreateDoctorRequest;
import com.freshworks.virtualdoctor.repository.DoctorRepository;
import com.freshworks.virtualdoctor.repository.RoleRepository;
import com.freshworks.virtualdoctor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DashBoardController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("categories")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?>  getDoctorsCategory() {
        List<String> list = doctorRepository.findDistinctCategory();
//        HashMap<Integer,String> hm = new HashMap<>();
//        int i=0;
//        for(String s: list){
//            hm.put(i++,s);
//        }
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("category",list);
        return new ResponseEntity<>(hm,HttpStatus.ACCEPTED);
    }

    @PostMapping("dashboard/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createDoctor(@Valid @RequestBody CreateDoctorRequest createDoctorRequest){
        HashMap<String,ApiResponse> hm = new HashMap<>();
        if(userRepository.existsByUsername(createDoctorRequest.getUsername())) {

            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(createDoctorRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        //Creating Doctor
        Doctor doctor = new Doctor(createDoctorRequest.getName(),createDoctorRequest.getUsername(),createDoctorRequest.getEmail(),createDoctorRequest.getCategory());

        User user = new User(createDoctorRequest.getName(), createDoctorRequest.getUsername(),
                createDoctorRequest.getEmail(), "doctor@1");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_DOCTOR)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User userResult = userRepository.save(user);
        Doctor doctorResult = doctorRepository.save(doctor);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(userResult.getUsername()).toUri();
        hm.put("signup",new ApiResponse(true, "Doctor registered successfully"));
        return  ResponseEntity.created(location).body(hm);
    }
}

