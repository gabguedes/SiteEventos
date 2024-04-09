package br.com.fiap.evento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@EqualsAndHashCode(exclude = {"nome", "estado"})
@Entity
@Table(name = "tb_cidade")
public class Cidade {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo Requerido.")
    @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres.")
    private String nome;

    @NotBlank(message = "Campo Requerido.")
    @Size(min = 3, message = "O estado deve ter pelo menos 3 caracteres.")
    private String estado;

    @OneToMany(mappedBy = "cidade")
    private List<Evento> eventos = new ArrayList<>();

}
