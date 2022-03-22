package com.example.springboot.BS3.Persona.application;

import com.example.springboot.BS3.Persona.infrastructure.PersonaInputDTO;
import com.example.springboot.BS3.Persona.infrastructure.PersonaOutputDTO;

import java.util.List;

public interface PersonaService {

    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO);
    public List<PersonaOutputDTO> getPersonas();
    public void deletePersona(String id);
    public PersonaOutputDTO updatePersona(PersonaInputDTO personaInputDTO, String id);
}
