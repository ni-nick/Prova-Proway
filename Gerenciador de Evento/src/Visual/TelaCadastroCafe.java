package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexao.ConectaDataBase;
import Entidades.Estabelecimento;
import TrataDados.TrataDados;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroCafe extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeCafe;
	private JTextField txtLotacaoCafe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCafe frame = new TelaCadastroCafe();
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
	public TelaCadastroCafe() {
		setTitle("Local do Intervalo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 193);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelEstabelecimento = new JLabel("Nome do Estabelecimento: ");
		labelEstabelecimento.setBounds(10, 25, 163, 14);
		contentPane.add(labelEstabelecimento);
		
		JLabel labelLotacaoCafe = new JLabel("Lota\u00E7\u00E3o:");
		labelLotacaoCafe.setBounds(10, 50, 126, 14);
		contentPane.add(labelLotacaoCafe);
		
		txtNomeCafe = new JTextField();
		txtNomeCafe.setBounds(183, 22, 278, 20);
		contentPane.add(txtNomeCafe);
		txtNomeCafe.setColumns(10);
		
		txtLotacaoCafe = new JTextField();
		txtLotacaoCafe.setBounds(183, 47, 89, 20);
		contentPane.add(txtLotacaoCafe);
		txtLotacaoCafe.setColumns(10);
		
		JButton bntOK = new JButton("OK");
		bntOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TrataDados trataDados = new TrataDados();
				ConectaDataBase conectaDB = new ConectaDataBase();
				Estabelecimento espacoCafe = new Estabelecimento();
				
				espacoCafe.setNome(txtNomeCafe.getText());
				espacoCafe.setLotacao(txtLotacaoCafe.getText());
				conectaDB.CriaConexao();
				
			
				if(trataDados.TrataDadosEstabelecimento(espacoCafe)) {
					
					if(conectaDB.CadastrarEstabelecimento(espacoCafe)) {
						JOptionPane.showMessageDialog(null, "Estabelecimento cadastrado com sucesso!");
						txtNomeCafe.setText("");
						txtLotacaoCafe.setText("");
					}
					else
						JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o estabelecimento!");
				}

			}
		});
		bntOK.setBounds(188, 110, 89, 23);
		contentPane.add(bntOK);
		
		JButton bntLimpar = new JButton("Limpar");
		bntLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtNomeCafe.setText("");
				txtLotacaoCafe.setText("");
			}
		});
		bntLimpar.setBounds(287, 110, 89, 23);
		contentPane.add(bntLimpar);
		
		JButton bntCancelar = new JButton("Cancelar");
		bntCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciaIntervalo espacoCafe = new TelaGerenciaIntervalo();
				espacoCafe.setVisible(true);
				
				setVisible(false);
			}
		});
		bntCancelar.setBounds(386, 110, 89, 23);
		contentPane.add(bntCancelar);
	}

}
