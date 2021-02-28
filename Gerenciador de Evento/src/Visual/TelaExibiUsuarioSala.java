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
import Entidades.SalaEvento;
import Entidades.Usuario;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaExibiUsuarioSala extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeUsuario;
	private JTextField txtSobrenome;
	private JTextField txtNomeSala;
	private JTextField txtLotacao;
	private JComboBox cbUsuario;
	private JComboBox cbListar;

	private ArrayList<Usuario> listarUsuario = new ArrayList<Usuario>();
	private ArrayList<SalaEvento> listarSalas = new ArrayList<SalaEvento>();

	ConectaDataBase conectaDB = new ConectaDataBase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaExibiUsuarioSala frame = new TelaExibiUsuarioSala();
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
	public TelaExibiUsuarioSala() {
		setTitle("Exibe usu\u00E1rios nas salas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 442, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.conectaDB.CriaConexao();

		JLabel lblSelecioneUmUsurio = new JLabel("Selecione um Usu\u00E1rio:");
		lblSelecioneUmUsurio.setBounds(10, 28, 130, 14);
		contentPane.add(lblSelecioneUmUsurio);

		JLabel lblSelecioneUmaOpo = new JLabel("Selecione uma Op\u00E7\u00E3o:");
		lblSelecioneUmaOpo.setBounds(10, 121, 130, 14);
		contentPane.add(lblSelecioneUmaOpo);

		cbUsuario = new JComboBox();
		cbUsuario.setBounds(150, 25, 252, 20);
		cbUsuario.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(cbUsuario.getItemCount()!=0) {
					txtNomeUsuario.setText(listarUsuario.get(cbUsuario.getSelectedIndex()).getNome());
					txtSobrenome.setText(listarUsuario.get(cbUsuario.getSelectedIndex()).getSobreNome());
				}

			}
		});
		contentPane.add(cbUsuario);

		cbListar = new JComboBox();
		cbListar.setBounds(150, 118, 252, 20);
		cbListar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(cbListar.getItemCount()!=0 && cbListar.getSelectedIndex() > 0) {
					txtNomeSala.setText(listarSalas.get(cbListar.getSelectedIndex()-1).getNome());
					txtLotacao.setText(listarSalas.get(cbListar.getSelectedIndex()-1).getLotacao());
				}
				else if(cbListar.getItemCount()!=0 && cbListar.getSelectedIndex() == 0) {
					txtNomeSala.setText("Listar Todos");
				}

				if(cbListar.getSelectedIndex() != 0) {

					listarUsuario = conectaDB.CarregaUsuarioNaSala(listarSalas.get(cbListar.getSelectedIndex()-1).getIdSalaEvento());

				}

				else{
					listarUsuario = conectaDB.CarregaTodosUsuarios();
				}

				PreencheCBusuario();
			}
		});
		contentPane.add(cbListar);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 213, 46, 14);
		contentPane.add(lblNome);

		JLabel lblSobrenome = new JLabel("Sobrenome: ");
		lblSobrenome.setBounds(10, 238, 77, 14);
		contentPane.add(lblSobrenome);

		JLabel lblNomeDaSala = new JLabel("Nome da Sala:");
		lblNomeDaSala.setBounds(10, 283, 89, 14);
		contentPane.add(lblNomeDaSala);

		JLabel lblLotao = new JLabel("Lota\u00E7\u00E3o:");
		lblLotao.setBounds(10, 308, 65, 14);
		contentPane.add(lblLotao);

		txtNomeUsuario = new JTextField();
		txtNomeUsuario.setEditable(false);
		txtNomeUsuario.setBounds(150, 210, 252, 20);
		contentPane.add(txtNomeUsuario);
		txtNomeUsuario.setColumns(10);

		txtSobrenome = new JTextField();
		txtSobrenome.setEditable(false);
		txtSobrenome.setBounds(150, 235, 252, 20);
		contentPane.add(txtSobrenome);
		txtSobrenome.setColumns(10);

		txtNomeSala = new JTextField();
		txtNomeSala.setEditable(false);
		txtNomeSala.setBounds(150, 280, 252, 20);
		contentPane.add(txtNomeSala);
		txtNomeSala.setColumns(10);

		txtLotacao = new JTextField();
		txtLotacao.setEditable(false);
		txtLotacao.setBounds(150, 305, 86, 20);
		contentPane.add(txtLotacao);
		txtLotacao.setColumns(10);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciaVinculacaoUsuarioSala vinculacao = new TelaGerenciaVinculacaoUsuarioSala();
				vinculacao.setVisible(true);
				
				setVisible(false);
			}
		});
		btnCancelar.setBounds(313, 336, 89, 23);
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

		listarSalas = this.conectaDB.CarregaTodasSalas();

		cbListar.addItem("Listar Todos");
		for(int index = 0; index < listarSalas.size(); index++)
			cbListar.addItem(listarSalas.get(index).getNome()+"    -Lotação: "+listarSalas.get(index).getLotacao());

		return;
	}
}
