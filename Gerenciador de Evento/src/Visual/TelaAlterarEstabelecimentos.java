package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexao.ConectaDataBase;
import Entidades.Estabelecimento;
import TrataDados.TrataDados;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaAlterarEstabelecimentos extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeCafe;
	private JTextField txtLotacao;
	private JComboBox cbEstabelecimento;
	private ArrayList<Estabelecimento> listarEstabele = new ArrayList<Estabelecimento>();
	
	ConectaDataBase conectaDB = new ConectaDataBase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAlterarEstabelecimentos frame = new TelaAlterarEstabelecimentos();
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
	public TelaAlterarEstabelecimentos() {
		setTitle("Alterar o Estabelecimento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.conectaDB.CriaConexao();
		
		cbEstabelecimento = new JComboBox();
		cbEstabelecimento.setBounds(211, 29, 280, 20);
		contentPane.add(cbEstabelecimento);
		cbEstabelecimento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			
				if (cbEstabelecimento.getItemCount() != 0)
				{
					txtNomeCafe.setText(listarEstabele.get(cbEstabelecimento.getSelectedIndex()).getNome());
					txtLotacao.setText(listarEstabele.get(cbEstabelecimento.getSelectedIndex()).getLotacao());
				}
				
			
			}
		});
//		JComboBox cbEstabelecimento = new JComboBox();
//		cbEstabelecimento.setBounds(175, 29, 280, 20);
//		contentPane.add(cbEstabelecimento);
		
		JLabel lblEstabelecimentoCastrado = new JLabel("Selecione um Estabelecimento:");
		lblEstabelecimentoCastrado.setBounds(10, 32, 193, 14);
		contentPane.add(lblEstabelecimentoCastrado);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 120, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblLotao = new JLabel("Lota\u00E7\u00E3o:");
		lblLotao.setBounds(10, 151, 73, 14);
		contentPane.add(lblLotao);
		
		txtNomeCafe = new JTextField();
		txtNomeCafe.setBounds(142, 117, 280, 20);
		contentPane.add(txtNomeCafe);
		txtNomeCafe.setColumns(10);
		
		txtLotacao = new JTextField();
		txtLotacao.setText("");
		txtLotacao.setBounds(142, 148, 86, 20);
		contentPane.add(txtLotacao);
		txtLotacao.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciaIntervalo espacoCafe = new TelaGerenciaIntervalo();
				espacoCafe.setVisible(true);
				
				setVisible(false);
			}
		});
		btnCancelar.setBounds(386, 226, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TrataDados trataDados = new TrataDados();
				Estabelecimento espacoCafe = new Estabelecimento();
				
				espacoCafe.setNome(txtNomeCafe.getText());
				espacoCafe.setLotacao(txtLotacao.getText());
				espacoCafe.setIdCafe(listarEstabele.get(cbEstabelecimento.getSelectedIndex()).getIdCafe());
				
				if(trataDados.TrataDadosEstabelecimento(espacoCafe)) {
					
					if(conectaDB.ExcluirEstabelecimento(espacoCafe)) {
						JOptionPane.showMessageDialog(null, "Estabelecimento excluído com sucesso!");
						PreencheCBestabelecimento();
					}
					else
						JOptionPane.showMessageDialog(null, "Não foi possível excluir esse estabelecimento!");
				}
			}
		});
		btnRemover.setBounds(287, 226, 89, 23);
		contentPane.add(btnRemover);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtNomeCafe.setText("");
				txtLotacao.setText("");
			}
		});
		btnLimpar.setBounds(188, 226, 89, 23);
		contentPane.add(btnLimpar);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TrataDados trataDados = new TrataDados();
				Estabelecimento espacoCafe = new Estabelecimento();
				
				espacoCafe.setNome(txtNomeCafe.getText());
				espacoCafe.setLotacao(txtLotacao.getText());
				espacoCafe.setIdCafe(listarEstabele.get(cbEstabelecimento.getSelectedIndex()).getIdCafe());
				
				if(trataDados.TrataDadosEstabelecimento(espacoCafe)) {
					
					if(conectaDB.AlterarEstabelecimento(espacoCafe)) {
						JOptionPane.showMessageDialog(null, "Estabelecimento alterado com sucesso!");
						PreencheCBestabelecimento();
					}
					else
						JOptionPane.showMessageDialog(null, "Não foi possível alterar esse estabelecimento!");
				}
			}
		});
		btnOk.setBounds(89, 226, 89, 23);
		contentPane.add(btnOk);
		
		PreencheCBestabelecimento();
		
	}
	
	private void PreencheCBestabelecimento() {
		
		listarEstabele = this.conectaDB.CarregaTodosEstabelecimentos();
		cbEstabelecimento.removeAllItems();
		
		for(int index = 0; index < listarEstabele.size(); index++)
			cbEstabelecimento.addItem(listarEstabele.get(index).getNome() +"      - Lotação: "+listarEstabele.get(index).getLotacao());
		
		return;
	}
}
