package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexao.ConectaDataBase;
import Entidades.SalaEvento;
import Entidades.Usuario;
import TrataDados.TrataDados;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class TelaCadastroUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtSobrenome;
	private JTextField txtNome;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroUsuario frame = new TelaCadastroUsuario();
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
	public TelaCadastroUsuario() {
		setTitle("Cadastro Usu\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel labelNome = new JLabel("Nome:");
		labelNome.setBounds(20, 48, 46, 14);
		contentPane.add(labelNome);

		JLabel labelSobrenome = new JLabel("Sobrenome:");
		labelSobrenome.setBounds(20, 82, 83, 14);
		contentPane.add(labelSobrenome);

		txtSobrenome = new JTextField();
		txtSobrenome.setBounds(113, 79, 205, 20);
		contentPane.add(txtSobrenome);
		txtSobrenome.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(113, 45, 205, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JButton bntOK = new JButton("OK");
		bntOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Usuario usuario = new Usuario();
				usuario.setNome(txtNome.getText());
				usuario.setSobreNome(txtSobrenome.getText());

				TrataDados trataDados = new TrataDados();
				ConectaDataBase conectaDB = new ConectaDataBase();
				conectaDB.CriaConexao();



				if (trataDados.TrataDadosUsuario(usuario))
				{
					if (conectaDB.CadastraUsuario(usuario)) {
						JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
						txtNome.setText("");
						txtSobrenome.setText("");
					}
					else
						JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o usuário!");
				}


			}
		});
		bntOK.setBounds(20, 154, 89, 23);
		contentPane.add(bntOK);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciarUsuario gerenciaUsuario = new TelaGerenciarUsuario();
				gerenciaUsuario.setVisible(true);
				
				setVisible(false);
			}
		});
		btnCancelar.setBounds(250, 154, 89, 23);
		contentPane.add(btnCancelar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtNome.setText("");
				txtSobrenome.setText("");
			}
		});
		btnLimpar.setBounds(134, 154, 95, 23);
		contentPane.add(btnLimpar);
		
	}

}
