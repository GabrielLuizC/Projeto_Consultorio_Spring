package br.com.uniamerica.api.controller;

import br.com.uniamerica.api.entity.Convenio;
import br.com.uniamerica.api.repository.ConvenioRepository;
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
@RequestMapping("/api/convenios")
public class ConvenioController {

    @Autowired
    public ConvenioRepository convenioRepository;

    @GetMapping
    public ResponseEntity<List<Convenio>> listAllConvenios(){
        return new ResponseEntity<>(convenioRepository.findAll(), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Convenio convenio){
        convenioRepository.save(convenio);
        return new ResponseEntity<>("Registro Cadastrado", HttpStatus.OK);
    }

}
