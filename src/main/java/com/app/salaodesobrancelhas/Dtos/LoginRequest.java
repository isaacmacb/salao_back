package com.app.salaodesobrancelhas.Dtos;

public class LoginRequest {
    // A classe que consome o DTO (Data Transfer Object) é a camada de Service.
    // DTO = Serve para transportar dados entre camadas, principalmente entre Controller e Service, e depois para o front-end.
    // Representam os mesmos dados essenciais de um conceito no sistema (exemplo: Usuário, Produto, Cliente).
    // O DTO geralmente tem os mesmos campos principais que a entidade, porque precisa transmitir esses dados entre camadas (ex: do back para o front).
    // a clase com os mesmo dados da entity Usuario

    private String username;
    private String senha;

    // Construtores (opcional)
    public LoginRequest() {}

    public LoginRequest(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
