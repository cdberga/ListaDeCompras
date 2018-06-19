package br.com.listaCompras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lista {

    private String nome;
    private Map<String, List<ProdutoLista>> itens;

    public Lista(String string) {
	nome = string;
	itens = new HashMap<String, List<ProdutoLista>>();
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public void addProduto(ProdutoLista pl) {
	List<ProdutoLista> plist = itens.get(pl.getProduto().getNome());
	if (plist == null) {
	    plist = new ArrayList<ProdutoLista>();
	}

	plist.add(pl);
	itens.put(pl.getProduto().getNome(), plist);
    }

    public int getQuantidadeItensProduto(String nome) {
	List<ProdutoLista> list = itens.get(nome);
	if (list == null) {
	    return 0;
	}
	return list.size();
    }

    public boolean removerProduto(ProdutoLista pl) {
	String nome = pl.getProduto().getNome();
	List<ProdutoLista> list = itens.get(nome);
	if (list == null || !list.remove(list.iterator().next())) {
	    return false;
	}

	if (itens.get(nome).isEmpty()) {
	    itens.remove(nome);
	}
	return true;
    }
}
