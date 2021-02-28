package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexao.ConectaDataBase;
import Entidades.Estabelecimento;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaListarEstabelecimentos extends JFrame {

	private JPanel contentPane;
	private JComboBox cbEstabelecimento;
	private ArrayList<Estabelecimento> listarEstabele = new ArrayList<Estabelecimento>();
	
	ConectaDataBase conectaDB = new ConectaDataBase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarEstabelecimentos frame = new TelaListarEstabelecimentos();
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
	public TelaListarEstabelecimentos() {
		setTitle("Lista Estabelecimentos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 366, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.conectaDB.CriaConexao();
		
		
		cbEstabelecimento = new JComboBox();
		cbEstabelecimento.setBounds(33, 56, 287, 20);
		contentPane.add(cbEstabelecimento);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaGerenciaIntervalo espacoCafe = new TelaGerenciaIntervalo();
				espacoCafe.setVisible(true);
				
				setVisible(false);
			}
		});
		btnVoltar.setBounds(251, 269, 89, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblListaDeEstabelecimentos = new JLabel("                  Lista de Estabelecimentos Cadastrados");
		lblListaDeEstabelecimentos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblListaDeEstabelecimentos.setBounds(10, 31, 287, 14);
		contentPane.add(lblListaDeEstabelecimentos);
		
		PreencheCBestabelecimento();
	}
	
	private void PreencheCBestabelecimento() {
		
		listarEstabele = this.conectaDB.CarregaTodosEstabelecimentos();
		
		for(int index = 0; index < listarEstabele.size(); index++)
			cbEstabelecimento.addItem(listarEstabele.get(index).getNome()+"      - Lotação: "+listarEstabele.get(index).getLotacao());
		
		return;
	}
}
