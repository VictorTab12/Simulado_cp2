package br.com.fiap.simuladospringpjunidades.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_USUARIO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_PESSOA", columnNames = "PESSOA"),
        @UniqueConstraint(name = "UK_USERNAME", columnNames = "USERNAME_USUARIO")})

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_USUARIO")
    @SequenceGenerator(name = "SQ_USUARIO", sequenceName = "SQ_USUARIO", allocationSize = 1)

    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "USERNAME_USUARIO")
    private String username;

    @Column(name = "PASSWORD_USUARIO")
    private String password;


    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(
                    name = "FK_PESSOA_USUARIO"
            )
    )
    private Pessoa pessoa;

}
