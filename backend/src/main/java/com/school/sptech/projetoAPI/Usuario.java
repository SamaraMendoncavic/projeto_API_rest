package com.school.sptech.projetoAPI;

import java.time.LocalDate;

public class Usuario {
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dtNascimento;
    private Integer idade;
    private String genero;
    private Boolean aceitaNewsletter;
    private Integer generoLiterarioId;

    public Usuario() {}

    public Usuario(Integer id, String nome, String email, String senha, LocalDate dtNascimento,
                   Integer idade, String genero, Boolean aceitaNewsletter, Integer generoLiterarioId) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.idade = idade;
        this.genero = genero;
        this.aceitaNewsletter = aceitaNewsletter;
        this.generoLiterarioId = generoLiterarioId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public LocalDate getDtNascimento() { return dtNascimento; }
    public void setDtNascimento(LocalDate dtNascimento) { this.dtNascimento = dtNascimento; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Boolean getAceitaNewsletter() { return aceitaNewsletter; }
    public void setAceitaNewsletter(Boolean aceitaNewsletter) { this.aceitaNewsletter = aceitaNewsletter; }

    public Integer getGeneroLiterarioId() { return generoLiterarioId; }
    public void setGeneroLiterarioId(Integer generoLiterarioId) { this.generoLiterarioId = generoLiterarioId; }
}
