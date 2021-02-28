package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaGerenciaSalaEvento extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerenciaSalaEvento frame = new TelaGerenciaSalaEvento();
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
	public TelaGerenciaSalaEvento() {
		setTitle("Gerencia  salas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 298, 271);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton bntCadastrarSala = new JButton("Cadastrar Sala");
		bntCadastrarSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCadastroSalaEvento salaEvento = new TelaCadastroSalaEvento();
				salaEvento.setVisible(true);
				
				setVisible(false);
			}
		});
		bntCadastrarSala.setBounds(84, 28, 124, 23);
		contentPane.add(bntCadastrarSala);
		
		JButton bntAlterarSala = new JButton("Alterar Sala");
		bntAlterarSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaAlterarSalaEvento alteraSala = new TelaAlterarSalaEvento();
				alteraSala.setVisible(true);
				
				setVisible(false);
			}
		});
		bntAlterarSala.setBounds(84, 76, 124, 23);
		contentPane.add(bntAlterarSala);
		
		JButton bntListarSalas = new JButton("Listar Salas");
		bntListarSalas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaListarSalasEvento listarsalas = new TelaListarSalasEvento();
				listarsalas.setVisible(true);
				
				setVisible(false);
			}
		});
		bntListarSalas.setBounds(84, 129, 124, 23);
		contentPane.add(bntListarSalas);
		
		JButton bntVoltar = new JButton("Voltar");
		bntVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaInicio telaInicio = new TelaInicio();
				telaInicio.setVisible(true);
				
				setVisible(false);
			}
		});
		bntVoltar.setBounds(84, 181, 124, 23);
		contentPane.add(bntVoltar);
	}

}
