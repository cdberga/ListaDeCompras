package br.com.listaCompras;

import java.math.BigDecimal;

public class Produto {

    private String nome;
    private BigDecimal valor;

    public Produto(String nome) {
	this.nome = nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public void setValor(BigDecimal valor) {
	this.valor = valor;
    }

    public String getNome() {
	return this.nome;
    }

    public BigDecimal getValor() {
	return this.valor;
    }

}
