package br.com.uniamerica.api.services;

import br.com.uniamerica.api.entity.Convenio;
import br.com.uniamerica.api.entity.Historico;
import br.com.uniamerica.api.repository.ConvenioRepository;
import br.com.uniamerica.api.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    public Optional<Historico> findById(Long id){
        return this. historicoRepository.findById(id);
    }

    public Page<Historico> listAll(Pageable pageable){
        return this. historicoRepository.findAll(pageable);
    }

    @Transactional
    public void update(Long id, Historico  historico){
        if (id ==  historico.getId()) {
            this. historicoRepository.save( historico);
        }
        else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void insert(Historico historico){
        this. historicoRepository.save(historico);
    }

    @Transactional
    public void updateStatus(Long id, Historico historico){
        if (id ==  historico.getId()) {
            LocalDateTime data = LocalDateTime.now();
            this. historicoRepository.updateStatus(historico.getId(), data);
        }
        else {
            throw new RuntimeException();
        }
    }
}
