package br.com.fiap.locaweb_email.dto.response;

import lombok.Builder;

@Builder
public record AttachmentResponse(
        Long id,
        String fileName,
        String fileType
) {}
