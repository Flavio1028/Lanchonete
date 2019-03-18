package lanchonete.negocio;

/*
 * @author flavio.rocha
 */
public class Codigos {

	/**
	 * Atributo
	 */
	private int codigosPedidos[];
	/**
	 * Atributo
	 */
	@SuppressWarnings("unused")
	private int inicio, fim, quantidade;
	
	/**
	 * Construtor
	 */
	public Codigos() {
		codigosPedidos = new int[100];
		inicio = fim = quantidade = 0;
	}
	
	/**
	 * @return Int
	 */
	public int geraCodigo() {
		int codigo = 0;

		do {

			codigo = (int) (Math.random() * 1000);

		} while (codigo < 100 || verificaRepetido(codigo) == true);
		adicionarNaFila(codigo);

		return codigo;
	}
	
	/**
	 * 
	 * @param codigo
	 */
	public void adicionarNaFila(int codigo) {
		codigosPedidos[fim] = codigo;
		fim++;
		quantidade++;
		if (fim == codigosPedidos.length) {
			fim = 0;
		}
	}
	
	/**
	 * @param codigo
	 * @return
	 */
	public boolean verificaRepetido(int codigo) {
		for (int i = 0; i < codigosPedidos.length; i++) {
			if (codigosPedidos[i] == codigo) {
				return true;
			}
		}

		return false;
	}
}