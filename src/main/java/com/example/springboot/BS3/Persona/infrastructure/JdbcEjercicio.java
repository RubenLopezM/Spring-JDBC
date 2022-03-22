package com.example.springboot.BS3.Persona.infrastructure;

import com.example.springboot.BS3.Persona.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class JdbcEjercicio {


    @Autowired
   private JdbcTemplate jdbcTemplate;



    private final AtomicInteger counter = new AtomicInteger(1);



    public List<Persona> getPersonas(){
        String sql= "select * from persona";
        return jdbcTemplate.queryForObject(sql,new personaListaRowMapper());
    }

    public void deletePersona(String id){
        String SQL = "delete from persona where id_persona = ?";
        jdbcTemplate.update(SQL, id);
        return;

    }

    public Persona setPersona(Persona persona,String id){

        String sql = "update persona set usuario=?,password=?,name=?,surname=?,company_email=?,personal_email=?,city=?,active=?,created_date=?,imagen_url=?,termination_date=? where id_persona=?";
        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID_PERSONA"});
                    ps.setString(12, persona.getId());
                    ps.setString(1, persona.getUsuario());
                    ps.setString(2, persona.getPassword());
                    ps.setString(3, persona.getName());
                    ps.setString(4, persona.getSurname());
                    ps.setString(5, persona.getCompany_email());
                    ps.setString(6, persona.getPersonal_email());
                    ps.setString(7, persona.getCity());
                    ps.setBoolean(8, persona.isActive());
                    ps.setDate(9, new java.sql.Date(persona.getCreated_date().getTime()));
                    ps.setString(10, persona.getImagen_url());
                    ps.setDate(11, new java.sql.Date(persona.getTermination_date().getTime())) ;

                    return ps;
                });
        return jdbcTemplate.queryForObject("select * from persona where id_persona=?", new personaRowMapper(),id);
    }



    public Persona insertPersona(Persona persona){

        String newId = String.valueOf(counter.getAndIncrement());


        final String sql = "insert into persona(id_persona,usuario,password,name,surname,company_email,personal_email,city,active,created_date,imagen_url,termination_date) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        persona.setId(newId);
        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID_PERSONA"});
                    ps.setString(1, persona.getId());
                    ps.setString(2, persona.getUsuario());
                    ps.setString(3, persona.getPassword());
                    ps.setString(4, persona.getName());
                    ps.setString(5, persona.getSurname());
                    ps.setString(6, persona.getCompany_email());
                    ps.setString(7, persona.getPersonal_email());
                    ps.setString(8, persona.getCity());
                    ps.setBoolean(9, persona.isActive());
                    ps.setDate(10, new java.sql.Date(persona.getCreated_date().getTime())) ;
                    ps.setString(11, persona.getImagen_url());
                    ps.setDate(12, new java.sql.Date(persona.getTermination_date().getTime())) ;
                    return ps;
                });
        return persona;

    }

    private class personaRowMapper implements RowMapper<Persona> {

        @Override
        public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Persona(
                    rs.getString("ID_PERSONA"),
                    rs.getString("usuario"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("company_email"),
                    rs.getString("personal_email"),
                    rs.getString("city"),
                    rs.getBoolean("ACTIVE"),
                    rs.getDate("created_date"),
                    rs.getString("imagen_url"),
                    rs.getDate("termination_date"));
        }
    }

    private  class personaListaRowMapper implements RowMapper<List<Persona>> {

        @Override
        public List<Persona> mapRow(ResultSet rs, int rowNum) throws SQLException {
            ArrayList<Persona> listaPersonas= new ArrayList();
            do
            {
                listaPersonas.add(new Persona(
                        rs.getString("ID_PERSONA"),
                        rs.getString("usuario"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("company_email"),
                        rs.getString("personal_email"),
                        rs.getString("city"),
                        rs.getBoolean("active"),
                        rs.getDate("created_date"),
                        rs.getString("imagen_url"),
                        rs.getDate("termination_date")));

            } while (rs.next());
            return listaPersonas;
        }
    }
}
