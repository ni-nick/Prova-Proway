package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import Entidades.Estabelecimento;
import Entidades.SalaEvento;
import Entidades.Usuario;

public class ConectaDataBase {

	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement ps = null;
	private ResultSet resultSet = null;

	public void CriaConexao()
	{
		String url = "jdbc:mysql://127.0.0.1:3306/";
		String user = "root";
		String senha = "123456";


		try {
			this.connection = DriverManager.getConnection(url, user, senha);
			this.statement = this.connection.createStatement();

			//criando database
			String query = "CREATE DATABASE IF NOT EXISTS GerenciadorEvento;";
			String useDB = "USE  GerenciadorEvento;";
			int resultSet = this.statement.executeUpdate(query);
			resultSet = this.statement.executeUpdate(useDB);
			this.statement = this.connection.createStatement();

		} catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
		}
	}

	
//	--------------USUÁRIO--------------------------------------------------------------------------------------------------------------------
	
	public void CriaTabelaUsuario() {

		try {
			String query = "create table if not exists Usuario (idUsuario int primary key auto_increment, nome varchar(50), sobrenome varchar(50), idSalaEvento int, idEstabelecimento int);";
			int resultSet = this.statement.executeUpdate(query);
			this.statement = this.connection.createStatement();

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
		}

	}

	public boolean CadastraUsuario(Usuario usuario) {	

		try {
			String query = "insert into Usuario (nome, sobrenome) values ('" + usuario.getNome() + "', '" + usuario.getSobreNome() + "');";
			int resultSet = this.statement.executeUpdate(query);
			this.statement = this.connection.createStatement();
			return true;

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
			return false;
		}
	}

	public ArrayList<Usuario> CarregaTodosUsuarios(){
		
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();


		try {			
			String query = "SELECT * FROM Usuario;";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();						

			while (this.resultSet.next())
			{
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(Integer.parseInt(this.resultSet.getString("idUsuario")));
				usuario.setNome(this.resultSet.getString("nome"));
				usuario.setSobreNome(this.resultSet.getString("sobrenome"));

				listaUsuarios.add(usuario);
			}

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());			
		}

		return listaUsuarios;	
	}

	public boolean AlteraUsuario(Usuario usuario) {

		try {			
			String query = "update Usuario set nome = '" + usuario.getNome() + "', sobrenome = '" + usuario.getSobreNome() + "' where idUsuario = "+ usuario.getIdUsuario();
			//this.resultSet = this.statement.executeQuery(query);  troquei isso pelo de baixo de foi a alteração
			int resultSet = this.statement.executeUpdate(query);
			this.statement = this.connection.createStatement();						
			return true;


		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
			return false;
		}

	}

	public boolean ExcluiUsuario(Usuario IdUsuario) {

		try {			
			String query = "delete from Usuario where idUsuario = "+ IdUsuario.getIdUsuario();
			int resultSet = this.statement.executeUpdate(query);
			this.statement = this.connection.createStatement();						
			return true;


		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
			return false;
		}



	}
	
	
//	---------------SALAS EVENTO---------------------------------------------------------------------------------------------------------------------------------------------

	public void CriaTabelaSalaEvento() {

		try {
			String query = "create table if not exists SalaEvento (idSalaEvento int primary key auto_increment, nome varchar(50), lotacao int;";
			int resultSet = this.statement.executeUpdate(query);
			this.statement = this.connection.createStatement();

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
		}

	}

	public boolean CadastrarSalaEvento(SalaEvento salaevento) {

		try {
			String query = "insert into SalaEvento (nome, lotacao) values ('" + salaevento.getNome() + "', '" + salaevento.getLotacao() + "');";
			int resultSet = this.statement.executeUpdate(query);
			this.statement = this.connection.createStatement();
			return true;

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
			return false;
		}
	}

	public ArrayList<SalaEvento> CarregaTodasSalas(){

		ArrayList<SalaEvento> listarSalas = new ArrayList<SalaEvento>();

		try {			
			String query = "SELECT * FROM SalaEvento;";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();						

			while (this.resultSet.next())
			{
				SalaEvento salaevento = new SalaEvento();
				salaevento.setIdSalaEvento(Integer.parseInt(this.resultSet.getString("idSalaEvento")));
				salaevento.setNome(this.resultSet.getString("nome"));
				salaevento.setLotacao(this.resultSet.getString("lotacao"));

				listarSalas.add(salaevento);
			}

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());			
		}

		return listarSalas;
	}

	public boolean AlteraSalaEvento(SalaEvento salaevento) {

		try {			
			String query = "update SalaEvento set nome = '" + salaevento.getNome() + "', lotacao = '" + salaevento.getLotacao() + "' where idSalaEvento = "+ salaevento.getIdSalaEvento();
			int resultSet = this.statement.executeUpdate(query);
			this.statement = this.connection.createStatement();						
			return true;


		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
			return false;
		}

	}

	public boolean ExcluiSalaEvento(SalaEvento IdSalaEvento) {

		try {			
			String query = "delete from SalaEvento where IdSalaEvento = "+ IdSalaEvento.getIdSalaEvento();
			int resultSet = this.statement.executeUpdate(query);
			this.statement = this.connection.createStatement();						
			return true;


		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
			return false;
		}



	}
	
	
	
