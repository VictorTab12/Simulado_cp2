package br.com.fiap.simuladospringpjunidades.service;

import br.com.fiap.simuladospringpjunidades.dto.request.ChefeRequest;
import br.com.fiap.simuladospringpjunidades.dto.response.ChefeResponse;
import br.com.fiap.simuladospringpjunidades.entity.Chefe;
import br.com.fiap.simuladospringpjunidades.repository.ChefeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChefeService implements ServiceDTO<Chefe, ChefeRequest, ChefeResponse>{

    @Autowired
    private ChefeRepository repo;

    @Autowired
    private UnidadeService unidadeService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Chefe toEntity(ChefeRequest chefeRequest) {

        if (Objects.isNull(chefeRequest)) return null;

        var unidade = unidadeService.findById(chefeRequest.unidade().id());

        var usuario = usuarioService.findById(chefeRequest.usuario().id());

        return Chefe.builder()
                .substituto(chefeRequest.substituto())
                .usuario(usuario)
                .unidade(unidade)
                .inicio(chefeRequest.inicio())
                .fim(chefeRequest.fim())
                .build();
    }

    @Override
    public ChefeResponse toResponse(Chefe chefe) {
        return ChefeResponse.builder()
                .id(chefe.getId())
                .substituto(chefe.getSubstituto())
                .usuario(usuarioService.toResponse(chefe.getUsuario()))
                .unidade(unidadeService.toResponse(chefe.getUnidade()))
                .inicio(chefe.getInicio())
                .fim(chefe.getFim())
                .build();
    }

    @Override
    public Chefe findById(Long id){
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Chefe> findAll(Example<Chefe> example){
        return repo.findAll(example);
    }

    @Override
    public List<Chefe> findAll(){
        return repo.findAll();
    }

    @Override
    public Chefe save(Chefe entity){
        return repo.save(entity);
    }

}
