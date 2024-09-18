package br.com.fiap.locaweb_email.service;

import org.springframework.data.domain.Example;

import java.util.List;



public interface ServiceDTO <Entity, Request, Response>{

    Entity toEntity (Request r);

    Response toResponse (Entity e);

    List<Entity> findAll();

    Entity findById(Long id);

    Entity save(Entity e);
}
