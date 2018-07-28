package com.freshworks.virtualdoctor.model;

import com.freshworks.virtualdoctor.model.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "messages")
public class Message extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String sender;

    private Long patientId;

    @NotBlank
    private String content;

    @NotBlank
    private String category;

    private String status;

    public Message() {
    }

    public Message(@NotBlank String sender, Long patientId, @NotBlank String content, @NotBlank String category, String status) {
        this.sender = sender;
        this.patientId = patientId;
        this.content = content;
        this.category = category;
        this.status = status;
    }

    public Message(@NotBlank String sender, Long patientId, @NotBlank String content, @NotBlank String category) {
        this.sender = sender;
        this.patientId = patientId;
        this.content = content;
        this.category = category;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
