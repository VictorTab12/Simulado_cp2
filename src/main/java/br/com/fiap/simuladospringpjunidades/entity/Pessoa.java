package br.com.fiap.simuladospringpjunidades.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_PESSOA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMAIL", columnNames = "EMAIL_PESSOA")})
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_PESSOA")
    @SequenceGenerator(name = "SQ_PESSOA", sequenceName = "SQ_PESSOA", allocationSize = 1)

    @Column(name = "ID_PESSOA")
    private Long id;

    @Column(name = "NOME_PESSOA")
    private String nome;

    @Column(name = "SOBRENOME_PESSOA")
    private String sobrenome;

    @Column(name = "EMAIL_PESSOA")
    private String email;

    @Column(name = "NASCIMENTO_PESSOA")
    private LocalDate nascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_PESSOA", nullable = false)
    private Tipo tipo;
}
