/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DCC159.ufjf;

/**
 *
 * @author ice
 */
public class Usuario {
    private String nome;
    private String operacao;
    private double valor;
    private String senha;

    public Usuario() {
    }

    public Usuario(String nome, String operacao, double valor, String senha) {
        this.nome = nome;
        this.operacao = operacao;
        this.valor = valor;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getOperacao() {
        return operacao;
    }

    public String getSenha() {
        return senha;
    }

    public double getValor() {
        return valor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
