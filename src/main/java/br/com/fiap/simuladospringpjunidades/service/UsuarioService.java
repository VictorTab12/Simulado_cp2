package br.com.fiap.simuladospringpjunidades.service;
import br.com.fiap.simuladospringpjunidades.dto.request.UsuarioRequest;
import br.com.fiap.simuladospringpjunidades.dto.response.UsuarioResponse;
import br.com.fiap.simuladospringpjunidades.entity.Usuario;
import br.com.fiap.simuladospringpjunidades.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService implements ServiceDTO<Usuario, UsuarioRequest, UsuarioResponse>{

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private PessoaService pessoaService;

    @Override
    public Usuario toEntity(UsuarioRequest usuarioRequest){

        if(Objects.isNull(usuarioRequest)) return null;

        var pessoa = pessoaService.toEntity(usuarioRequest.pessoa());

        return Usuario.builder()
                .username(usuarioRequest.username())
                .password(usuarioRequest.password())
                .pessoa(pessoa)
                .build();
    }

    @Override
    public UsuarioResponse toResponse(Usuario usuario){
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .pessoa(pessoaService.toResponse(usuario.getPessoa()))
                .build();
    }

    @Override
    public Usuario findById(Long id){
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Usuario> findAll(Example<Usuario> example){
        return repo.findAll(example);
    }

    @Override
    public List<Usuario> findAll(){
        return repo.findAll();
    }

    @Override
    public Usuario save(Usuario entity){
        return repo.save(entity);
    }

}
