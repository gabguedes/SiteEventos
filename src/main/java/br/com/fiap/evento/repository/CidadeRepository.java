package br.com.fiap.evento.repository;

import br.com.fiap.evento.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
