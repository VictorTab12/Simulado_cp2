package br.com.fiap.simuladospringpjunidades.dto.response;

import br.com.fiap.simuladospringpjunidades.entity.Unidade;
import br.com.fiap.simuladospringpjunidades.entity.Usuario;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChefeResponse(

        Long id,
        Boolean substituto,
        UsuarioResponse usuario,
        UnidadeResponse unidade,
        LocalDateTime inicio,
        LocalDateTime fim
) {
}
