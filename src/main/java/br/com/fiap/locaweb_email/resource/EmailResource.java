package br.com.fiap.locaweb_email.resource;

import br.com.fiap.locaweb_email.dto.request.EmailRequest;
import br.com.fiap.locaweb_email.dto.response.EmailResponse;
import br.com.fiap.locaweb_email.entity.Email;
import br.com.fiap.locaweb_email.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/emails")
public class EmailResource {

    @Autowired
    private EmailService service;

    @GetMapping
    public ResponseEntity<Collection<EmailResponse>> findAll() {
        var entity = service.findAll();

        if (Objects.isNull(entity) || entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = entity.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmailResponse> findById(@PathVariable final Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) {
            return ResponseEntity.notFound().build();
        }
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<EmailResponse> save(@RequestBody @Valid EmailRequest dto) {
        var entity = service.toEntity(dto);
        var saved = service.save(entity);
        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        var response = service.toResponse(saved);
        return ResponseEntity.created(uri).body(response);
    }
}
