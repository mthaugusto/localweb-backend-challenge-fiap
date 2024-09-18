package br.com.fiap.locaweb_email.resource;

import br.com.fiap.locaweb_email.dto.request.FolderRequest;
import br.com.fiap.locaweb_email.dto.response.FolderResponse;
import br.com.fiap.locaweb_email.service.FolderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/folders")
public class FolderResource {

    @Autowired
    private FolderService service;

    @GetMapping
    public ResponseEntity<Collection<FolderResponse>> findAll() {
        var entity = service.findAll();

        if (Objects.isNull(entity) || entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = entity.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FolderResponse> findById(@PathVariable final Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) {
            return ResponseEntity.notFound().build();
        }
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<FolderResponse> save(@RequestBody @Valid FolderRequest dto) {
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
