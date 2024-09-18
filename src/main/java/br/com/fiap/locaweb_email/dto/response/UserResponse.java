package br.com.fiap.locaweb_email.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record UserResponse(
        Long id,
        String name,
        String email,
        Boolean active,
        List<FolderResponse> folders
) {}
