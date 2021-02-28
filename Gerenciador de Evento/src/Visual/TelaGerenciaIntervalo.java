package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaGerenciaIntervalo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerenciaIntervalo frame = new TelaGerenciaIntervalo();
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
	public TelaGerenciaIntervalo() {
		setTitle("Gerencia intervalos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 302, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Cadastrar Estabelecimento");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaCadastroCafe intervalo = new TelaCadastroCafe();
				intervalo.setVisible(true);
				
				setVisible(false);
			}
		});
		btnNewButton.setBounds(41, 35, 198, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Alterar Estabelecimento");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaAlterarEstabelecimentos alteraEstabele = new TelaAlterarEstabelecimentos();
				alteraEstabele.setVisible(true);
				
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(41, 93, 198, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Listar Estabelecimentos");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaListarEstabelecimentos listaEstabele = new TelaListarEstabelecimentos();
				listaEstabele.setVisible(true);
				
				setVisible(false);
			}
		});
		btnNewButton_2.setBounds(41, 152, 198, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Voltar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaInicio inicio = new TelaInicio();
				inicio.setVisible(true);
				
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(41, 209, 198, 23);
		contentPane.add(btnNewButton_3);
	}
}
