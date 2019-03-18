package lanchonete.negocio;

import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author flavio.rocha
 */
public class Usuario extends JDialog implements ActionListener {

	/**
	 * Atributo
	 */
	private static final long serialVersionUID = 3570219644182415083L;
	/**
	 * Atributo
	 */
	private FileWriter arquivo;
	/**
	 * Atributo
	 */
	private PrintWriter gravar;
	/**
	 * Atributo
	 */
	@SuppressWarnings("unused")
	private Scanner leitura, entrada;
	/**
	 * Atributo
	 */
	private JButton bOK, bCriar;
	/**
	 * Atributo
	 */
	private JTextField tUsuario;
	/**
	 * Atributo
	 */
	private JPasswordField tSenha;
	/**
	 * Atributo
	 */
	private boolean status;
	/**
	 * Atributo
	 */
	private ResourceBundle linguas;
	/**
	 * Atributo
	 */
	private ArrayList<Dados> dados = new ArrayList<Dados>();

	Dados d = new Dados();

	public Usuario(JFrame fr, ResourceBundle linguas, int operacao) {
		super(fr, true);
		this.linguas = linguas;
		boolean a = abreArquivo();
		leArquivo();

		if (a == true)
			JanelaLogin(operacao);
	}

	// Criar o arquivo para garvar o usuário e a senha
	public void criaArquivo() {
		try {
			arquivo = new FileWriter("_login/Usuario.txt", true);
			gravar = new PrintWriter(arquivo);

		} catch (SecurityException excecao) {
			JOptionPane.showMessageDialog(null, linguas.getString("usuario.mensagem5"),
					linguas.getString("mensagens.alerta"), 2);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, linguas.getString("usuario.mensagem6"),
					linguas.getString("mensagens.alerta"), 2);
		}
	}

	// Salva o usuário e a senha no arquivo
	public boolean criaUsuario(String usuario, String senha) {
		boolean saida = false;

		try {
			d.setNome(usuario);
			d.setSenha(senha);
			criaArquivo();
			gravar.printf("%s %s%n", d.getNome(), d.getSenha());
			fechaArquivo();
			saida = true;
		} catch (Exception excecao) {
			// excecao.printStackTrace();
		}

		return saida;
	}

	// Abre o arquivo
	public boolean abreArquivo() {
		boolean saida = false;
		try {
			leitura = new Scanner(new File("_login/Usuario.txt"));
			saida = true;
		} catch (FileNotFoundException excecao) {
			JOptionPane.showMessageDialog(null, linguas.getString("usuario.mensagem7"),
					linguas.getString("mensagem.padrao"), 1);
			criaArquivo();
			abreArquivo();
			JanelaLogin(1);
			saida = false;
		}
		return saida;
	}

	// Carrega os dados do arquivo para a memoria
	public void leArquivo() {
		try {

			while (leitura.hasNext() == true) {
				dados.add(new Dados(leitura.next(), leitura.next()));
			}

			leitura.close();

		} catch (NoSuchElementException excecao) {
			JOptionPane.showMessageDialog(null, linguas.getString("usuario.mensagem8"),
					linguas.getString("mensagens.erro"), 0);
			fechaArquivo();
		} catch (IllegalStateException excecao) {
			JOptionPane.showMessageDialog(null, linguas.getString("usuario.mensagem9"),
					linguas.getString("mensagens.erro"), 0);
			fechaArquivo();
		}
	}

	// Fecha o arquivo TXT
	public void fechaArquivo() {
		if (arquivo != null) {
			try {
				arquivo.close();
			} catch (IOException excecao) {
				// excecao.printStackTrace();
			}
		}
	}

	// Efetua o login do usuário
	public boolean login(String usuario, String senha) {
		Dados da;
		Iterator<Dados> achaLogin = dados.iterator();

		while (achaLogin.hasNext() == true) {
			da = achaLogin.next();
			if (da.getNome().equals(usuario) && da.getSenha().equals(senha)) {
				return true;
			}
		}
		return false;
	}

	// Tela de Login
	public void JanelaLogin(int operacao) {
		// Itens básicos de uma Janela
		setTitle(linguas.getString("usuario"));
		setSize(300, 180);
		setLocation(750, 250);
		setResizable(false);
		// Container
		Container janela = getContentPane();
		janela.setLayout(new BorderLayout());
		// Paineis
		JPanel pCentro = new JPanel(new GridLayout(2, 2, 1, 1));
		pCentro.setBackground(new Color(255, 255, 255));
		JPanel pBaixo = new JPanel(new FlowLayout());
		pBaixo.setBackground(new Color(0, 0, 0));
		JPanel pCima = new JPanel(new FlowLayout());
		pCima.setBackground(new Color(0, 0, 0));
		// Outros itens
		tUsuario = new JTextField(10);
		tSenha = new JPasswordField(10);
		JLabel lUsuario = new JLabel(linguas.getString("usuario.us"));
		JLabel lSenha = new JLabel(linguas.getString("usuario.se"));

		if (operacao == 0) {
			bOK = new JButton(linguas.getString("usuario.botao.ok"));
			bOK.addActionListener(this);
			bOK.setBackground(new Color(63, 81, 181));
			bOK.setForeground(new Color(255, 255, 255));
			pBaixo.add(bOK);
		} else {
			bCriar = new JButton(linguas.getString("usuario.botao.ok"));
			bCriar.addActionListener(this);
			bCriar.setBackground(new Color(63, 81, 181));
			bCriar.setForeground(new Color(255, 255, 255));
			pBaixo.add(bCriar);
		}

		// Adicionando itens
		pCentro.add(lUsuario);
		pCentro.add(tUsuario);
		pCentro.add(lSenha);
		pCentro.add(tSenha);

		janela.add(pCentro, BorderLayout.CENTER);
		janela.add(pBaixo, BorderLayout.SOUTH);
		janela.add(pCima, BorderLayout.NORTH);

		setVisible(true);

	}

	public boolean status() {
		return status;
	}

	// Trada os evendos dos botoes
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bOK) {
			if (login(tUsuario.getText(), new String(tSenha.getPassword())) == true) {
				JOptionPane.showMessageDialog(null, linguas.getString("usuario.mensagem1"),
						linguas.getString("mensagem.padrao"), 1);
				status = true;
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, linguas.getString("usuario.mensagem2"),
						linguas.getString("mensagens.alerta"), 2);
				status = false;
			}
		}

		if (e.getSource() == bCriar) {
			if (criaUsuario(tUsuario.getText(), new String(tSenha.getPassword())) == true) {
				JOptionPane.showMessageDialog(null, linguas.getString("usuario.mensagem3"),
						linguas.getString("mensagem.padrao"), 1);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, linguas.getString("usuario.mensagem4"),
						linguas.getString("mensagem.erro"), 0);
				dispose();
			}
		}
	}
}