//	--------------INTERVALO CAFÉ--------------------------------------------------------------------------------------------------------------------

	public void CriaTabelaEstabelecimento() {
		
		try {
			String query = "create table if not exists Estabelecimento (idEstabelecimento int primary key auto_increment, nome varchar(50), lotacao int;";
			int resultSet = this.statement.executeUpdate(query);
			this.statement = this.connection.createStatement();

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
		}

		
	}
	
	public boolean CadastrarEstabelecimento(Estabelecimento espacoCafe) {
		
		try {
			String query = "insert into Estabelecimento (nome, lotacao) values ('" + espacoCafe.getNome() + "', '" + espacoCafe.getLotacao() + "');";
			int resultSet = this.statement.executeUpdate(query);
			this.statement = this.connection.createStatement();
			return true;

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
			return false;
		}
	}
	
	public ArrayList<Estabelecimento> CarregaTodosEstabelecimentos(){
		
		ArrayList<Estabelecimento> listarEstabele = new ArrayList<Estabelecimento>();

		try {			
			String query = "SELECT * FROM Estabelecimento;";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();						

			while (this.resultSet.next())
			{
				Estabelecimento espacoCafe = new Estabelecimento();
				espacoCafe.setIdCafe(Integer.parseInt(this.resultSet.getString("idEstabelecimento")));
				espacoCafe.setNome(this.resultSet.getString("nome"));
				espacoCafe.setLotacao(this.resultSet.getString("lotacao"));

				listarEstabele.add(espacoCafe);
			}

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());			
		}

		return listarEstabele;
		
		
	}
	
	public boolean AlterarEstabelecimento(Estabelecimento espacoCafe) {
		
		try {			
			String query = "update Estabelecimento set nome = '" + espacoCafe.getNome() + "', lotacao = '" + espacoCafe.getLotacao() + "' where idEstabelecimento = "+ espacoCafe.getIdCafe();
			int resultSet = this.statement.executeUpdate(query);
			this.statement = this.connection.createStatement();						
			return true;


		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
			return false;
		}
		
	}
	
	public boolean ExcluirEstabelecimento(Estabelecimento espacoCafe) {
		
		try {			
			String query = "delete from Estabelecimento where idEstabelecimento = "+ espacoCafe.getIdCafe();
			int resultSet = this.statement.executeUpdate(query);
			this.statement = this.connection.createStatement();						
			return true;


		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
			return false;
		}
	}
	
//-------------Usuário na Sala--------------------------------------------------------------------------------------------
	public boolean VinculaSalaUsuario(int idUsuario, int idSalaEvento) {

		try {			
			String query = "update Usuario set idSalaEvento = " + idSalaEvento + " where idUsuario = "+ idUsuario;
			int resultSet = this.statement.executeUpdate(query);
			this.statement = this.connection.createStatement();						
			return true;

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
			return false;
		}		
	}
	
	public ArrayList<Usuario> CarregaUsuarioNaSala(int idSalaEvento) {
		
		ArrayList<Usuario> listarUsuarioSala = new ArrayList<Usuario>();

		try {			
			String query = "SELECT * FROM Usuario where idSalaEvento =" +idSalaEvento;
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();						

			while (this.resultSet.next())
			{
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(Integer.parseInt(this.resultSet.getString("idUsuario")));
				usuario.setNome(this.resultSet.getString("nome"));
				usuario.setSobreNome(this.resultSet.getString("Sobrenome"));

				listarUsuarioSala.add(usuario);
			}

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());			
		}
		
		return listarUsuarioSala;		
	}
	
	public int GetQuantidadeUsuarioSala(int idSalaEvento) {
		
		try {		
			int qtdUsuario = 0;
					
			String query = "select count(*) QTDusuario from Usuario where idSalaEvento = " + idSalaEvento +";";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();				
			
			while (this.resultSet.next())
			{
				qtdUsuario = Integer.parseInt(this.resultSet.getString("QTDusuario"));
			}
							
			return qtdUsuario;

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
			return -1;
		}
	}
	

//-------------------------Usuário no Estabelecimento---------------------------------------------------------------------	
	public int GetQuantidadeUsuarioEstabelecimento(int idEstabelecimento) {
		
		try {		
			int qtdUsuario= 0;
					
			String query = "select count(*) QTDusuario from Usuario where idEstabelecimento = " + idEstabelecimento +";";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();				
			
			while (this.resultSet.next())
			{
				qtdUsuario = Integer.parseInt(this.resultSet.getString("QTDusuario"));
			}
							
			return qtdUsuario;

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
			return -1;
		}
		
	}
	
	public boolean VinculaEstabelecimentoUsuario(int idUsuario, int idEstabelecimento) {

		try {			
			String query = "update Usuario set idEstabelecimento = " + idEstabelecimento + " where idUsuario = "+ idUsuario;
			int resultSet = this.statement.executeUpdate(query);
			this.statement = this.connection.createStatement();						
			return true;

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());
			return false;
		}		
	}
	
public ArrayList<Usuario> CarregaUsuarioNoEstabelecimento(int idEstabelecimento) {
		
		ArrayList<Usuario> listarUsuarioEstabele = new ArrayList<Usuario>();

		try {			
			String query = "SELECT * FROM Usuario where idEstabelecimento =" +idEstabelecimento;
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();						

			while (this.resultSet.next())
			{
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(Integer.parseInt(this.resultSet.getString("idUsuario")));
				usuario.setNome(this.resultSet.getString("nome"));
				usuario.setSobreNome(this.resultSet.getString("Sobrenome"));

				listarUsuarioEstabele.add(usuario);
			}

		}catch (SQLException e) {
			System.out.println("error:  " + e.getMessage());			
		}
		
		return listarUsuarioEstabele;		
	}
}