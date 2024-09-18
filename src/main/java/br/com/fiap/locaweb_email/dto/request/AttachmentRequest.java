package br.com.fiap.locaweb_email.dto.request;

public record AttachmentRequest(
        String fileName,
        String fileType,
        byte[] data,
        Long emailId
) {}
