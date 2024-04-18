package br.com.fiap.simuladospringpjunidades.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_CHEFE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_UNI", columnNames = "UNIDADE")})
public class Chefe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_CHEFE")
    @SequenceGenerator(name = "SQ_CHEFE", sequenceName = "SQ_CHEFE", allocationSize = 1)

    @Column(name = "ID_CHEFE")
    private Long id;

    @Column(name = "SUBSTITUTO_CHEFE")
    private Boolean substituto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "USUARIO",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(
                    name = "FK_USUARIO_CHEFE"
            )
    )
    private Usuario usuario;



    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "UNIDADE",
            referencedColumnName = "ID_UNIDADE",
            foreignKey = @ForeignKey(
                    name = "FK_UNIDADE_CHEFE"
            )
    )
    private Unidade unidade;

    @Column(name = "INICIO_CHEFE")
    private LocalDateTime inicio;

    @Column(name = "FIM_CHEFE")
    private LocalDateTime fim;
}
