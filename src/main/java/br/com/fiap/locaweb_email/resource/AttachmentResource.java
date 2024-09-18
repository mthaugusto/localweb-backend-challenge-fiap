package br.com.fiap.locaweb_email.resource;

import br.com.fiap.locaweb_email.dto.request.AttachmentRequest;
import br.com.fiap.locaweb_email.dto.response.AttachmentResponse;
import br.com.fiap.locaweb_email.service.AttachmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping(value = "/attachments")
public class AttachmentResource {

    @Autowired
    private AttachmentService service;

    @GetMapping
    public ResponseEntity<Collection<AttachmentResponse>> findAll() {
        var entities = service.findAll();

        if (entities.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = entities.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AttachmentResponse> findById(@PathVariable final Long id) {
        var entity = service.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<AttachmentResponse> save(@RequestBody @Valid AttachmentRequest dto) {
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
