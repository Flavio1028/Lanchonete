package lanchonete.view;

import javax.swing.*;

import lanchonete.negocio.Codigos;
import lanchonete.negocio.Pagamento;
import lanchonete.negocio.Pedido;
import lanchonete.negocio.Usuario;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/*
 * @author flavio.rocha
 */
public class LanchoneteJanela extends JFrame implements ActionListener {

	/**
	 * Atributo
	 */
	private static final long serialVersionUID = 937380373670029026L;
	/**
	 * Atributo
	 */
	private JButton botoes[];
	/**
	 * Atributo
	 */
	private JPanel pBotoes, pCentro, pCentroCima, pCentroCentro, pCentroBaixo;
	/**
	 * Atributo
	 */
	private JLabel lTextos[], lIcone, lAdicionar, lCodigo;
	/**
	 * Atributo
	 */
	private JTextField tAdicionar;
	/**
	 * Atributo
	 */
	private JTextArea aTexto;
	/**
	 * Atributo
	 */
	private JMenuBar mBarra;
	/**
	 * Atributo
	 */
	private JMenu iOpcao, iIdiomas;
	/**
	 * Atributo
	 */
	private JMenuItem itens[];
	/**
	 * Atributo
	 */
	private JRadioButtonMenuItem idioma[];
	/**
	 * Atributo
	 */
	private ButtonGroup grupoIdioma;
	/**
	 * Atributo
	 */
	private int compraAtiva = 0, codigoComprar;
	/**
	 * Atributo
	 */
	private boolean loginAtivo = false;
	/**
	 * Atributo
	 */
	private ResourceBundle linguas = ResourceBundle.getBundle("_idiomas/Portugues", new Locale("pt", "BR"));

	// Textos do programa:
	private String nomesLabels[] = { "janela.nomesLabels.Codigo", "janela.nomesLabels.Numero",
			"janela.nomesLabels.Itens" }, nomesItens[] = { "Janela.itens.Login", "Janela.itens.Sair" },
			nomesIdioma[] = { "Janela.NomeIdioma.Por", "Janela.NomeIdioma.In", "Janela.NomeIdioma.Es" },
			nomesBotoes[] = { "Janela.NomeBotoes.IPe", "Janela.NomeBotoes.Con", "Janela.NomeBotoes.fin",
					"Janela.NomeBotoes.Adi", "Janela.NomeBotoes.Rem", "Janela.NomeBotoes.Vou" };

	// Crição dos objetos
	Pedido p = new Pedido(linguas);
	Codigos codigo = new Codigos();

	public LanchoneteJanela() {
		textos();
		criaBotoes();
		Janela01();
	}

	public void Janela01() {
		setTitle(linguas.getString("JanelaTitulo"));
		setSize(750, 500);
		setLocation(550, 100);
		setResizable(false);

		conteudo = getContentPane();
		conteudo.setLayout(new BorderLayout());

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// e.printStackTrace();
		}

		pCentro = new JPanel(new BorderLayout());
		pCentro.setBackground(new Color(255, 250, 250));
		lIcone = new JLabel(new ImageIcon("_imagens/logotipo.png"));
		pCentro.add(lIcone);

		mBarra = new JMenuBar();
		mBarra.add(menu());

