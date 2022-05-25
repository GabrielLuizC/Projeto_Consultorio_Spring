package br.com.uniamerica.api.services;

import br.com.uniamerica.api.entity.Agenda;
import br.com.uniamerica.api.entity.Secretaria;
import br.com.uniamerica.api.entity.StatusAgenda;
import br.com.uniamerica.api.repository.AgendaRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.TemporalQuery;
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
            verificaEncaixe(agenda);
            verificaDataMaiorNow(agenda);
            verificaDataAteMaiorDe(agenda);
            verificaIntervalo(agenda);
            verificaFuncionamento(agenda);
            verificaMedicoHorario(agenda);
            verificaAgendamentoExistente(agenda);
            this.agendaRepository.save(agenda);
        }
        else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void insert(Agenda agenda, Secretaria secretaria){
        verificaCadastro(secretaria, agenda);
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

    public boolean verificaEncaixe(Agenda agenda){
        if(agenda.getEncaixe()){
            return true;
        }else{
            return false;
        }
    }

    public void verificaDataMaiorNow(Agenda agenda) {
        if(agenda.getDataDe().compareTo(LocalDateTime.now()) >= 0 &&
                agenda.getDataAte().compareTo(LocalDateTime.now()) >= 0){
            throw new RuntimeException("DATA ESTA ALÉM DA DATA ATUAL");
        }
    }

    public boolean verificaDataMenorNow(Agenda agenda) {
        if(agenda.getDataDe().compareTo(LocalDateTime.now()) <= 0 &&
                agenda.getDataAte().compareTo(LocalDateTime.now()) <= 0){
            return true;
        }else{
            return false;
        }
    }

    public void verificaDataAteMaiorDe(Agenda agenda){
        if(agenda.getDataAte().compareTo(LocalDateTime.now())
                >
                agenda.getDataDe().compareTo(LocalDateTime.now())){
            throw new RuntimeException("DATA ATÉ ESTA ALÉM DA DATA DE");
        }
    }

    public LocalDateTime getHour(Agenda agenda){
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm");
        LocalDateTime hourAgenda = LocalDateTime.parse(hour.format(agenda.getDataDe()));
        return hourAgenda;
    }

    public void verificaIntervalo(Agenda agenda){
        if(agenda.getDataDe().compareTo(getHour(agenda)) >= 8
            && agenda.getDataDe().compareTo(getHour(agenda)) <= 12){
            throw new RuntimeException("HORARIO DE FUNCIONAMENTO ANTES DO ALMOÇO");
        }else if(agenda.getDataDe().compareTo(getHour(agenda)) >= 14
                && agenda.getDataDe().compareTo(getHour(agenda)) <= 18){
            throw new RuntimeException("HORARIO DE FUNCIONAMENTO DEPOIS DO ALMOÇO");
        }else{
            throw new RuntimeException("ERROR: HORARIO INVALIDO, FORA DE FUNCIONAMENTO");
        }
    }

    public boolean verificaFechamento(LocalDateTime fds){
        if(fds.getDayOfWeek() == DayOfWeek.SUNDAY
                ||
                fds.getDayOfWeek() == DayOfWeek.SATURDAY){
            return true;
        }else{
            return false;
        }
    }

    public void verificaFuncionamento(Agenda agenda){
        if(verificaFechamento(agenda.getDataDe()) || verificaFechamento(agenda.getDataAte())){
            throw new RuntimeException("ERROR: FINAL DE SEMANA NÃO POSSUI AGENDAMENTOS");
        }
    }

    private void verificaMedicoHorario(Agenda agenda){
        if(this.agendaRepository.verificaMedicoHora
                (agenda.getDataDe(), agenda.getDataAte(), agenda.getMedico().getId()).size() > 0){
          throw new RuntimeException("ERROR: HORARIO INVALIDO, MEDICO JA POSSUI UMA CONSULTA NESTE HORARIO");
        }
    }

    public void verificaAgendamentoExistente(Agenda agenda){
        if(this.agendaRepository.verificaAgenda(agenda.getDataDe()).size() > 0){
            throw new RuntimeException("ERROR: HORARIO INVALIDO, PACIENTE JA CADASTRADO");
        }
    }

    public void verificaCadastro(Secretaria secretaria, Agenda agenda){
        if(secretaria.getId() == null){
            verificaPaciente(agenda);
        }else{
            verificaSecretaria(agenda);
        }
    }

    public void verificaPaciente(Agenda agenda){
        verificaEncaixe(agenda);
        verificaDataMaiorNow(agenda);
        verificaDataAteMaiorDe(agenda);
        verificaIntervalo(agenda);
        verificaFuncionamento(agenda);
        if (verificaEncaixe(agenda) == true){
            verificaMedicoHorario(agenda);
        }
        verificaAgendamentoExistente(agenda);
        agenda.setStatus(StatusAgenda.pendente);
    }

    public void verificaSecretaria(Agenda agenda){
        verificaEncaixe(agenda);
        verificaDataMaiorNow(agenda);
        verificaDataAteMaiorDe(agenda);
        verificaIntervalo(agenda);
        verificaFuncionamento(agenda);
        if (verificaEncaixe(agenda) == true){
            verificaMedicoHorario(agenda);
        }
        verificaAgendamentoExistente(agenda);
        agenda.setStatus(StatusAgenda.aprovado);
    }

    public void statusRejeitado(Agenda agenda, Secretaria secretaria){
        if(agenda.getStatus().equals(StatusAgenda.pendente) && secretaria.getId() != null){
            agenda.setStatus(StatusAgenda.rejeitado);
        }
    }

    public void statusAprovado(Agenda agenda, Secretaria secretaria){
        if(agenda.getStatus().equals(StatusAgenda.pendente) && secretaria.getId() != null){
            agenda.setStatus(StatusAgenda.aprovado);
        }
    }

    public void statusCancelado(Agenda agenda, Secretaria secretaria){
        if(agenda.getStatus().equals(StatusAgenda.pendente)
                || agenda.getStatus().equals(StatusAgenda.aprovado)
                && secretaria.getId() != null
                || agenda.getPaciente() != null){
            agenda.setStatus(StatusAgenda.cancelado);
        }
    }

    public void statusCompareceu(Agenda agenda, Secretaria secretaria){
        if(agenda.getStatus().equals(StatusAgenda.aprovado)
                && secretaria.getId() != null
                && verificaDataMenorNow(agenda) == true){
            agenda.setStatus(StatusAgenda.compareceu);
        }
    }

    public void statusNaoCompareceu(Agenda agenda, Secretaria secretaria){
        if(agenda.getStatus().equals(StatusAgenda.aprovado)
                && secretaria.getId() != null
                && verificaDataMenorNow(agenda) == true){
            agenda.setStatus(StatusAgenda.nao_compareceu);
        }
    }

}
