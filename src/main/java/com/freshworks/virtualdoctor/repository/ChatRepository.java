package com.freshworks.virtualdoctor.repository;

import com.freshworks.virtualdoctor.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChatRepository  extends JpaRepository<Message, Long> {
    List<Message> findByCategory(String category);

}
