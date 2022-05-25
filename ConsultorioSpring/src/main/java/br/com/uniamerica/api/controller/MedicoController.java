package br.com.uniamerica.api.controller;

import br.com.uniamerica.api.entity.Medico;
import br.com.uniamerica.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    public MedicoRepository medicoRepository;

    @GetMapping
    public ResponseEntity<List<Medico>> findAllMedicos(){
        return new ResponseEntity<>(medicoRepository.findAll(), HttpStatus.OK) ;
    }

    @GetMapping("listTable")
    public ResponseEntity<List<Medico>> listTable(){
        return new ResponseEntity<>(medicoRepository.findAll(), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Medico medico){
        medicoRepository.save(medico);
        return new ResponseEntity<>("Registro Cadastrado", HttpStatus.OK);
    }

}
