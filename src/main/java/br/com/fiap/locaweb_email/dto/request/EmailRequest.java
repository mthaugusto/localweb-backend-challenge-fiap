package br.com.fiap.locaweb_email.dto.request;

import java.time.LocalDateTime;


public record EmailRequest(
        String subject,
        String body,
        LocalDateTime sentAt,
        LocalDateTime receivedAt,
        Long senderId,
        Long recipientId
) {}