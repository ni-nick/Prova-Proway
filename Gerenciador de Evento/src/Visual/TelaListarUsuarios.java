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
import Entidades.Usuario;

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

public class TelaListarUsuarios extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	
	ConectaDataBase conectaDB = new ConectaDataBase();
	

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarUsuarios frame = new TelaListarUsuarios();
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
	public TelaListarUsuarios() {
		setTitle("Lista de Usu\u00E1rios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 309, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.conectaDB.CriaConexao();
		
		comboBox = new JComboBox();
		comboBox.setBounds(45, 44, 198, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Lista de Usu\u00E1rios Cadastrados");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(58, 19, 192, 14);
		contentPane.add(lblNewLabel);
		
		JButton bntVoltar = new JButton("Voltar");
		bntVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaGerenciarUsuario gerenciaUsuario = new TelaGerenciarUsuario();
				gerenciaUsuario.setVisible(true);
				
				setVisible(false);
			}
		});
		bntVoltar.setBounds(194, 255, 89, 23);
		contentPane.add(bntVoltar);
	
		PreencheLista();
		
	}
	
	private void PreencheLista () {	

		listaUsuarios = this.conectaDB.CarregaTodosUsuarios();

		for (int index = 0; index < listaUsuarios.size(); index++)
			comboBox.addItem(listaUsuarios.get(index).getNome() + " " + listaUsuarios.get(index).getSobreNome());				

		return;

	}
}
