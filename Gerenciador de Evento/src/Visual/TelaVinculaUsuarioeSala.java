package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexao.ConectaDataBase;
import Entidades.SalaEvento;
import Entidades.Usuario;
import TrataDados.TrataDados;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class TelaVinculaUsuarioeSala extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeUsuario;
	private JTextField txtSobrenome;
	private JTextField txtNomeSala;
	private JTextField txtLotacao;
	private JComboBox cbUsuario;
	private JComboBox cbSalaEvento;

	private ArrayList<Usuario> listarUsuario = new ArrayList<Usuario>();
	private ArrayList<SalaEvento> listarSalas = new ArrayList<SalaEvento>();

	ConectaDataBase conectaDB = new ConectaDataBase();
	private JButton btnAdicionar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVinculaUsuarioeSala frame = new TelaVinculaUsuarioeSala();
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
	public TelaVinculaUsuarioeSala() {
		setTitle("Insere usu\u00E1rio em uma sala");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.conectaDB.CriaConexao();

		JLabel lblSelecionaUsuario = new JLabel("Selecione um Usu\u00E1rio: ");
		lblSelecionaUsuario.setBounds(10, 23, 140, 14);
		contentPane.add(lblSelecionaUsuario);

		JLabel lblSelecionaUmaSala = new JLabel("Selecione uma Sala:");
		lblSelecionaUmaSala.setBounds(10, 117, 121, 14);
		contentPane.add(lblSelecionaUmaSala);

		JLabel lblNomeUsurio = new JLabel("Nome: ");
		lblNomeUsurio.setBounds(10, 220, 89, 14);
		contentPane.add(lblNomeUsurio);

		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(10, 245, 89, 14);
		contentPane.add(lblSobrenome);

		txtNomeUsuario = new JTextField();
		txtNomeUsuario.setEditable(false);
		txtNomeUsuario.setBounds(160, 217, 252, 20);
		contentPane.add(txtNomeUsuario);
		txtNomeUsuario.setColumns(10);

		txtSobrenome = new JTextField();
		txtSobrenome.setEditable(false);
		txtSobrenome.setText("");
		txtSobrenome.setBounds(160, 242, 252, 20);
		contentPane.add(txtSobrenome);
		txtSobrenome.setColumns(10);

		JLabel lblNomeDaSala = new JLabel("Nome da Sala:");
		lblNomeDaSala.setBounds(10, 289, 86, 14);
		contentPane.add(lblNomeDaSala);

		JLabel lblLotao = new JLabel("Lota\u00E7\u00E3o: ");
		lblLotao.setBounds(10, 314, 67, 14);
		contentPane.add(lblLotao);

		txtNomeSala = new JTextField();
		txtNomeSala.setEditable(false);
		txtNomeSala.setBounds(158, 286, 254, 20);
		contentPane.add(txtNomeSala);
		txtNomeSala.setColumns(10);

		txtLotacao = new JTextField();
		txtLotacao.setEditable(false);
		txtLotacao.setBounds(158, 311, 86, 20);
		contentPane.add(txtLotacao);
		txtLotacao.setColumns(10);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TrataDados trataDados = new TrataDados();
				SalaEvento salaEvento = new SalaEvento();
				Usuario usuario = new Usuario();


				usuario.setIdUsuario(listarUsuario.get(cbUsuario.getSelectedIndex()).getIdUsuario());
				salaEvento.setIdSalaEvento(listarSalas.get(cbSalaEvento.getSelectedIndex()).getIdSalaEvento());

				
				int idSalaEvento = listarSalas.get(cbSalaEvento.getSelectedIndex()).getIdSalaEvento();
				int idUsuario = listarUsuario.get(cbUsuario.getSelectedIndex()).getIdUsuario();
				int Lotacao = Integer.parseInt(listarSalas.get(cbSalaEvento.getSelectedIndex()).getLotacao());

				
					if(trataDados.TrataDadosVincula(idSalaEvento, idUsuario, Lotacao)) {
						if(conectaDB.VinculaSalaUsuario(listarUsuario.get(cbUsuario.getSelectedIndex()).getIdUsuario(), listarSalas.get(cbSalaEvento.getSelectedIndex()).getIdSalaEvento())) {
							JOptionPane.showMessageDialog(null, "Usuário inserido na sala com sucesso!");
						}
						else
							JOptionPane.showMessageDialog(null, "Não foi possível inserir usuário na sala!");
					}
					else
						JOptionPane.showMessageDialog(null,"Dados Inconsistentes");
			}

		});
		btnAdicionar.setBounds(222, 352, 89, 23);
		contentPane.add(btnAdicionar);

		cbUsuario = new JComboBox();
		cbUsuario.setBounds(160, 20, 252, 20);
		cbUsuario.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(cbUsuario.getItemCount()!=0) {
					txtNomeUsuario.setText(listarUsuario.get(cbUsuario.getSelectedIndex()).getNome());
					txtSobrenome.setText(listarUsuario.get(cbUsuario.getSelectedIndex()).getSobreNome());
				}

			}
		});
		contentPane.add(cbUsuario);

		cbSalaEvento = new JComboBox();
		cbSalaEvento.setBounds(160, 114, 252, 20);
		cbSalaEvento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(cbSalaEvento.getItemCount()!=0) {
					txtNomeSala.setText(listarSalas.get(cbSalaEvento.getSelectedIndex()).getNome());
					txtLotacao.setText(listarSalas.get(cbSalaEvento.getSelectedIndex()).getLotacao());
				}
			}
		});
		contentPane.add(cbSalaEvento);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				TelaGerenciaVinculacaoUsuarioSala vinculacao = new TelaGerenciaVinculacaoUsuarioSala();
				vinculacao.setVisible(true);
				
				setVisible(false);
			}
		});
		btnCancelar.setBounds(323, 352, 89, 23);
		contentPane.add(btnCancelar);

		PreencheCBsalas();
		PreencheCBusuario();
	}

	private void PreencheCBusuario() {

		listarUsuario = this.conectaDB.CarregaTodosUsuarios();
		cbUsuario.removeAllItems();

		for(int index = 0; index < listarUsuario.size(); index++)
			cbUsuario.addItem(listarUsuario.get(index).getNome()+" "+listarUsuario.get(index).getSobreNome());

		return;
	}

	private void PreencheCBsalas() {

		listarSalas = this.conectaDB.CarregaTodasSalas();
		cbSalaEvento.removeAllItems();

		for(int index = 0; index < listarSalas.size(); index++)
			cbSalaEvento.addItem(listarSalas.get(index).getNome()+"    -Lotação: "+listarSalas.get(index).getLotacao());

		return;
	}
}
