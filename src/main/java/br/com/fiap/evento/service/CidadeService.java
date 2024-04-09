package br.com.fiap.evento.service;

import br.com.fiap.evento.model.Cidade;
import br.com.fiap.evento.repository.CidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository repository;

    @Transactional(readOnly = true)
    public List<Cidade> findAll(){
        return repository.findAll();
    }

    public Cidade insert(Cidade cidade){
        return repository.save(cidade);
    }

    @Transactional(readOnly = true)
    public Cidade findById(Long id){

        return repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso Inválido - " + id)
        );
    }

    @Transactional
    public Cidade update(Long id, Cidade entity){
        try {
            Cidade cidade = repository.getReferenceById(id);
            copyToCidade(entity, cidade);
            cidade = repository.save(cidade);
            return cidade;
        }catch (EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrado.");
        }
    }

    private void copyToCidade(Cidade entity, Cidade cidade) {
        cidade.setNome(entity.getNome());
        cidade.setEstado(entity.getEstado());
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
