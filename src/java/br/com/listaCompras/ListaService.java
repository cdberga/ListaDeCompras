package br.com.listaCompras;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ListaService {

    private ObservadorListaI observadorLista;

    public ListaService() {
	super();
	listas = new HashMap<String, Lista>();
	observadorLista = new ObservadorValorLista();
    }

    private Map<String, Lista> listas;

    public boolean criarLista(Lista l) {
	if (!isPermitidoCriarLista(l))
	    return false;

	listas.put(l.getNome(), l);
	return true;
    }

    private boolean isPermitidoCriarLista(Lista l) {
	return l != null && listas.get(l.getNome()) == null && isNomeListaValido(l);
    }

    public boolean isNomeListaValido(Lista l) {
	return l.getNome().matches("^[^0-9].*");
    }

    public void limparListas() {
	if (listas != null) {
	    listas.clear();
	}
    }

    public boolean excluirLista(String nome) {
	return listas.remove(nome) != null;
    }

    public boolean excluirLista(Lista l) {
	return excluirLista(l.getNome());
    }

    public boolean addProdutoLista(Lista lista, ProdutoLista pl) {
	if (pl.getQuantidade() <= 0 || !isValidadeAceitavel(pl.getValidade())) {
	    return false;
	}
	lista.addProduto(pl);
	listas.put(pl.getProduto().getNome(), lista);
	observadorLista.produtoAdicionado(pl);
	return true;
    }

    private boolean isValidadeAceitavel(Date validade) {
	if (validade == null) {
	    return false;
	}
	Calendar min = Calendar.getInstance();
	min.add(Calendar.DATE, 1);
	min.set(Calendar.MINUTE, 0);
	min.set(Calendar.SECOND, 0);
	min.set(Calendar.HOUR_OF_DAY, 0);
	return min.getTime().compareTo(validade) <= 0;
    }

    public Lista getLista(String nome) {
	return listas.get(nome);
    }

    public boolean removerProdutoLista(Lista l, ProdutoLista pl) {
	boolean removido = l.removerProduto(pl);
	if (removido) {
	    observadorLista.produtoRemovido(pl);
	    return true;
	}
	return false;
    }

    public void setObservadorLista(ObservadorListaI observadorLista) {
	this.observadorLista = observadorLista;
    }

}
