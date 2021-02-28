package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaGerenciaVinculacaoEstabeleUsuario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerenciaVinculacaoEstabeleUsuario frame = new TelaGerenciaVinculacaoEstabeleUsuario();
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
	public TelaGerenciaVinculacaoEstabeleUsuario() {
		setTitle("Gerencia vincula\u00E7\u00E3o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 368, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Vincula Usu\u00E1rio ao Estabelecimento");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaVinculaUsuarioeEstabeleciemento vinculaEstabeUsu = new TelaVinculaUsuarioeEstabeleciemento();
				vinculaEstabeUsu.setVisible(true);
				
				setVisible(false);
			}
		});
		btnNewButton.setBounds(60, 43, 238, 33);
		contentPane.add(btnNewButton);
		
		JButton btnExibeUsurioNo = new JButton("Exibe Usu\u00E1rio no Estabelecimento");
		btnExibeUsurioNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaExibeUsuarioEstabelecimento exibe = new TelaExibeUsuarioEstabelecimento();
				exibe.setVisible(true);
				
				setVisible(false);
			}
		});
		btnExibeUsurioNo.setBounds(60, 120, 238, 33);
		contentPane.add(btnExibeUsurioNo);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaInicio inicio = new TelaInicio();
				inicio.setVisible(true);
				
				setVisible(false);
			}
		});
		btnVoltar.setBounds(60, 194, 238, 33);
		contentPane.add(btnVoltar);
	}

}