		setJMenuBar(mBarra);
		conteudo.add(pCentro, BorderLayout.CENTER);
		conteudo.add(botoes(), BorderLayout.WEST);
		setVisible(true);
	}

	Container conteudo;

	// Muda o painel do centor.
	public void telaPedido() {
		// Limpa o painel
		conteudo.removeAll();
		pCentro.removeAll();

		pCentroCima = new JPanel(new FlowLayout(1));
		pCentroCima.setBackground(new Color(255, 250, 250));
		lCodigo = new JLabel("" + codigoComprar);
		pCentroCima.add(lTextos[0]);
		pCentroCima.add(lCodigo);

		pCentroCentro = new JPanel(new BorderLayout());
		aTexto = new JTextArea(10, 15);
		aTexto.setEditable(false);
		// Atencao !!!!!!
		aTexto.setText(p.atualizaListaPedido());
		JScrollPane barra_rolagem = new JScrollPane(aTexto);
		this.add(barra_rolagem);
		lTextos[2].setForeground(new Color(255, 250, 250));
		pCentroCentro.add(lTextos[2], BorderLayout.NORTH);
		pCentroCentro.setBackground(new Color(0, 0, 0));
		pCentroCentro.add(barra_rolagem, BorderLayout.CENTER);

		pCentroBaixo = new JPanel(new FlowLayout(1));
		pCentroBaixo.setBackground(new Color(255, 250, 250));
		lAdicionar = new JLabel(linguas.getString("Janela.telaPedido"));
		tAdicionar = new JTextField(5);
		pCentroBaixo.add(lAdicionar);
		pCentroBaixo.add(tAdicionar);
		pCentroBaixo.add(botoes[3]);
		pCentroBaixo.add(botoes[4]);
		pCentroBaixo.add(botoes[5]);

		pCentro.add(pCentroBaixo, BorderLayout.SOUTH);
		pCentro.add(pCentroCentro, BorderLayout.CENTER);
		pCentro.add(pCentroCima, BorderLayout.NORTH);
		conteudo.add(pCentro, BorderLayout.CENTER);
		conteudo.add(botoes(), BorderLayout.WEST);
		repaint();
	}

	// Cria os Labels
	public void textos() {
		lTextos = new JLabel[3];
		for (int i = 0; i < lTextos.length; i++) {
			lTextos[i] = new JLabel(linguas.getString(nomesLabels[i]));
		}
	}

	// Cria os botoes
	public void criaBotoes() {
		botoes = new JButton[6];
		for (int i = 0; i < botoes.length; i++) {
			botoes[i] = new JButton(linguas.getString(nomesBotoes[i]));
			botoes[i].setBackground(new Color(63, 81, 181));
			botoes[i].setForeground(new Color(255, 255, 255));
			botoes[i].setFont(new Font("Segoe UI Light", Font.ITALIC + Font.BOLD, 18));
			botoes[i].addActionListener(this);
		}
	}

	public JPanel botoes() {
		pBotoes = new JPanel(new GridLayout(3, 0, 2, 2));
		pBotoes.setBackground(new Color(255, 250, 250));

		for (int i = 0; i < botoes.length; i++) {
			// Coloca apenas os 3 primeiros botoes no painel a esquerda.
			if (i < 3) {
				pBotoes.add(botoes[i]);
			}
		}
		return pBotoes;
	}

	// Cria os meus
	public JMenu menu() {
		iOpcao = new JMenu(linguas.getString("Janela.menu.op"));

		itens = new JMenuItem[2];
		for (int i = 0; i < itens.length; i++) {
			itens[i] = new JMenuItem(linguas.getString(nomesItens[i]));
			itens[i].addActionListener(this);
		}
		iOpcao.add(idioma());

		iOpcao.add(itens[0]);
		iOpcao.add(itens[1]);
		return iOpcao;
	}

	// Cria o menu idioma e suas opções
	public JMenu idioma() {
		iIdiomas = new JMenu(linguas.getString("Janela.menu.id"));
		iIdiomas.setEnabled(false);
		idioma = new JRadioButtonMenuItem[3];
		grupoIdioma = new ButtonGroup();
		for (int i = 0; i < idioma.length; i++) {
			idioma[i] = new JRadioButtonMenuItem(linguas.getString(nomesIdioma[i]));
			idioma[i].addActionListener(this);
			grupoIdioma.add(idioma[i]);
			iIdiomas.add(idioma[i]);
		}
		idioma[0].setSelected(true);
		return iIdiomas;
	}

	// Seleciona o idioma atual do programa
	public void selecionaIdioma(int opcao) {
		switch (opcao) {
		case 1:
			linguas = ResourceBundle.getBundle("_idiomas/English", Locale.US);
			break;

		case 2:
			linguas = ResourceBundle.getBundle("_idiomas/Portugues", new Locale("pt", "BR"));
			break;

		case 3:
			linguas = ResourceBundle.getBundle("_idiomas/Espanhol", new Locale("es"));
			break;

		default:
			System.err.println("Invalid language");
			linguas = ResourceBundle.getBundle("_idiomas/English", Locale.US);
		}
	}

	public void trocaTextos() {
		for (int i = 0; i < nomesLabels.length; i++) {
			lTextos[i].setText(linguas.getString(nomesLabels[i]));
		}

		for (int i = 0; i < botoes.length; i++) {
			botoes[i].setText((linguas.getString(nomesBotoes[i])));
		}

		for (int i = 0; i < idioma.length; i++) {
			idioma[i].setText(linguas.getString(nomesIdioma[i]));
		}

		for (int i = 0; i < itens.length; i++) {
			itens[i].setText(linguas.getString(nomesItens[i]));
		}

		setTitle(linguas.getString("JanelaTitulo"));
		iIdiomas.setText(linguas.getString("Janela.menu.id"));
		iOpcao.setText(linguas.getString("Janela.menu.op"));
		itens[0].setText(linguas.getString("Janela.itens.Logoff"));

	}

	public void voltar() {
		pCentro.removeAll();
		pCentro.add(lIcone);
		botoes[0].setText((linguas.getString("Janela.NomeBotoes.IPc")));
		repaint();
	}

	// Trada os evendos dos botoes
	public void actionPerformed(ActionEvent e) {
		// Inicia um novo pedido
		if (e.getSource() == botoes[0]) {
			if (compraAtiva == 0) {
				codigoComprar = codigo.geraCodigo();
				compraAtiva++;
				botoes[0].setText(linguas.getString("Janela.NomeBotoes.IPc"));
			}
			telaPedido();
			validate();
		}

		// Mostra o cardapio
		if (e.getSource() == botoes[1]) {
			p.JanelaCardapio();
		}

		// Finaliza o pedido
		if (e.getSource() == botoes[2]) {
			int opc = JOptionPane.showConfirmDialog(null, linguas.getString("Janela.mensagem1"),
					linguas.getString("mensagens.alerta"), 0);
			if (opc == 0 && compraAtiva != 0 && p.preco().equals("00,00") == false) {
				Pagamento pagamento = new Pagamento(this, linguas);
				pCentro.removeAll();
				pCentro.add(lIcone);
				repaint();
				pagamento.janelaPagamento(codigoComprar, p.preco());

				// Verifica se o pagamento foi concluido.
				if (pagamento.operacaoSucesso() == true) {
					compraAtiva--;
					botoes[0].setText(linguas.getString("Janela.NomeBotoes.IPe"));
					p = new Pedido(linguas);
					JOptionPane.showMessageDialog(null, linguas.getString("Janela.mensagem2"),
							linguas.getString("mensagem.padrao"), 1);
				} else {
					JOptionPane.showMessageDialog(null, linguas.getString("Janela.mensagem3"),
							linguas.getString("mensagens.alerta"), 2);
				}
			} else if (opc == 0) {
				JOptionPane.showMessageDialog(null, linguas.getString("Janela.mensagem4"),
						linguas.getString("mensagens.alerta"), 2);
			}
		}

		// Adiciona itens ao pedido
		if (e.getSource() == botoes[3]) {
			try {
				int codigo = Integer.parseInt(tAdicionar.getText());
				p.adicionaCodigo(codigo);
				tAdicionar.setText("");
				aTexto.setText(p.atualizaListaPedido());

				// Verifica se o código invormado esta cadastrado
				if (p.codigoValido(codigo) == false) {
					JOptionPane.showMessageDialog(null, linguas.getString("Janela.mensagem5"),
							linguas.getString("mensagens.erro"), 0);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, linguas.getString("Janela.mensagem5"),
						linguas.getString("mensagens.erro"), 0);
			}
		}

		// Remove itens do pedido
		if (e.getSource() == botoes[4]) {
			try {
				int codigo = Integer.parseInt(tAdicionar.getText());
				tAdicionar.setText("");
				if (p.removeCodigo(codigo) == true) {
					aTexto.setText(p.atualizaListaPedido());
				} else {
					JOptionPane.showMessageDialog(null, linguas.getString("Janela.mensagem5"),
							linguas.getString("mensagens.erro"), 0);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, linguas.getString("Janela.mensagem5"),
						linguas.getString("mensagens.erro"), 0);
			}
		}

		// Volta para a tela inicial
		if (e.getSource() == botoes[5]) {
			voltar();
		}

		// Confirma se o usuário deseja encerrar o aplicativo
		if (e.getSource() == itens[1]) {
			int opc = JOptionPane.showConfirmDialog(null, linguas.getString("Janela.mensagem6"),
					linguas.getString("mensagem.padrao"), 0);
			if (opc == 0) {
				System.exit(0);
			}
		}

		// Faz o login
		if (e.getSource() == itens[0]) {
			if (loginAtivo == false) {
				Usuario u = new Usuario(this, linguas, 0);
				loginAtivo = u.status();
				if (loginAtivo == true) {
					itens[0].setText(linguas.getString("Janela.itens.Logoff"));
					iIdiomas.setEnabled(true);
				}
			} else {
				loginAtivo = false;
				itens[0].setText(linguas.getString("Janela.itens.Login"));
				iIdiomas.setEnabled(false);
				JOptionPane.showMessageDialog(null, linguas.getString("Janela.mensagem7"),
						linguas.getString("mensagem.padrao"), 1);
			}
		}

		// Muda o idioma
		if (e.getSource() == idioma[1]) {
			selecionaIdioma(1);
			trocaTextos();
			p.trocaIdioma(linguas);
			voltar();
		}
		if (e.getSource() == idioma[0]) {
			selecionaIdioma(2);
			trocaTextos();
			p.trocaIdioma(linguas);
			voltar();
		}
		if (e.getSource() == idioma[2]) {
			selecionaIdioma(3);
			trocaTextos();
			p.trocaIdioma(linguas);
			voltar();
		}
	}
}