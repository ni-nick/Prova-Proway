package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexao.ConectaDataBase;
import Entidades.Estabelecimento;
import Entidades.SalaEvento;
import Entidades.Usuario;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaExibeUsuarioEstabelecimento extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtNomeEstabelecimento;
	private JTextField txtLotacao;
	private JComboBox cbUsuario;
	private JComboBox cbListar;
	
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
					TelaExibeUsuarioEstabelecimento frame = new TelaExibeUsuarioEstabelecimento();
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
	public TelaExibeUsuarioEstabelecimento() {
		setTitle("Exibe usu\u00E1rios nos estabelecimentos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.conectaDB.CriaConexao();
		
		JLabel lblSelecioneUmUsurio = new JLabel("Selecione um Usu\u00E1rio:");
		lblSelecioneUmUsurio.setBounds(10, 14, 136, 14);
		contentPane.add(lblSelecioneUmUsurio);
		
		JLabel lblSelecioneUmaOpo = new JLabel("Selecione uma Op\u00E7\u00E3o:");
		lblSelecioneUmaOpo.setBounds(10, 93, 136, 14);
		contentPane.add(lblSelecioneUmaOpo);
		
		cbUsuario = new JComboBox();
		cbUsuario.setBounds(176, 11, 253, 20);
		cbUsuario.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(cbUsuario.getItemCount()!=0) {
					txtNome.setText(listarUsuario.get(cbUsuario.getSelectedIndex()).getNome());
					txtSobrenome.setText(listarUsuario.get(cbUsuario.getSelectedIndex()).getSobreNome());
				}

			}
		});
		contentPane.add(cbUsuario);
		
		cbListar = new JComboBox();
		cbListar.setBounds(176, 90, 253, 20);
		cbListar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(cbListar.getItemCount()!=0 && cbListar.getSelectedIndex() > 0) {
					txtNomeEstabelecimento.setText(listarEstabelecimento.get(cbListar.getSelectedIndex()-1).getNome());
					txtLotacao.setText(listarEstabelecimento.get(cbListar.getSelectedIndex()-1).getLotacao());
				}
				else if(cbListar.getItemCount()!=0 && cbListar.getSelectedIndex() == 0) {
					txtNomeEstabelecimento.setText("Listar Todos");
				}

				if(cbListar.getSelectedIndex() != 0) {

					listarUsuario = conectaDB.CarregaUsuarioNoEstabelecimento(listarEstabelecimento.get(cbListar.getSelectedIndex()-1).getIdCafe());

				}

				else{
					listarUsuario = conectaDB.CarregaTodosUsuarios();
				}

				PreencheCBusuario();
			}
		});
		contentPane.add(cbListar);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 181, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(10, 206, 78, 14);
		contentPane.add(lblSobrenome);
		
		JLabel lblNomeDoEstabelecimento = new JLabel("Nome do Estabelecimento:");
		lblNomeDoEstabelecimento.setBounds(10, 248, 161, 14);
		contentPane.add(lblNomeDoEstabelecimento);
		
		JLabel lblLotao = new JLabel("Lota\u00E7\u00E3o:");
		lblLotao.setBounds(10, 273, 78, 14);
		contentPane.add(lblLotao);
		
		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setBounds(176, 178, 253, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setEditable(false);
		txtSobrenome.setBounds(176, 203, 253, 20);
		contentPane.add(txtSobrenome);
		txtSobrenome.setColumns(10);
		
		txtNomeEstabelecimento = new JTextField();
		txtNomeEstabelecimento.setEditable(false);
		txtNomeEstabelecimento.setBounds(176, 245, 253, 20);
		contentPane.add(txtNomeEstabelecimento);
		txtNomeEstabelecimento.setColumns(10);
		
		txtLotacao = new JTextField();
		txtLotacao.setEditable(false);
		txtLotacao.setBounds(176, 270, 69, 20);
		contentPane.add(txtLotacao);
		txtLotacao.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciaVinculacaoEstabeleUsuario vincula = new TelaGerenciaVinculacaoEstabeleUsuario();
				vincula.setVisible(true);
				
				setVisible(false);
			}
		});
		btnCancelar.setBounds(340, 282, 89, 23);
		contentPane.add(btnCancelar);
		
		PreencheCBLista();
		PreencheCBusuario();
	}
	
	private void PreencheCBusuario() {

		if(listarUsuario.size() == 0)
			listarUsuario = this.conectaDB.CarregaTodosUsuarios();

		cbUsuario.removeAllItems();

		for(int index = 0; index < listarUsuario.size(); index++)
			cbUsuario.addItem(listarUsuario.get(index).getNome()+" "+listarUsuario.get(index).getSobreNome());

		return;
	}

	private void PreencheCBLista() {

		listarEstabelecimento = this.conectaDB.CarregaTodosEstabelecimentos();
		cbListar.addItem("Listar Todos");

		for(int index = 0; index < listarEstabelecimento.size(); index++)
			cbListar.addItem(listarEstabelecimento.get(index).getNome()+"    -Lotação: "+listarEstabelecimento.get(index).getLotacao());

		return;
	}

}
