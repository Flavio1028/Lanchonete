package lanchonete.negocio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * @author flavio.rocha
 */
public class Cardapio extends JFrame {

	/**
	 * Atributo
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Atributo
	 */
	@SuppressWarnings("unused")
	private int codigo;
	/**
	 * Atributo
	 */
	private Connection conn;
	/**
	 * Atributo
	 */
	@SuppressWarnings("unused")
	private JButton bPes;
	/**
	 * Atributo
	 */
	private JTextField tPes;
	/**
	 * Atributo
	 */
	private JTextArea a;
	/**
	 * Atributo
	 */
	@SuppressWarnings("unused")
	private JLabel lTitulo, lpes;
	/**
	 * Atributo
	 */
	private ResourceBundle linguas;
	/**
	 * Atributo
	 */
	DecimalFormat formato = new DecimalFormat("00.00");

	/**
	 * @param linguas
	 * @param conn
	 */
	public Cardapio(ResourceBundle linguas, Connection conn) {
		this.linguas = linguas;
		this.conn = conn;
	}

	// Recupera o cardapio inteiro
	public String carregaCardapio() {
		String dados = "";
		String sql = "SELECT * FROM cardapio ORDER BY codigo";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			ResultSet r = stm.executeQuery();
			while (r.next() == true) {
				dados += linguas.getString("cardapio.Codigo") + r.getInt("codigo") + "\n";
				dados += linguas.getString("cardapio.Nome") + r.getString("nome") + "\n";
				dados += linguas.getString("cardapio.Tipo") + r.getString("tipo") + "\n";
				dados += linguas.getString("cardapio.Descricao") + r.getString("descricao") + "\n";
				dados += linguas.getString("cardapio.Preco") + formato.format(r.getDouble("preco")) + "\n\n";
			}
			stm.close();
		} catch (Exception e) {
			System.err.println(e.getCause());
		}

		return dados;
	}

	// Recupera apenas um prato para adicionar ao pedido
	public String itemAdicionar(int codigo) {
		String item = "";
		String sql = "SELECT * FROM cardapio WHERE codigo = (?)";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, codigo);
			ResultSet r = stm.executeQuery();
			while (r.next() == true) {
				item += linguas.getString("cardapio.Codigo") + r.getInt("codigo") + "\n";
				item += linguas.getString("cardapio.Nome") + r.getString("nome") + "\n";
				item += linguas.getString("cardapio.Preco") + formato.format(r.getDouble("preco")) + "\n\n";
			}
		} catch (Exception e) {
			System.err.println(e.getCause());
		}
		return item;
	}

	// Recupera o preço do prato
	public double itemPreco(int codigo) {
		double preco = 0.0;
		String sql = "SELECT preco FROM cardapio WHERE codigo = (?)";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, codigo);
			ResultSet r = stm.executeQuery();
			while (r.next() == true) {
				preco = r.getDouble("preco");
			}
		} catch (Exception e) {
			System.err.println(e.getCause());
		}
		return preco;
	}

	// Verifica se o codigo informada é válido
	public boolean codigoValido(int codigo) {
		String sql = "SELECT codigo From cardapio Where codigo = (?)";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, codigo);
			ResultSet r = stm.executeQuery();
			if (r.next() == true) {
				return true;
			}
		} catch (Exception e) {
			System.err.println(e.getCause());
		}
		return false;
	}

	// Busca por nome
	public String buscaPorNome(String busca) {
		busca = busca + "%";
		String saida = "";
		String sql = "SELECT * FROM cardapio WHERE nome LIKE (?)";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, busca);
			ResultSet r = stm.executeQuery();
			while (r.next() == true) {
				saida += linguas.getString("cardapio.Codigo") + r.getInt("codigo") + "\n";
				saida += linguas.getString("cardapio.Nome") + r.getString("nome") + "\n";
				saida += linguas.getString("cardapio.Tipo") + r.getString("tipo") + "\n";
				saida += linguas.getString("cardapio.Descricao") + r.getString("descricao") + "\n";
				saida += linguas.getString("cardapio.Preco") + formato.format(r.getDouble("preco")) + "\n\n";
			}
		} catch (Exception e) {
			System.err.println(e.getCause());
		}
		if (saida.isEmpty() == true) {
			saida = linguas.getString("cardapio.resultado.pes");
		}

		return saida;
	}

	// Cria a janela para mostrar o cardapio
	public void janelaCardapio() {
		setTitle(linguas.getString("cardapio"));
		setSize(500, 500);
		setLocation(25, 100);
		setResizable(false);

		lTitulo = new JLabel(linguas.getString("cardapio"));

		Container itens = getContentPane();
		itens.setLayout(new BorderLayout());

		JPanel pBaixo = new JPanel(new FlowLayout(1));
		pBaixo.setBackground(new Color(0, 0, 0));
		pBaixo.add(lpes = new JLabel(linguas.getString("cardapio.label.nome")));
		lpes.setForeground(new Color(255, 255, 255));
		pBaixo.add(tPes = new JTextField(10));
		tPes.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {

				// Remove os espaços em branco a esquerda da barra de pesquisa.
				String busca = tPes.getText().trim();
				// Faz a busca e exibe o resultado na area de texto.
				a.setText(buscaPorNome(busca));
			}
		});
		JPanel centro = new JPanel(new FlowLayout(1));
		centro.setBackground(new Color(63, 81, 181));
		a = new JTextArea(22, 40);
		a.setEditable(false);
		a.setText(carregaCardapio());
		JScrollPane barra_rolagem = new JScrollPane(a);
		this.add(barra_rolagem);
		centro.add(barra_rolagem);

		JPanel pCima = new JPanel(new FlowLayout(1));
		pCima.setBackground(new Color(0, 0, 0));
		JLabel texto = new JLabel(linguas.getString("JanelaTitulo"));
		texto.setForeground(new Color(255, 255, 255));
		texto.setFont(new Font("Segoe UI Light", Font.ITALIC + Font.BOLD, 12));
		pCima.add(texto);

		itens.add(pBaixo, BorderLayout.SOUTH);
		itens.add(centro, BorderLayout.CENTER);
		itens.add(pCima, BorderLayout.NORTH);

		setVisible(true);
	}
}