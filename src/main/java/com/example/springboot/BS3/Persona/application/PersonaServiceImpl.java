package com.example.springboot.BS3.Persona.application;

import com.example.springboot.BS3.Persona.domain.Persona;
import com.example.springboot.BS3.Persona.infrastructure.JdbcEjercicio;
import com.example.springboot.BS3.Persona.infrastructure.PersonaInputDTO;
import com.example.springboot.BS3.Persona.infrastructure.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    JdbcEjercicio jdbc;

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) {
        Persona persona= personaInputDTO.converToPersona(personaInputDTO);
        jdbc.insertPersona(persona);
        return convertToDTO(persona);
    }

    @Override
    public List<PersonaOutputDTO> getPersonas() {
      List<Persona> personas= jdbc.getPersonas();
      List<PersonaOutputDTO> personaOutputDTOList=new ArrayList<>();
      for (Persona persona:personas){
          personaOutputDTOList.add(convertToDTO(persona));
      }
      return personaOutputDTOList;
    }

    @Override
    public void deletePersona(String id) {
            jdbc.deletePersona(id);
    }

    @Override
    public PersonaOutputDTO updatePersona(PersonaInputDTO personaInputDTO, String id) {
        Persona persona= personaInputDTO.converToPersona(personaInputDTO);
          jdbc.setPersona(persona,id);
          return convertToDTO(persona);
    }

    private PersonaOutputDTO convertToDTO (Persona persona){
        return new PersonaOutputDTO(persona);
    }

}
