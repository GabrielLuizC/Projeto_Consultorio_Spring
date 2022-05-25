package br.com.uniamerica.api.controller;

import br.com.uniamerica.api.entity.Secretaria;
import br.com.uniamerica.api.repository.SecretariaRepository;
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
@RequestMapping("/api/secretarias")
public class SecretariaController {

    @Autowired
    public SecretariaRepository secretariaRepository;

    @GetMapping
    public ResponseEntity<List<Secretaria>> listAllSecretaria(){
        return new ResponseEntity<>(secretariaRepository.findAll(), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Secretaria secretaria){
        secretariaRepository.save(secretaria);
        return new ResponseEntity<>("Registro Cadastrado", HttpStatus.OK);
    }
}