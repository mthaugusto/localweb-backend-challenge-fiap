package br.com.fiap.locaweb_email.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record FolderResponse(
        Long id,
        String name,
        UserResponse user,
        List<EmailResponse> emails
) {}
