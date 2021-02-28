package Entidades;

public class Estabelecimento {

	private String nome;
	private String lotacao;
	private int IdCafe;
	
	public int getIdCafe() {
		return IdCafe;
	}
	public void setIdCafe(int idCafe) {
		IdCafe = idCafe;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLotacao() {
		return lotacao;
	}
	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}
}
