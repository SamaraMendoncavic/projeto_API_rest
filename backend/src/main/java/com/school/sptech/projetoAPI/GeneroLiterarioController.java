package com.school.sptech.projetoAPI;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/generos-literarios")
public class GeneroLiterarioController {

    private final JdbcTemplate jdbcTemplate;

    public GeneroLiterarioController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public ResponseEntity<List<GeneroLiterario>> listar() {
        String sql = "SELECT * FROM genero_literario";
        List<GeneroLiterario> generos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GeneroLiterario.class));
        return ResponseEntity.status(200).body(generos);
    }
}
