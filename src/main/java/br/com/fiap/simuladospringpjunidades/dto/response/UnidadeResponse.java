package br.com.fiap.simuladospringpjunidades.dto.response;

import br.com.fiap.simuladospringpjunidades.dto.request.AbstractRequest;
import br.com.fiap.simuladospringpjunidades.entity.Unidade;
import lombok.Builder;

@Builder
public record UnidadeResponse(

        Long id,
        String nome,
        String sigla,
        String descricao,
        UnidadeResponse macro

) {
}
