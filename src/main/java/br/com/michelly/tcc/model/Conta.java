package br.com.michelly.tcc.model;

import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
@Table(name = "CONTA")
public class Conta{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 60)
    private String nome;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(unique = true, length = 14)
    private String telefone;
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(nullable = false)
    private Boolean deficiente;
    @Column(nullable = false)
    private LocalDate datanas;
    private int idade;
    @Column(nullable = false, length = 100) 
    private String senha;

    public Long getId() {        return id;    }
    public void setId(Long id) {        this.id = id;    }
    public String getNome() {        return nome;    }
    public void setNome(String nome) {        this.nome = nome;    }
    public String getEmail() {        return email;    }
    public void setEmail(String email) {        this.email = email;    }
    public String getTelefone() {        return telefone;    }
    public void setTelefone(String telefone) {        this.telefone = telefone;    }
    public Boolean getDeficiente() {        return deficiente;    }
    public void setDeficiente(Boolean deficiente) {        this.deficiente = deficiente;    }
    public int getIdade() {        return idade;    }
    public void setIdade(int idade) {        this.idade = idade;    }
    public String getSenha() {        return senha;    }
    public void setSenha(String senha) {        this.senha = senha;    }
    public String getCpf() {        return cpf;    }
    public void setCpf(String cpf) {        this.cpf = cpf;    }
    public LocalDate getDatanas() {        return datanas;    }
    public void setDatanas(LocalDate datanas) {        this.datanas = datanas;    }
    
}