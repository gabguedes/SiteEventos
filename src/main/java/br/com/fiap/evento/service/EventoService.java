package br.com.fiap.evento.service;

import br.com.fiap.evento.model.Evento;
import br.com.fiap.evento.model.Evento;
import br.com.fiap.evento.repository.EventoRepository;
import br.com.fiap.evento.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRepository repository;

    @Transactional(readOnly = true)
    public List<Evento> findAll(){
        return repository.findAll();
    }

    public Evento insert(Evento evento){
        return repository.save(evento);
    }

    @Transactional(readOnly = true)
    public Evento findById(Long id){

        return repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso Inválido - " + id)
        );
    }

    @Transactional
    public Evento update(Long id, Evento entity){
        try {
            Evento evento = repository.getReferenceById(id);
            copyToEvento(entity, evento);
            evento = repository.save(evento);
            return evento;
        }catch (EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrado.");
        }
    }

    private void copyToEvento(Evento entity, Evento evento) {
        evento.setNome(entity.getNome());
        evento.setData(entity.getData());
        evento.setUrl(entity.getUrl());
        evento.setCidade(entity.getCidade());
    }

    @Transactional
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Recurso Inválido - " + id);
        }
        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Falha de integridade referencial - id: " + id);
        }
    }
}
