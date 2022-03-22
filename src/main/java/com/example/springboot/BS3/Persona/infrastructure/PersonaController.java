package com.example.springboot.BS3.Persona.infrastructure;

import com.example.springboot.BS3.Persona.application.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("personas")
    public ResponseEntity <List<PersonaOutputDTO>> getAllPersonas(){
        return new ResponseEntity<>(personaService.getPersonas(), HttpStatus.OK);
    }

    @PostMapping("insertPersona")
    public ResponseEntity<PersonaOutputDTO> addPersona(@RequestBody PersonaInputDTO personaInputDTO){
        return new ResponseEntity<>(personaService.addPersona(personaInputDTO),HttpStatus.OK);
    }

    @PutMapping("personas/{id}")
    public ResponseEntity<PersonaOutputDTO> updatePersona(@RequestBody PersonaInputDTO personaInputDTO, @PathVariable String id){
        return new ResponseEntity<>(personaService.updatePersona(personaInputDTO,id),HttpStatus.OK);
    }

    @DeleteMapping("personas/{id}")
    public void deletePersona(@PathVariable String id){
       personaService.deletePersona(id) ;
    }
}
