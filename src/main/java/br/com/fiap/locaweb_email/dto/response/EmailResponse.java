package br.com.fiap.locaweb_email.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record EmailResponse(
        Long id,
        String subject,
        String body,
        boolean isRead,
        boolean isArchived,
        LocalDateTime sentAt,
        LocalDateTime receivedAt,
        UserResponse sender,
        UserResponse recipient,
        List<AttachmentResponse> attachments
) {}