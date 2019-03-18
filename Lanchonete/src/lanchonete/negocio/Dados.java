package lanchonete.negocio;

/**
 * @author flavio.rocha
 */
public class Dados {

	/**
	 * Atributo
	 */
	private String nomeUsuario;
	/**
	 * Atributo
	 */
	private String senhaUsuario;

	// Construtores
	public Dados() {
		// Nenhuma ação e necessária aqui
	}

	public Dados(String nomeUsuario, String senhaUsuario) {
		this.nomeUsuario = nomeUsuario;
		this.senhaUsuario = senhaUsuario;
	}

	// Métodos de acesso e modficadores
	public void setNome(String nome) {
		nomeUsuario = nome;
	}

	public String getNome() {
		return nomeUsuario;
	}

	public void setSenha(String senha) {
		senhaUsuario = senha;
	}

	public String getSenha() {
		return senhaUsuario;
	}

}