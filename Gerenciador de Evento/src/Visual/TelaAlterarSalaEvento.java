package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexao.ConectaDataBase;
import Entidades.SalaEvento;
import TrataDados.TrataDados;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class TelaAlterarSalaEvento extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeSala;
	private JTextField txtLotacao;
	private JComboBox cbSalaEvento;
	private ArrayList<SalaEvento> listarSalas = new ArrayList<SalaEvento>();
	
	ConectaDataBase conectaDB = new ConectaDataBase();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAlterarSalaEvento frame = new TelaAlterarSalaEvento();
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
	public TelaAlterarSalaEvento() {
		setTitle("Alterar as Salas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.conectaDB.CriaConexao();
		
		JLabel lblSalasCadastradas = new JLabel("Salas Cadastradas: ");
		lblSalasCadastradas.setBounds(10, 27, 138, 14);
		contentPane.add(lblSalasCadastradas);
		
		JLabel lblNome = new JLabel("Nome da Sala: ");
		lblNome.setBounds(10, 107, 110, 14);
		contentPane.add(lblNome);
		
		JLabel labelLotacao = new JLabel("Lotaçao: ");
		labelLotacao.setBounds(10, 144, 86, 14);
		contentPane.add(labelLotacao);
		
		cbSalaEvento = new JComboBox();
		cbSalaEvento.setToolTipText("");
		cbSalaEvento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			
				if (cbSalaEvento.getItemCount() != 0)
				{
					txtNomeSala.setText(listarSalas.get(cbSalaEvento.getSelectedIndex()).getNome());
					txtLotacao.setText(listarSalas.get(cbSalaEvento.getSelectedIndex()).getLotacao());
				}
				
			
			}
		});
		cbSalaEvento.setBounds(144, 24, 300, 20);
		contentPane.add(cbSalaEvento);
		
		txtNomeSala = new JTextField();
		txtNomeSala.setBounds(144, 104, 303, 20);
		contentPane.add(txtNomeSala);
		txtNomeSala.setColumns(10);
		
		txtLotacao = new JTextField();
		txtLotacao.setBounds(144, 141, 86, 20);
		contentPane.add(txtLotacao);
		txtLotacao.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SalaEvento salaevento = new SalaEvento(); 
				TrataDados trataDados = new TrataDados();
				
				salaevento.setNome(txtNomeSala.getText());
				salaevento.setLotacao(txtLotacao.getText());
				salaevento.setIdSalaEvento(listarSalas.get(cbSalaEvento.getSelectedIndex()).getIdSalaEvento());
				
				if(trataDados.TrataDadosSala(salaevento)) {
					
					if(conectaDB.AlteraSalaEvento(salaevento)) {
						JOptionPane.showMessageDialog(null, "Sala alterada com sucesso!");
						PreencheCBSala();
					}
					else
						JOptionPane.showMessageDialog(null, "Não foi possível alterar essa sala!");
						
				}
			}
		});
		btnOk.setBounds(72, 212, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtNomeSala.setText("");
				txtLotacao.setText("");
			}
		});
		btnLimpar.setBounds(171, 212, 89, 23);
		contentPane.add(btnLimpar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SalaEvento salaevento = new SalaEvento(); 
				TrataDados trataDados = new TrataDados();
				
				salaevento.setNome(txtNomeSala.getText());
				salaevento.setLotacao(txtLotacao.getText());
				salaevento.setIdSalaEvento(listarSalas.get(cbSalaEvento.getSelectedIndex()).getIdSalaEvento());
				
				if(trataDados.TrataDadosSala(salaevento)) {
					
					if(conectaDB.ExcluiSalaEvento(salaevento)) {
						JOptionPane.showMessageDialog(null, "Sala excluída com sucesso!");
						PreencheCBSala();
					}else
						JOptionPane.showMessageDialog(null, "Não foi possível excluir essa sala!");
						
				}
			}
		});
		btnRemover.setBounds(270, 212, 89, 23);
		contentPane.add(btnRemover);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciaSalaEvento gerenciaSala = new TelaGerenciaSalaEvento();
				gerenciaSala.setVisible(true);
				
				setVisible(false);
			}
		});
		btnCancelar.setBounds(369, 212, 89, 23);
		contentPane.add(btnCancelar);
		
		PreencheCBSala();
	}
	
	private void PreencheCBSala() {
		
		listarSalas = this.conectaDB.CarregaTodasSalas();
		cbSalaEvento.removeAllItems();
		
		for(int index = 0; index < listarSalas.size(); index++)
			cbSalaEvento.addItem(listarSalas.get(index).getNome() + "      - Lotação: "+ listarSalas.get(index).getLotacao());
		
		return;
		
	}
}
