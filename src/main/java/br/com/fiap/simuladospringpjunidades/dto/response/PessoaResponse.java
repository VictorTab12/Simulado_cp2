package br.com.fiap.simuladospringpjunidades.dto.response;

import br.com.fiap.simuladospringpjunidades.entity.Tipo;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PessoaResponse(
        Long id,
        String nome,
        String sobrenome,
        String email,
        LocalDate nascimento,
        Tipo tipo
) {
}
