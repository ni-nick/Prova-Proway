package TrataDados;

import javax.swing.JOptionPane;

import Conexao.ConectaDataBase;
import Entidades.Estabelecimento;
import Entidades.SalaEvento;
import Entidades.Usuario;

public class TrataDados {

	ConectaDataBase conectaDB = new ConectaDataBase();


	public boolean TrataDadosUsuario(Usuario usuario) {

		if(usuario.getNome().isEmpty() && usuario.getSobreNome().isEmpty()) 
			return false;

		return true;		
	}

	public boolean TrataDadosSala(SalaEvento salaevento) {

		if(salaevento.getNome().isEmpty() && salaevento.getLotacao().isEmpty())
			return false;

		return true;

	}

	public boolean TrataDadosEstabelecimento(Estabelecimento espacoCafe) {

		if(espacoCafe.getNome().isEmpty() && espacoCafe.getLotacao().isEmpty())
			return false;

		return true;
	}


	public boolean TrataDadosVincula(int idSalaEvento, int idUsuario, int Lotacao) {

		conectaDB.CriaConexao();
		int verificaLotacao = conectaDB.GetQuantidadeUsuarioSala(idSalaEvento);

		if(verificaLotacao >= Lotacao) {
			JOptionPane.showMessageDialog(null, "Sala lotada!");
			return false;
		}

		if(idSalaEvento >=1 && idUsuario >=1)
			return true;

		return false;		
	}

	public boolean TrataDadosVinculaEstabelecimento(int idEstabelecimento, int idUsuario, int lotacao) {

		conectaDB.CriaConexao();
		int verificaLotacao = conectaDB.GetQuantidadeUsuarioEstabelecimento(idEstabelecimento);

		if(verificaLotacao >= lotacao) {
			JOptionPane.showMessageDialog(null, "Sala lotada!");
			return false;
		}

		if(idEstabelecimento >=1 && idUsuario >=1)
			return true;

		return false;
	}

}
