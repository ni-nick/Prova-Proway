package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaGerenciarUsuario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerenciarUsuario frame = new TelaGerenciarUsuario();
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
	public TelaGerenciarUsuario() {
		setTitle("Gerencia usu\u00E1rios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 273);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton bntListaUsuarios = new JButton("Listar Usuarios");
		bntListaUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaListarUsuarios listaUsuarios = new TelaListarUsuarios();
				listaUsuarios.setVisible(true);
				
				setVisible(false);
			}
		});
		bntListaUsuarios.setBounds(60, 136, 148, 23);
		contentPane.add(bntListaUsuarios);
		
		JButton bntCadastroUsuario = new JButton("Cadastrar Usu\u00E1rio");
		bntCadastroUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCadastroUsuario cadastroUsuario = new TelaCadastroUsuario();
				cadastroUsuario.setVisible(true);
				
				setVisible(false);
			}
		});
		bntCadastroUsuario.setBounds(60, 31, 148, 23);
		contentPane.add(bntCadastroUsuario);
		
		JButton bntAlteraUsuario = new JButton("Alterar Usu\u00E1rio");
		bntAlteraUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaAlterarUsuario alteraUsuario = new TelaAlterarUsuario();
				alteraUsuario.setVisible(true);
				
				setVisible(false);
			}
		});
		bntAlteraUsuario.setBounds(60, 82, 148, 23);
		contentPane.add(bntAlteraUsuario);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaInicio telaInicio = new TelaInicio();
				telaInicio.setVisible(true);
				
				setVisible(false);
			}
		});
		btnVoltar.setBounds(60, 182, 148, 23);
		contentPane.add(btnVoltar);
	}
}
