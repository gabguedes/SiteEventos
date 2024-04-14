package br.com.fiap.evento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@EqualsAndHashCode(exclude = {"nome", "data", "url"})
@Entity
@Table(name = "tb_evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo Requerido.")
    @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres.")
    private String nome;

    @NotNull(message = "Data requerida.")
    @Future(message = "A data deve ser em um dia futuro.")
//    private String data;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;

    @NotBlank(message = "Campo Requerido.")
    private String url;

    @ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;
}
