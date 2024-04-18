package br.com.fiap.simuladospringpjunidades.resource;

import br.com.fiap.simuladospringpjunidades.dto.request.ChefeRequest;
import br.com.fiap.simuladospringpjunidades.dto.response.ChefeResponse;
import br.com.fiap.simuladospringpjunidades.entity.Chefe;
import br.com.fiap.simuladospringpjunidades.repository.UnidadeRepository;
import br.com.fiap.simuladospringpjunidades.repository.UsuarioRepository;
import br.com.fiap.simuladospringpjunidades.service.ChefeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequestMapping(value = "/chefe")
public class ChefeResource implements ResourceDTO<ChefeRequest, ChefeResponse>{

    @Autowired
    private ChefeService service;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @GetMapping
    public ResponseEntity<Collection<ChefeResponse>> findAll(
            @RequestParam(name = "substituto", required = false) Boolean substituto,
            @RequestParam(name = "inicio", required = false) LocalDateTime inicio,
            @RequestParam(name = "fim", required = false) LocalDateTime fim
    ) {
        var chefe = Chefe.builder()
                .substituto( substituto )
                .inicio( inicio )
                .fim( fim )
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Chefe> example = Example.of( chefe, matcher );

        var encontrados = service.findAll( example );
        var resposta = encontrados.stream()
                .map( service::toResponse )
                .toList();
        return ResponseEntity.ok( resposta );
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ChefeResponse> findById(@PathVariable Long id) {
        var encontrado = service.findById( id );
        var resposta = service.toResponse( encontrado );
        return ResponseEntity.ok( resposta );
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<ChefeResponse> save(@RequestBody @Valid ChefeRequest r) {
        var entity = service.toEntity( r );
        var saved = service.save( entity );
        var resposta = service.toResponse( saved );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created( uri ).body( resposta );
    }



}