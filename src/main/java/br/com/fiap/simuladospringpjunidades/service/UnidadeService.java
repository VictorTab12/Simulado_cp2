package br.com.fiap.simuladospringpjunidades.service;

import br.com.fiap.simuladospringpjunidades.dto.request.UnidadeRequest;
import br.com.fiap.simuladospringpjunidades.dto.response.UnidadeResponse;
import br.com.fiap.simuladospringpjunidades.entity.Unidade;
import br.com.fiap.simuladospringpjunidades.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UnidadeService implements ServiceDTO<Unidade, UnidadeRequest, UnidadeResponse>{

    @Autowired
    private UnidadeRepository repo;

    @Override
    public Unidade toEntity(UnidadeRequest unidadeRequest){

        if(Objects.isNull(unidadeRequest)) return null;

        var macro = findById(unidadeRequest.macro().id());

        return Unidade.builder()
                .nome(unidadeRequest.nome())
                .sigla(unidadeRequest.sigla())
                .descricao(unidadeRequest.descricao())
                .macro(macro)
                .build();
    }

    @Override
    public UnidadeResponse toResponse(Unidade unidade){
        return UnidadeResponse.builder()
                .id(unidade.getId())
                .nome(unidade.getNome())
                .sigla(unidade.getSigla())
                .descricao(unidade.getDescricao())
                .macro(this.toResponse(unidade.getMacro()))
                .build();
    }

    @Override
    public Unidade findById(Long id){
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Unidade> findAll(Example<Unidade> example){
        return repo.findAll(example);
    }

    @Override
    public List<Unidade> findAll(){
        return repo.findAll();
    }

    @Override
    public Unidade save(Unidade entity){
        return repo.save(entity);
    }
}
