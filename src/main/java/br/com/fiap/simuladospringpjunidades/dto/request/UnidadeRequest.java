package br.com.fiap.simuladospringpjunidades.dto.request;

import br.com.fiap.simuladospringpjunidades.entity.Unidade;

public record UnidadeRequest(

        String nome,

        String sigla,

        String descricao,

        AbstractRequest macro
) {
}
