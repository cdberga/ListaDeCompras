package br.com.listaCompras;

import java.util.HashMap;
import java.util.Map;

import br.com.listaCompras.exception.ProdutoJaCriadoException;

public class ProdutoService {

    Map<String, Produto> produtos;

    public ProdutoService() {
	super();
	produtos = new HashMap<String, Produto>();
    }

    public boolean criarProduto(Produto p) throws ProdutoJaCriadoException {
	if (!isNomeProdutoValido(p) || !isValorProdutoValido(p)) {
	    return false;
	} else if (produtos.get(p.getNome()) != null) {
	    throw new ProdutoJaCriadoException();
	}

	produtos.put(p.getNome(), p);
	return true;
    }

    private boolean isNomeProdutoValido(Produto p) {
	if (p == null || p.getNome() == null) {
	    return false;
	}

	return p.getNome().matches("[a-zA-Z ].*");
    }

    private boolean isValorProdutoValido(Produto p) {
	if (p == null || p.getValor() == null) {
	    return false;
	}
	return true;
    }

    public void limparProdutos() {
	if (produtos != null) {
	    produtos.clear();
	}
    }

    public boolean excluirProduto(String nome) {
	return produtos.remove(nome) != null;
    }

    public boolean excluirProduto(Produto p) {
	return excluirProduto(p.getNome());
    }
}
