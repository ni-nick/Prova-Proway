package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexao.ConectaDataBase;
import Entidades.SalaEvento;
import TrataDados.TrataDados;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroSalaEvento extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeSala;
	private JTextField txtLotacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroSalaEvento frame = new TelaCadastroSalaEvento();
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
	public TelaCadastroSalaEvento() {
		setTitle("Cadastro da Sala do Evento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNomeDaSala = new JLabel("Nome da Sala:");
		labelNomeDaSala.setBounds(10, 24, 103, 14);
		contentPane.add(labelNomeDaSala);
		
		JLabel labelLotacao = new JLabel("Lota\u00E7\u00E3o:");
		labelLotacao.setBounds(10, 52, 80, 14);
		contentPane.add(labelLotacao);
		
		txtNomeSala = new JTextField();
		txtNomeSala.setBounds(123, 21, 312, 20);
		contentPane.add(txtNomeSala);
		txtNomeSala.setColumns(10);
		
		txtLotacao = new JTextField();
		txtLotacao.setBounds(123, 49, 86, 20);
		contentPane.add(txtLotacao);
		txtLotacao.setColumns(10);
		
		JButton bntOK = new JButton("OK");
		bntOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SalaEvento salaevento = new SalaEvento();
				salaevento.setNome(txtNomeSala.getText());
				salaevento.setLotacao(txtLotacao.getText());
				
				TrataDados trataDados = new TrataDados();
				ConectaDataBase conectaDB = new ConectaDataBase();
				conectaDB.CriaConexao();
				
				if(trataDados.TrataDadosSala(salaevento)) {
					
					if(conectaDB.CadastrarSalaEvento(salaevento)) {
						JOptionPane.showMessageDialog(null, "Sala cadastrada com sucesso!");
						txtNomeSala.setText("");
						txtLotacao.setText("");
					}
					else
						JOptionPane.showMessageDialog(null, "Não foi possível cadastrar a sala!");
						
				}
				
			}
		});
		bntOK.setBounds(170, 111, 89, 23);
		contentPane.add(bntOK);
		
		JButton bntLimpar = new JButton("Limpar");
		bntLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtNomeSala.setText(" ");
				txtLotacao.setText(" ");
			}
		});
		bntLimpar.setBounds(269, 111, 89, 23);
		contentPane.add(bntLimpar);
		
		JButton bntCancelar = new JButton("Cancelar");
		bntCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciaSalaEvento salaevento = new TelaGerenciaSalaEvento();
				salaevento.setVisible(true);
				
				setVisible(false);
			}
		});
		bntCancelar.setBounds(368, 111, 89, 23);
		contentPane.add(bntCancelar);
	}

}
