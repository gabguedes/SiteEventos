package br.com.fiap.evento.repository;

import br.com.fiap.evento.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
