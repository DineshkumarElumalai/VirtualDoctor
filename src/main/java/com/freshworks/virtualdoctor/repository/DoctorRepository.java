package com.freshworks.virtualdoctor.repository;

import com.freshworks.virtualdoctor.model.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByUsername(String username);
//    List<?> findDistinctCategory();
    List<Doctor> findAll();

    @Query(value = "SELECT DISTINCT category FROM doctors", nativeQuery = true)
    List<String> findDistinctCategory();

}
