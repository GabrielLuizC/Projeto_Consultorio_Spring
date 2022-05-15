package br.com.uniamerica.api.services;

import br.com.uniamerica.api.entity.Paciente;
import br.com.uniamerica.api.entity.TipoAtendimento;
import br.com.uniamerica.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Optional<Paciente> findById(Long id){
        return this.pacienteRepository.findById(id);
    }

    public Page<Paciente> listAll(Pageable pageable){
        return this.pacienteRepository.findAll(pageable);
    }

    @Transactional
    public void update(Long id, Paciente paciente){
        if (id == paciente.getId()) {
            verifica(paciente);
            this.pacienteRepository.save(paciente);
        }
        else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void insert(Paciente paciente){
        this.pacienteRepository.save(paciente);
    }

    @Transactional
    public void updateStatus(Long id, Paciente paciente){
        if (id == paciente.getId()) {
            LocalDateTime data = LocalDateTime.now();
            this.pacienteRepository.updateStatus(paciente.getId(), data);
        }
        else {
            throw new RuntimeException();
        }
    }

    public void verifica(Paciente paciente){
        if(paciente.getId() == null){
            throw new RuntimeException("Paciente NULO");
        }
        if(paciente.getTipoAtendimento().equals(TipoAtendimento.convenio)){
            if(paciente.getNumeroCartaoConvenio() == null){
                throw new RuntimeException("Numero Cartao Convenio NULO");
            }
            if(paciente.getDataVencimento() == null){
                throw new RuntimeException("Data Vencimento NULO");
            }
            if(paciente.getDataVencimento().compareTo(LocalDateTime.now()) < 0){
                throw new RuntimeException("Data Vencimento Atingida");
            }
        }
        if(paciente.getTipoAtendimento().equals(TipoAtendimento.particular)){
            paciente.setConvenio(null);
            paciente.setNumeroCartaoConvenio(null);
            paciente.setDataVencimento(null);
        }
    }
}
