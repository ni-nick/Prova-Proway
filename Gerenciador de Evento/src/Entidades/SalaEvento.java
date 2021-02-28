package Entidades;

public class SalaEvento {

	private String nome;
	private String lotacao;
	private int IdSalaEvento;
	
	public int getIdSalaEvento() {
		return IdSalaEvento;
	}
	public void setIdSalaEvento(int idSalaEvento) {
		IdSalaEvento = idSalaEvento;
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
