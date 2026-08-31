package com.school.sptech.projetoAPI;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usuario.class));
        return ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario paraCriar) {

        // Validação de campos obrigatórios
        if (paraCriar.getNome() == null || paraCriar.getNome().trim().isEmpty()) {
            return ResponseEntity.status(400).body("Nome é obrigatório.");
        }
        if (paraCriar.getEmail() == null || paraCriar.getEmail().trim().isEmpty()) {
            return ResponseEntity.status(400).body("E-mail inválido.");
        }
        if (paraCriar.getSenha() == null || paraCriar.getSenha().length() < 6) {
            return ResponseEntity.status(400).body("Senha deve ter no mínimo 6 caracteres.");
        }
        if (paraCriar.getIdade() == null || paraCriar.getIdade() <= 0) {
            return ResponseEntity.status(400).body("Idade inválida.");
        }
        if (paraCriar.getDtNascimento() == null) {
            return ResponseEntity.status(400).body("Data de nascimento é obrigatória.");
        }
        if (paraCriar.getGenero() == null || paraCriar.getGenero().isBlank()) {
            return ResponseEntity.status(400).body("Selecione um gênero.");
        }
        if (paraCriar.getGeneroLiterarioId() == null || !generoLiterarioExiste(paraCriar.getGeneroLiterarioId())) {
            return ResponseEntity.status(400).body("Gênero literário inválido.");
        }
        if (emailJaExiste(paraCriar.getEmail())) {
            return ResponseEntity.status(400).body("Este e-mail já está cadastrado.");
        }

        String sql = "INSERT INTO usuario (nome, email, senha, dt_nascimento, idade, genero, aceita_newsletter, genero_literario_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, paraCriar.getNome());
            ps.setString(2, paraCriar.getEmail());
            ps.setString(3, paraCriar.getSenha());
            ps.setDate(4, Date.valueOf(paraCriar.getDtNascimento()));
            ps.setInt(5, paraCriar.getIdade());
            ps.setString(6, paraCriar.getGenero());
            ps.setBoolean(7, Boolean.TRUE.equals(paraCriar.getAceitaNewsletter()));
            ps.setInt(8, paraCriar.getGeneroLiterarioId());
            return ps;
        }, keyHolder);

        Integer idGerado = keyHolder.getKeyAs(Integer.class);
        paraCriar.setId(idGerado);
        paraCriar.setSenha(null);

        return ResponseEntity.status(201).body(paraCriar);
    }

    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticar(@RequestBody Usuario paraAutenticar) {
        if (paraAutenticar.getEmail() == null || paraAutenticar.getSenha() == null) {
            return ResponseEntity.status(400).body("E-mail e senha são obrigatórios.");
        }

        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try {
            List<Usuario> usuarios = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usuario.class),
                    paraAutenticar.getEmail(), paraAutenticar.getSenha());

            if (usuarios.isEmpty()) {
                return ResponseEntity.status(404).body("E-mail ou senha incorretos.");
            }

            Usuario encontrado = usuarios.get(0);
            encontrado.setSenha(null);
            return ResponseEntity.status(200).body(encontrado);

        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(404).body("E-mail ou senha incorretos.");
        }
    }

    private boolean emailJaExiste(String email) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    private boolean generoLiterarioExiste(Integer id) {
        String sql = "SELECT COUNT(*) FROM genero_literario WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
