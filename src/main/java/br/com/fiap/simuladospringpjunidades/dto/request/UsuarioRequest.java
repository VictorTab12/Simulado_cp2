package br.com.fiap.simuladospringpjunidades.dto.request;

import br.com.fiap.simuladospringpjunidades.entity.Pessoa;

public record UsuarioRequest(

        String username,

        String password,

        PessoaRequest pessoa

) {
}
