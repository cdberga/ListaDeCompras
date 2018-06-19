package br.com.listaCompras;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.listaCompras.exception.ProdutoJaCriadoException;

public class ProdutoServiceTest {

    ProdutoService service = new ProdutoService();

    @Test
    public void whenProdutoCriarSemNome() throws ProdutoJaCriadoException {
	service.limparProdutos();
	Produto p = new Produto(null);
	p.setValor(new BigDecimal(12.00));
	assertFalse(service.criarProduto(p));
    }

    @Test
    public void whenProdutoCriarComNomeInvalido() throws ProdutoJaCriadoException {
	service.limparProdutos();
	Produto p = new Produto("1 produto");
	p.setValor(new BigDecimal(12.00));
	assertFalse(service.criarProduto(p));
    }

    @Test
    public void whenProdutoCriarComNomeEValorValido() throws ProdutoJaCriadoException {
	service.limparProdutos();
	Produto p = new Produto("Sabao Omo");
	p.setValor(new BigDecimal(12.00));
	boolean b = service.criarProduto(p);
	assertTrue(b);
    }

    @Test
    public void whenProdutoCriarSemValor() throws ProdutoJaCriadoException {
	service.limparProdutos();
	Produto p = new Produto("Sabao Omo");
	assertFalse(service.criarProduto(p));
    }

    @Test(expected = ProdutoJaCriadoException.class)
    public void whenProdutoJaExiste() throws ProdutoJaCriadoException {
	service.limparProdutos();
	Produto p = new Produto("Sabao Omo");
	p.setValor(BigDecimal.TEN);
	service.criarProduto(p);
	Produto p2 = new Produto("Sabao Omo");
	p2.setValor(new BigDecimal(9));
	service.criarProduto(p2);
    }
    
    @Test
    public void whenExcluirProdutoExistente() throws ProdutoJaCriadoException {
	Produto p = criarProdutoNaListaParaTeste();
	assertTrue(service.excluirProduto(p));
    }

    private Produto criarProdutoNaListaParaTeste() throws ProdutoJaCriadoException {
	service.limparProdutos();
	Produto p = new Produto("Bombril");
	p.setValor(BigDecimal.TEN);
	service.criarProduto(p);
	return p;
    }

    @Test
    public void whenTentoExcluirProdutoInexistente() throws ProdutoJaCriadoException {
	assertFalse(service.excluirProduto("Produto que nao existe"));
    }

}
