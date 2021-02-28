package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexao.ConectaDataBase;
import Entidades.Usuario;
import TrataDados.TrataDados;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TelaAlterarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtSobrenome;	
	private JComboBox cbUsuario;
	private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	
	ConectaDataBase conectaDB = new ConectaDataBase();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAlterarUsuario frame = new TelaAlterarUsuario();
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
	public TelaAlterarUsuario() {
		setTitle("Alterar Usu\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.conectaDB.CriaConexao();
		
		JLabel label = new JLabel("Nome:");
		label.setBounds(-44, 29, 46, 14);
		contentPane.add(label);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(176, 53, 248, 20);
		contentPane.add(txtNome);
		
		JLabel labelSobrenome = new JLabel("Sobrenome:");
		labelSobrenome.setBounds(21, 90, 80, 14);
		contentPane.add(labelSobrenome);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setColumns(10);
		txtSobrenome.setBounds(176, 84, 248, 20);
		contentPane.add(txtSobrenome);
		
		JButton bntOK = new JButton("OK");
		bntOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Usuario usuario = new Usuario();
				TrataDados trataDados = new TrataDados();
				
				usuario.setNome(txtNome.getText());
				usuario.setSobreNome(txtSobrenome.getText());
				usuario.setIdUsuario(listaUsuarios.get(cbUsuario.getSelectedIndex()).getIdUsuario());
				

				if (trataDados.TrataDadosUsuario(usuario))
				{
					if(conectaDB.AlteraUsuario(usuario)) {		
						JOptionPane.showMessageDialog(null,"Usuário alterado com sucesso!");	
						PreencheComboBox();
					}
					else {
						JOptionPane.showMessageDialog(null,"Não foi possível alterar esse usuário!" );
					}
				}
				
			}
		});
		bntOK.setBounds(46, 161, 89, 23);
		contentPane.add(bntOK);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtNome.setText(" ");
				txtSobrenome.setText(" ");
			}
		});
		btnLimpar.setBounds(145, 161, 95, 23);
		contentPane.add(btnLimpar);
		
		JButton bntCancelar = new JButton("Cancelar");
		bntCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaGerenciarUsuario gerenciaUsuario = new TelaGerenciarUsuario();
				gerenciaUsuario.setVisible(true);
				
				setVisible(false);
			}
		});
		bntCancelar.setBounds(349, 161, 89, 23);
		contentPane.add(bntCancelar);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(21, 56, 46, 14);
		contentPane.add(lblNome);
		
		cbUsuario = new JComboBox();
		cbUsuario.setToolTipText("");
		cbUsuario.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			
				if (cbUsuario.getItemCount() != 0)
				{
					txtNome.setText(listaUsuarios.get(cbUsuario.getSelectedIndex()).getNome());
					txtSobrenome.setText(listaUsuarios.get(cbUsuario.getSelectedIndex()).getSobreNome());
				}
				
			
			}
		});
		cbUsuario.setBounds(176, 11, 248, 20);
		contentPane.add(cbUsuario);
		
		JLabel lblSelecioneUmUsuario = new JLabel("Selecione um Usuario:");
		lblSelecioneUmUsuario.setBounds(21, 14, 145, 14);
		contentPane.add(lblSelecioneUmUsuario);		
		
		JButton bntRemoveUsuario = new JButton("Remover");
		bntRemoveUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				Usuario usuario = new Usuario();
				TrataDados trataDados = new TrataDados();
				
				usuario.setNome(txtNome.getText());
				usuario.setSobreNome(txtSobrenome.getText());
				usuario.setIdUsuario(listaUsuarios.get(cbUsuario.getSelectedIndex()).getIdUsuario());

				if (trataDados.TrataDadosUsuario(usuario))
				{
					if(conectaDB.ExcluiUsuario(usuario)) {
						JOptionPane.showMessageDialog(null,"Usuário excluído com sucesso!");
						PreencheComboBox();
					}
					else {
						JOptionPane.showMessageDialog(null,"Não foi possível excluir esse usuário!" );
					}
				}
				
			}
				
		});
		bntRemoveUsuario.setBounds(250, 161, 89, 23);
		contentPane.add(bntRemoveUsuario);
		
		PreencheComboBox();
		
	}
	
	private void PreencheComboBox () {	
		
		listaUsuarios = this.conectaDB.CarregaTodosUsuarios();	
		cbUsuario.removeAllItems();
		
		for (int index = 0; index < listaUsuarios.size(); index++)			
			cbUsuario.addItem(listaUsuarios.get(index).getNome() + " " + listaUsuarios.get(index).getSobreNome());				
	
		return;
		
		
	}
	
}
