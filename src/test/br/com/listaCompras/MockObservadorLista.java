package br.com.listaCompras;

import java.math.BigDecimal;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MockObservadorLista implements ObservadorListaI {

    private final BigDecimal CEM = new BigDecimal(100);
    private BigDecimal valorLista = BigDecimal.ZERO;
    private boolean superou100 = false;
    
    public void verificaSuperouMultiplo100() {
	assertTrue(superou100);
    }

    public void verificaNaoSuperouMultiplo100() {
	assertFalse(superou100);
    }

    @Override
    public void produtoAdicionado(ProdutoLista pl) {
	BigDecimal retorno = valorLista.divide(CEM);
	int resultadoAnterior = retorno.intValue();

	BigDecimal auxiliar = pl.getProduto().getValor().multiply(new BigDecimal( pl.getQuantidade()));
	valorLista = valorLista.add(auxiliar);
	
	retorno = valorLista.divide(CEM);
	int resultadoPosterior = retorno.intValue();
	
	superou100 = resultadoAnterior < resultadoPosterior;
    }

    @Override
    public void produtoRemovido(ProdutoLista pl) {
	BigDecimal auxiliar = pl.getProduto().getValor().multiply(new BigDecimal( pl.getQuantidade()));
	valorLista = valorLista.subtract(auxiliar);
	superou100 = false;
    }

}
