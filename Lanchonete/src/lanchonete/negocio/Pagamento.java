package lanchonete.negocio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

public class Pagamento extends JDialog implements ActionListener {

	/**
	 * Atributo
	 */
	private static final long serialVersionUID = -103330926890896592L;
	/**
	 * Atributo
	 */
	@SuppressWarnings("unused")
	private JLabel lNome, lCodigoCartao, lSenha, lCodigo, lPreco;
	/**
	 * Atributo
	 */
	private JTextField tNome, tCodigoCartao;
	/**
	 * Atributo
	 */
	private JPasswordField tSenha;
	/**
	 * Atributo
	 */
	private JButton lOk, lCancelar;
	/**
	 * Atributo
	 */
	private boolean completo = false;
	/**
	 * Atributo
	 */
	private ResourceBundle linguas;

	public Pagamento(JFrame fr, ResourceBundle linguas) {
		super(fr, true);
		this.linguas = linguas;
	}

	public void janelaPagamento(int codigo, String preco) {

		lCodigo = new JLabel(linguas.getString("pag.Codigo") + codigo);
		lPreco = new JLabel(linguas.getString("pag.Preco") + preco);

		setTitle(linguas.getString("pag"));
		setSize(450, 300);
		setLocation(500, 150);

		Container j = getContentPane();
		j.setLayout(new BorderLayout());

		JPanel pCima = new JPanel(new FlowLayout(1));
		pCima.setBackground(new Color(0, 0, 0));

		JPanel pCentro = new JPanel(new GridLayout(0, 2, 1, 1));
		pCentro.setBackground(new Color(255, 250, 250));
		pCentro.add(lNome = new JLabel(linguas.getString("pag.Nome")));
		pCentro.add(tNome = new JTextField(15));
		pCentro.add(lCodigoCartao = new JLabel(linguas.getString("pag.Cartao")));
		pCentro.add(tCodigoCartao = new JTextField(15));
		pCentro.add(lSenha = new JLabel(linguas.getString("pag.Senha")));
		pCentro.add(tSenha = new JPasswordField(15));
		pCentro.add(lCodigo);
		pCentro.add(lPreco);

		JPanel pBaixo = new JPanel(new FlowLayout(1));
		pBaixo.setBackground(new Color(0, 0, 0));
		pBaixo.add(lOk = new JButton(linguas.getString("pag.botao.ok")));
		lOk.setBackground(new Color(63, 81, 181));
		lOk.setForeground(new Color(255, 255, 255));
		lOk.addActionListener(this);
		pBaixo.add(lCancelar = new JButton(linguas.getString("pag.botao.con")));
		lCancelar.setBackground(new Color(63, 81, 181));
		lCancelar.setForeground(new Color(255, 255, 255));
		lCancelar.addActionListener(this);

		j.add(pCima, BorderLayout.NORTH);
		j.add(pCentro, BorderLayout.CENTER);
		j.add(pBaixo, BorderLayout.SOUTH);

		setVisible(true);
	}

	public boolean operacaoSucesso() {
		return completo;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == lCancelar) {
			dispose();
		}
		if (e.getSource() == lOk) {

			if (tCodigoCartao.getText().isEmpty() || new String(tSenha.getPassword()).isEmpty()
					|| tNome.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, linguas.getString("pag.mensagem1"),
						linguas.getString("mensagens.erro"), 0);
			} else {
				completo = true;
				dispose();
			}
		}
	}
}