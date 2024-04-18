package br.com.fiap.simuladospringpjunidades.dto.request;

import br.com.fiap.simuladospringpjunidades.entity.Tipo;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record PessoaRequest(
        String nome,

        String sobrenome,

        String email,
        @PastOrPresent
        LocalDate nascimento,

        Tipo tipo

) {
}
