package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaGerenciaVinculacaoUsuarioSala extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerenciaVinculacaoUsuarioSala frame = new TelaGerenciaVinculacaoUsuarioSala();
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
	public TelaGerenciaVinculacaoUsuarioSala() {
		setTitle("Gerenciador de Vincula\u00E7\u00E3o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Vincula Usu\u00E1rio a Sala");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaVinculaUsuarioeSala vinculaUsuSala = new TelaVinculaUsuarioeSala();
				vinculaUsuSala.setVisible(true);
				
				setVisible(false);
			}
		});
		btnNewButton.setBounds(66, 59, 183, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exibe Usu\u00E1rio na Sala");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaExibiUsuarioSala exibeUsuSala = new TelaExibiUsuarioSala();
				exibeUsuSala.setVisible(true);
				
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(66, 123, 183, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaInicio inicio = new TelaInicio();
				inicio.setVisible(true);
				
				setVisible(false);
			}
		});
		btnVoltar.setBounds(66, 191, 183, 29);
		contentPane.add(btnVoltar);
	}

}
