package lanchonete.negocio;

import java.util.ArrayList;

import lanchonete.dao.AcessoBD;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author flavio.rocha
 */
public class Pedido {

	/**
	 * Atributo
	 */
	private ArrayList<Integer> codigos = new ArrayList<Integer>();
	/**
	 * Atributo
	 */
	private String itemPedido = "";
	/**
	 * Atributo
	 */
	double preco = 0.0;
	/**
	 * Atributo
	 */
	private ResourceBundle linguas;
	/**
	 * Atributo
	 */
	DecimalFormat formato = new DecimalFormat("00.00");

	Cardapio cardapio;

	public Pedido(ResourceBundle linguas) {
		this.linguas = linguas;
		cardapio = new Cardapio(linguas, conecta());
	}

	// Altera o idioma da tela
	public void trocaIdioma(ResourceBundle linguas) {
		this.linguas = linguas;
		cardapio = new Cardapio(linguas, conecta());
	}

	// Adiciona os itens ao pedido
	public void adicionaCodigo(int codigo) {
		codigos.add(codigo);
	}

	// Remove um item da lista
	public boolean removeCodigo(int codigo) {
		itemPedido = "";
		int tamanho = codigos.size();
		for (int i = 0; i < tamanho; i++) {
			if (codigo == codigos.get(i)) {
				codigos.remove(i);
				return true;
			}
		}
		return false;
	}

	// Atualiza a tela com a lista de produtos
	public String atualizaListaPedido() {
		itemPedido = "";
		preco = 0.0;
		int tamanho = codigos.size();
		for (int i = 0; i < tamanho; i++) {
			itemPedido += cardapio.itemAdicionar(codigos.get(i));
			preco += cardapio.itemPreco(codigos.get(i));
		}

		if (itemPedido.isEmpty() == true) {
			itemPedido = linguas.getString("pedido.mensagem") + "\n\n";
		}
		return itemPedido + linguas.getString("pedido.preco") + " R$ " + formato.format(preco);
	}

	// Outros métodos
	public String preco() {
		return formato.format(preco);
	}

	public boolean codigoValido(int codigo) {
		return cardapio.codigoValido(codigo);
	}

	public void JanelaCardapio() {
		cardapio.janelaCardapio();
	}

	// Faz a conexao com o banco de dados
	public Connection conecta() {
		AcessoBD bancoDados = new AcessoBD();
		try {
			return bancoDados.obtemConexao();
		} catch (Exception e) {
			System.out.print("Error database !!!");
			// System.exit(0);
		}
		return null;
	}
}