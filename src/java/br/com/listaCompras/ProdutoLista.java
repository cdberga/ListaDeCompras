package br.com.listaCompras;

import java.util.Date;

public class ProdutoLista {

    private Produto produto;
    private Date validade;
    private int quantidade;
    
    public ProdutoLista(Produto produtoParaTeste) {
	this.produto = produtoParaTeste; 
    }

    public void setValidade(Date data) {
	this.validade = data;
    }
    
    public Date getValidade() {
	return validade;
    }

    public void setQuantidade(int i) {
	this.quantidade = i;
    }
    
    public int getQuantidade() {
	return quantidade;
    }
    
    public void setProduto(Produto produto) {
	this.produto = produto;
    }
    
    public Produto getProduto() {
	return produto;
    }

}
