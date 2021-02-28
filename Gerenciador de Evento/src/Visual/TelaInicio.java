package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexao.ConectaDataBase;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInicio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicio frame = new TelaInicio();
					ConectaDataBase conectaDB = new ConectaDataBase();
					conectaDB.CriaConexao();
					conectaDB.CriaTabelaUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 368, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton bntPessoas = new JButton("Gerenciador de Usu\u00E1rios");
		bntPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaGerenciarUsuario telaUsuario = new TelaGerenciarUsuario();
				telaUsuario.setVisible(true);
				
				setVisible(false);
				
			}
		});
		bntPessoas.setBounds(78, 50, 211, 33);
		contentPane.add(bntPessoas);
		
		JButton bntSalas = new JButton("Gerenciador de Salas");
		bntSalas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaGerenciaSalaEvento gerenciaSala = new TelaGerenciaSalaEvento();
				gerenciaSala.setVisible(true);
				
				setVisible(false);
			}
		});
		bntSalas.setBounds(78, 94, 211, 34);
		contentPane.add(bntSalas);
		
		JButton bntIntervalos = new JButton("Gerenciador de Intervalos");
		bntIntervalos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciaIntervalo intervaloCafe = new TelaGerenciaIntervalo();
				intervaloCafe.setVisible(true);
				
				setVisible(false);
			}
		});
		bntIntervalos.setBounds(78, 139, 211, 33);
		contentPane.add(bntIntervalos);
		
		JLabel lblBemvindoAoEvento = new JLabel("Seja Bem-Vindo ao Evento");
		lblBemvindoAoEvento.setFont(new Font("Cambria", Font.BOLD, 15));
		lblBemvindoAoEvento.setBounds(88, 11, 181, 14);
		contentPane.add(lblBemvindoAoEvento);
		
		JButton btnInsereUsuarioNa = new JButton("Gerenciador de Usu\u00E1rio Sala");
		btnInsereUsuarioNa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciaVinculacaoUsuarioSala vinculaUsuarioSala = new TelaGerenciaVinculacaoUsuarioSala();
				vinculaUsuarioSala.setVisible(true);
				
				setVisible(false);
			}
		});
		btnInsereUsuarioNa.setBounds(78, 183, 211, 33);
		contentPane.add(btnInsereUsuarioNa);
		
		JButton btnInsereUsuarioNo = new JButton("Gerenciador Usu\u00E1rio Intervalo");
		btnInsereUsuarioNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciaVinculacaoEstabeleUsuario vincula = new TelaGerenciaVinculacaoEstabeleUsuario();
				vincula.setVisible(true);
				
				setVisible(false);
			}
		});
		btnInsereUsuarioNo.setBounds(78, 227, 211, 33);
		contentPane.add(btnInsereUsuarioNo);
	}
}
