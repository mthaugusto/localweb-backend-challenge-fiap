package br.com.fiap.locaweb_email.dto.request;

public record UserRequest(
        String name,
        String email,
        String password,
        Boolean active
) {}
