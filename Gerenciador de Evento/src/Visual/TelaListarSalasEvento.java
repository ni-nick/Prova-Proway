package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexao.ConectaDataBase;
import Entidades.SalaEvento;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaListarSalasEvento extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private ArrayList<SalaEvento> listarSalas = new ArrayList<SalaEvento>();
	
	ConectaDataBase conectaDB = new ConectaDataBase();
	

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarSalasEvento frame = new TelaListarSalasEvento();
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
	public TelaListarSalasEvento() {
		setTitle("Lista Salas do Evento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 309, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.conectaDB.CriaConexao();
		
		comboBox = new JComboBox();
		comboBox.setBounds(24, 59, 248, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Lista de Salas Cadastradas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(66, 27, 192, 14);
		contentPane.add(lblNewLabel);
		
		JButton bntVoltar = new JButton("Voltar");
		bntVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaGerenciaSalaEvento gerenciaSala = new TelaGerenciaSalaEvento();
				gerenciaSala.setVisible(true);
				
				setVisible(false);
			}
		});
		bntVoltar.setBounds(194, 255, 89, 23);
		contentPane.add(bntVoltar);
	
		PreencheListaSalas();
		
	}
	
	private void PreencheListaSalas () {	

		listarSalas = this.conectaDB.CarregaTodasSalas();

		for (int index = 0; index < listarSalas.size(); index++)
			comboBox.addItem(listarSalas.get(index).getNome() +"      - Lotação: "+ listarSalas.get(index).getLotacao());				

		return;

	}
}
