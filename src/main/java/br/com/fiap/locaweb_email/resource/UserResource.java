package br.com.fiap.locaweb_email.resource;

import br.com.fiap.locaweb_email.dto.request.UserRequest;
import br.com.fiap.locaweb_email.dto.response.UserResponse;
import br.com.fiap.locaweb_email.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Collection<UserResponse>> findAll() {
        var entity = service.findAll();

        if (Objects.isNull(entity) || entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = entity.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable final Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) {
            return ResponseEntity.notFound().build();
        }
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest dto) {
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
