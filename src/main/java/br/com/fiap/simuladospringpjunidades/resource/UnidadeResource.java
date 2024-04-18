package br.com.fiap.simuladospringpjunidades.resource;


import br.com.fiap.simuladospringpjunidades.dto.request.UnidadeRequest;
import br.com.fiap.simuladospringpjunidades.dto.response.UnidadeResponse;
import br.com.fiap.simuladospringpjunidades.entity.Unidade;
import br.com.fiap.simuladospringpjunidades.service.UnidadeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping(value = "/unidade")
public class UnidadeResource implements ResourceDTO<UnidadeRequest, UnidadeResponse>{

    @Autowired
    private UnidadeService service;

    @GetMapping
    public ResponseEntity<Collection<UnidadeResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "sigla", required = false) String sigla,
            @RequestParam(name = "descricao", required = false) String descricao
    ) {
        var unidade = Unidade.builder()
                .nome(nome)
                .sigla(sigla)
                .descricao(descricao)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Unidade> example = Example.of(unidade, matcher);

        var encontrados = service.findAll(example);
        var resposta = encontrados.stream()
                .map(service::toResponse)
                .toList();
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UnidadeResponse> findById(@PathVariable Long id) {
        var encontrado = service.findById(id);
        var resposta = service.toResponse(encontrado);
        return ResponseEntity.ok(resposta);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<UnidadeResponse> save(@RequestBody @Valid UnidadeRequest r) {
        var entity = service.toEntity(r);
        var saved = service.save(entity);
        var resposta = service.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(resposta);
    }
}
