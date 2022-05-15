package br.com.uniamerica.api.services;

import br.com.uniamerica.api.entity.Agenda;
import br.com.uniamerica.api.entity.Paciente;
import br.com.uniamerica.api.entity.StatusAgenda;
import br.com.uniamerica.api.entity.TipoAtendimento;
import br.com.uniamerica.api.repository.AgendaRepository;
import br.com.uniamerica.api.repository.PacienteRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public Optional<Agenda> findById(Long id){
        return this.agendaRepository.findById(id);
    }

    public Page<Agenda> listAll(Pageable pageable){
        return this.agendaRepository.findAll(pageable);
    }

    @Transactional
    public void update(Long id, Agenda agenda){
        if (id == agenda.getId()) {
            verifica(agenda);
            this.agendaRepository.save(agenda);
        }
        else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void insert(Agenda agenda){
        this.agendaRepository.save(agenda);
    }

    @Transactional
    public void updateStatus(Long id, Agenda agenda){
        if (id == agenda.getId()) {
            LocalDateTime data = LocalDateTime.now();
            this.agendaRepository.updateStatus(agenda.getId(), data);
        }
        else {
            throw new RuntimeException();
        }
    }

    public void verifica(Agenda agenda){
        if(agenda.getStatusAgenda().equals(StatusAgenda.pendente)){
            if(agenda.getDataAgenda().compareTo(LocalDateTime.now()) < 0){
                throw new RuntimeException("ERROR: DATA NO PASSADO");
            }
        }

        if(agenda.getStatusAgenda().equals(StatusAgenda.compareceu)
                ||
                agenda.getStatusAgenda().equals(StatusAgenda.nao_compareceu)){
            if(agenda.getDataAgenda().compareTo(LocalDateTime.now()) > 0){
                throw new RuntimeException("ERROR: DATA NO FUTURO");
            }
        }

        if(agenda.getDataAgenda().compareTo(LocalDateTime.now()) < 0){
            throw new RuntimeException("ERROR: DATA NO PASSADO");
        }

        if(agenda.getPaciente().getId() == null){
            throw new RuntimeException("ERROR: PACIENTE NÃO PODE SER NULO");
        }

        if(agenda.getMedico().getId() == null){
            throw new RuntimeException("ERROR: MEDICO NÃO PODE SER NULO");
        }

        if(this.agendaRepository.verificaAgenda(agenda.getDataAgenda()).size() > 0){
            throw new RuntimeException("ERROR: HORARIO INVALIDO, PACIENTE JA CADASTRADO");
        }
    }
}
