package br.com.listaCompras;

import java.math.BigDecimal;

public class ObservadorValorLista implements ObservadorListaI{

    private BigDecimal valorLista = BigDecimal.ZERO;

    @Override
    public void produtoAdicionado(ProdutoLista pl) {
	BigDecimal auxiliar = pl.getProduto().getValor().multiply(new BigDecimal( pl.getQuantidade()));
	valorLista = valorLista.add(auxiliar);
    }

    @Override
    public void produtoRemovido(ProdutoLista pl) {
	BigDecimal auxiliar = pl.getProduto().getValor().multiply(new BigDecimal( pl.getQuantidade()));
	valorLista = valorLista.subtract(auxiliar);
    }
}
