package br.com.listaCompras;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ListaServiceTest {

    private ListaService service = new ListaService();
    private Lista listaParaTeste;
    private Produto produtoParaTeste;
    private ProdutoLista produtoListaValido;

    private Lista getListaParaTeste() {
	Lista l = new Lista("Compra Mes");
	return l;
    }

    private Produto getProdutoParaTeste() {
	Produto p = new Produto("Novo Produto");
	p.setValor(BigDecimal.TEN);
	return p;
    }

    private ProdutoLista getProdutoListaValido() {
	ProdutoLista pl = new ProdutoLista(produtoParaTeste);
	pl.setQuantidade(1);
	Calendar data = Calendar.getInstance();
	data.add(Calendar.DATE, 1);
	Date dataValidade = data.getTime();
	pl.setValidade(dataValidade);
	return pl;
    }

    @Before
    public void setUp() {
	service = new ListaService();
	listaParaTeste = getListaParaTeste();
	produtoParaTeste = getProdutoParaTeste();
	produtoListaValido = getProdutoListaValido();
    }

    @Test
    public void whenNomeListaAlfaValido() {
	Lista l = new Lista("Compra Mes");
	assertTrue(service.isNomeListaValido(l));
    }

    @Test
    public void whenNomeListaAlfaNumValido() {
	Lista l = new Lista("Compra Mes 2");
	assertTrue(service.isNomeListaValido(l));
    }

    @Test
    public void whenNomeListaInvalido() {
	Lista l = new Lista("1 Mes");
	assertFalse(service.isNomeListaValido(l));
    }

    @Test
    public void whenNaoPermiteDuplicarLista() {
	Lista l = getListaParaTeste();
	service.criarLista(l);
	Lista l2 = new Lista("Compra Mes");
	assertFalse(service.criarLista(l2));
	service.limparListas();
    }

    @Test
    public void whenCriaListaInexistente() {
	Lista l = new Lista("Compra Proxima");
	assertTrue(service.criarLista(l));
	service.limparListas();
    }

    @Test
    public void whenExcluirListaExistente() {
	Lista l = getListaParaTeste();
	service.criarLista(l);
	assertTrue(service.excluirLista(listaParaTeste));
	service.limparListas();
    }

    @Test
    public void whenTentoExcluirListaInexistente() {
	Lista l = getListaParaTeste();
	service.criarLista(l);
	assertFalse(service.excluirLista("Comprei no mes"));
	service.limparListas();
    }

    @Test
    public void whenTentoIncluirProdutoComQuantidadeEValidade() {
	ProdutoLista pl = new ProdutoLista(produtoParaTeste);
	
	Calendar data = Calendar.getInstance();
	data.add(Calendar.DATE, 1);
	Date dataValidade = data.getTime();
	pl.setValidade(dataValidade);
	pl.setQuantidade(5);

	assertTrue(service.addProdutoLista(listaParaTeste, pl));
	service.limparListas();
    }

    @Test
    public void whenTentoIncluirProdutoSemQuantidade() {
	ProdutoLista pl = new ProdutoLista(produtoParaTeste);
	Calendar data = Calendar.getInstance();
	data.add(Calendar.DATE, 1);
	Date dataValidade = data.getTime();
	pl.setValidade(dataValidade);

	assertFalse(service.addProdutoLista(listaParaTeste, pl));
	service.limparListas();
    }

    @Test
    public void whenTentoIncluirProdutoSemValidade() {
	ProdutoLista pl = new ProdutoLista(produtoParaTeste);
	pl.setQuantidade(2);

	assertFalse(service.addProdutoLista(listaParaTeste, pl));
	service.limparListas();
    }

    @Test
    public void whenTentoIncluirProdutoForaDaValidade() {
	ProdutoLista pl = new ProdutoLista(produtoParaTeste);
	pl.setQuantidade(1);
	pl.setValidade(Calendar.getInstance().getTime());

	assertFalse(service.addProdutoLista(listaParaTeste, pl));
	service.limparListas();
    }

    @Test
    public void whenValorListaSuperaMultiplo100(){
	ProdutoLista pl = new ProdutoLista(produtoParaTeste);
	pl.setQuantidade(9);
	Calendar data = Calendar.getInstance();
	data.add(Calendar.DATE, 1);
	Date dataValidade = data.getTime();
	pl.setValidade(dataValidade);
	
	ProdutoLista pl2 = new ProdutoLista(produtoParaTeste);
	pl2.setQuantidade(2);
	pl2.setValidade(dataValidade);

	MockObservadorLista mock = new MockObservadorLista();
	service.setObservadorLista(mock);
	service.addProdutoLista(listaParaTeste, pl);
	mock.verificaNaoSuperouMultiplo100();

	service.addProdutoLista(listaParaTeste, pl2);
	mock.verificaSuperouMultiplo100();

	service.limparListas();
    }

    @Test
    public void whenExcluirProdutoComMaisDeUmItem() {
	service.addProdutoLista(listaParaTeste, produtoListaValido);
	service.addProdutoLista(listaParaTeste, produtoListaValido);
	service.addProdutoLista(listaParaTeste, produtoListaValido);
	
	Lista l = service.getLista(produtoListaValido.getProduto().getNome());
	int quantidadeAntes = l.getQuantidadeItensProduto(produtoListaValido.getProduto().getNome());
	assertTrue(service.removerProdutoLista(l, produtoListaValido));
	int quantidadeDepois = l.getQuantidadeItensProduto(produtoListaValido.getProduto().getNome());
	
	assertTrue(quantidadeAntes > quantidadeDepois);
	service.limparListas();
    }

    @Test
    public void whenExcluirProdutoComUmItem() {
	service.addProdutoLista(listaParaTeste, produtoListaValido);

	Lista l = service.getLista(produtoListaValido.getProduto().getNome());
	int quantidadeAntes = l.getQuantidadeItensProduto(produtoListaValido.getProduto().getNome());
	assertTrue(service.removerProdutoLista(l, produtoListaValido));
	int quantidadeDepois = l.getQuantidadeItensProduto(produtoListaValido.getProduto().getNome());
	
	assertTrue(quantidadeAntes > quantidadeDepois);
	service.limparListas();
    }

    @Test
    public void whenExcluirProdutoQueNaoEstaNaLista() {
	Produto p = new Produto("Um Produto");
	p.setValor(BigDecimal.TEN);
	
	service.addProdutoLista(listaParaTeste, produtoListaValido);

	Lista l = service.getLista(produtoListaValido.getProduto().getNome());
	produtoListaValido.setProduto(p);
	assertFalse(service.removerProdutoLista(l, produtoListaValido));
	service.limparListas();
    }

}
