package br.com.fiap.locaweb_email.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Email {

    private Long id;
    private String subject;
    private String body;
    private boolean isRead = false;
    private boolean isArchived = false;
    private LocalDateTime sentAt;
    private LocalDateTime receivedAt;
    private List<Attachment> attachments;
    private User sender;
    private User recipient;

}
