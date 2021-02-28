package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexao.ConectaDataBase;
import Entidades.Estabelecimento;
import Entidades.Usuario;
import TrataDados.TrataDados;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaVinculaUsuarioeEstabeleciemento extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeUsuario;
	private JTextField txtSobrenome;
	private JTextField txtNomeEstabelecimento;
	private JTextField txtLotacao;
	private JComboBox cbUsuario;
	private JComboBox cbEstabelecimento;
	
	private ArrayList<Usuario> listarUsuario = new ArrayList<Usuario>();
	private ArrayList<Estabelecimento> listarEstabelecimento = new ArrayList<Estabelecimento>();
	
	ConectaDataBase conectaDB = new ConectaDataBase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVinculaUsuarioeEstabeleciemento frame = new TelaVinculaUsuarioeEstabeleciemento();
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
	public TelaVinculaUsuarioeEstabeleciemento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.conectaDB.CriaConexao();
		
		JLabel lblSelecioneUmUsurio = new JLabel("Selecione um Usu\u00E1rio:");
		lblSelecioneUmUsurio.setBounds(10, 27, 146, 14);
		contentPane.add(lblSelecioneUmUsurio);
		
		JLabel lblSelecioneUmEstabelecimento = new JLabel("Selecione um Estabelecimento:");
		lblSelecioneUmEstabelecimento.setBounds(10, 111, 186, 14);
		contentPane.add(lblSelecioneUmEstabelecimento);
		
		cbUsuario = new JComboBox();
		cbUsuario.setBounds(196, 24, 257, 20);
		cbUsuario.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(cbUsuario.getItemCount()!=0) {
					txtNomeUsuario.setText(listarUsuario.get(cbUsuario.getSelectedIndex()).getNome());
					txtSobrenome.setText(listarUsuario.get(cbUsuario.getSelectedIndex()).getSobreNome());
				}

			}
		});
		contentPane.add(cbUsuario);
		
		cbEstabelecimento = new JComboBox();
		cbEstabelecimento.setBounds(196, 108, 257, 20);
		cbEstabelecimento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(cbEstabelecimento.getItemCount()!=0) {
					txtNomeEstabelecimento.setText(listarEstabelecimento.get(cbEstabelecimento.getSelectedIndex()).getNome());
					txtLotacao.setText(listarEstabelecimento.get(cbEstabelecimento.getSelectedIndex()).getLotacao());
				}
				
			}
		});
		contentPane.add(cbEstabelecimento);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 217, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(10, 242, 76, 14);
		contentPane.add(lblSobrenome);
		
		JLabel lblNomeEstabelecimento = new JLabel("Nome Estabelecimento:");
		lblNomeEstabelecimento.setBounds(10, 284, 146, 14);
		contentPane.add(lblNomeEstabelecimento);
		
		JLabel lblLotao = new JLabel("Lota\u00E7\u00E3o:");
		lblLotao.setBounds(10, 309, 65, 14);
		contentPane.add(lblLotao);
		
		txtNomeUsuario = new JTextField();
		txtNomeUsuario.setEditable(false);
		txtNomeUsuario.setBounds(196, 214, 257, 20);
		contentPane.add(txtNomeUsuario);
		txtNomeUsuario.setColumns(10);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setEditable(false);
		txtSobrenome.setBounds(196, 239, 257, 20);
		contentPane.add(txtSobrenome);
		txtSobrenome.setColumns(10);
		
		txtNomeEstabelecimento = new JTextField();
		txtNomeEstabelecimento.setEditable(false);
		txtNomeEstabelecimento.setBounds(196, 281, 257, 20);
		contentPane.add(txtNomeEstabelecimento);
		txtNomeEstabelecimento.setColumns(10);
		
		txtLotacao = new JTextField();
		txtLotacao.setEditable(false);
		txtLotacao.setBounds(196, 306, 76, 20);
		contentPane.add(txtLotacao);
		txtLotacao.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciaVinculacaoEstabeleUsuario vinculacao = new TelaGerenciaVinculacaoEstabeleUsuario();
				vinculacao.setVisible(true);
				
				setVisible(false);
				
			}
		});
		btnCancelar.setBounds(364, 337, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Estabelecimento espacoCafe = new Estabelecimento();
				TrataDados trataDados = new TrataDados();
				Usuario usuario = new Usuario();
				
				usuario.setIdUsuario(listarUsuario.get(cbUsuario.getSelectedIndex()).getIdUsuario());
				espacoCafe.setIdCafe(listarEstabelecimento.get(cbEstabelecimento.getSelectedIndex()).getIdCafe());

				
				int idEstabelecimento = listarEstabelecimento.get(cbEstabelecimento.getSelectedIndex()).getIdCafe();
				int idUsuario = listarUsuario.get(cbUsuario.getSelectedIndex()).getIdUsuario();
				int lotacao = Integer.parseInt(listarEstabelecimento.get(cbEstabelecimento.getSelectedIndex()).getLotacao());

				
					if(trataDados.TrataDadosVinculaEstabelecimento(idEstabelecimento, idUsuario, lotacao)) {
						if(conectaDB.VinculaEstabelecimentoUsuario(listarUsuario.get(cbUsuario.getSelectedIndex()).getIdUsuario(),listarEstabelecimento.get(cbEstabelecimento.getSelectedIndex()).getIdCafe())) {
							JOptionPane.showMessageDialog(null, "Usuário inserido no estabelecimento com sucesso!");
				
						}
						else
							JOptionPane.showMessageDialog(null, "Não foi possível inserir usuário no estabelecimento!");
					}
					else
						JOptionPane.showMessageDialog(null,"Dados Inconsistentes");
			}
		});
		btnAdicionar.setBounds(265, 337, 89, 23);
		contentPane.add(btnAdicionar);
		
		PreencheCBusuario();
		PreencheCBEstabelecimento();
	}
	
	private void PreencheCBusuario() {

		listarUsuario = this.conectaDB.CarregaTodosUsuarios();
		cbUsuario.removeAllItems();

		for(int index = 0; index < listarUsuario.size(); index++)
			cbUsuario.addItem(listarUsuario.get(index).getNome()+" "+listarUsuario.get(index).getSobreNome());

		return;
	}

	private void PreencheCBEstabelecimento() {

		listarEstabelecimento = this.conectaDB.CarregaTodosEstabelecimentos();
		cbEstabelecimento.removeAllItems();

		for(int index = 0; index < listarEstabelecimento.size(); index++)
			cbEstabelecimento.addItem(listarEstabelecimento.get(index).getNome()+"    -Lotação: "+listarEstabelecimento.get(index).getLotacao());

		return;
	}
}
