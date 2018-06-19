package br.com.listaCompras;

public interface ObservadorListaI {

    public void produtoAdicionado(ProdutoLista pl);
    public void produtoRemovido(ProdutoLista pl);
}
