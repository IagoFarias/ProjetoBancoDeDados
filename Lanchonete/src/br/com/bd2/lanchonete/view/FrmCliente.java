package br.com.bd2.lanchonete.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.bd2.lanchonete.controller.ClienteController;
import br.com.bd2.lanchonete.negocio.Cliente;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

public class FrmCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtContato;
	private JTextField txtEndereco;
	private JTable tblConsulta;
	JLabel lblMensagem = new JLabel("Mensagem:");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCliente frame = new FrmCliente();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmCliente() {
		setTitle("Gerenciamento de Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 703, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
		);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		tblConsulta = new JTable();
		tblConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer linha = tblConsulta.getSelectedRow();
				String nome = tblConsulta.getValueAt(linha, 0).toString();
				String cpf = tblConsulta.getValueAt(linha, 1).toString();
				String contato = tblConsulta.getValueAt(linha, 2).toString();
				String endereco = tblConsulta.getValueAt(linha, 3).toString();
				
				txtNome.setText(nome);
				txtCpf.setText(cpf);
				txtContato.setText(contato);
				txtEndereco.setText(endereco);
				
			}
		});
		tblConsulta.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "CPF", "Contato", "Endere\u00E7o" }) {
					Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					boolean[] columnEditables = new boolean[] { false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		tblConsulta.getColumnModel().getColumn(0).setResizable(false);
		tblConsulta.getColumnModel().getColumn(1).setResizable(false);
		tblConsulta.getColumnModel().getColumn(2).setResizable(false);
		tblConsulta.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(tblConsulta);
		panel_2.setLayout(gl_panel_2);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cli = new Cliente();
				ClienteController cliCon = new ClienteController();
				cli.setNome(txtNome.getText());
				cli.setCpf(txtCpf.getText());
				cli.setContato(txtContato.getText());
				cli.setEndereco(txtEndereco.getText());
				
				lblMensagem.setText("Mensagem: "+cliCon.inserir(cli));

			}
		});

		JButton btnAtualizarDados = new JButton("Atualizar Dados");
		btnAtualizarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cli = new Cliente();
				ClienteController cliCon = new ClienteController();
				cli.setNome(txtNome.getText());
				cli.setCpf(txtCpf.getText());
				cli.setContato(txtContato.getText());
				cli.setEndereco(txtEndereco.getText());
				
				lblMensagem.setText("Mensagem: "+cliCon.alterar(cli));
				
			}
		});

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteController cliCon = new ClienteController();
				
				
				Cliente cli =cliCon.pesquisarPorCpf(txtCpf.getText());
				
				if(cli == null)
				{
					JOptionPane.showMessageDialog(null,"Cliente Não Encontrado!");
					
				}
				else {
					Object[] opcoes = { "Sim", "Não" };
					int i = JOptionPane.showOptionDialog(null, "Deseja excluir esse Cliente: " + txtNome.getText() + "?",
							"Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
					if (JOptionPane.YES_OPTION == i) {
						lblMensagem.setText("Mensagem: "+cliCon.excluir(cli));
					}
				}
	
			}
		});

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMensagem.setText("Mensagem: ");
				List<Cliente> listaCli = new ArrayList<Cliente>();
				ClienteController cliCon = new ClienteController();
				
				listaCli = cliCon.listarTodos();
				
				DefaultTableModel tbm = (DefaultTableModel)tblConsulta.getModel();
				for(int i = tbm.getRowCount()-1; i >= 0; i--){
				tbm.removeRow(i);
				}
				int i = 0;
				for(Cliente cli : listaCli ){
				tbm.addRow(new String[1]);
				tblConsulta.setValueAt(cli.getNome(), i, 0);
				tblConsulta.setValueAt(cli.getCpf(), i, 1);
				tblConsulta.setValueAt(cli.getContato(), i, 2);
				tblConsulta.setValueAt(cli.getEndereco(), i, 3);
				i++;
				}
				
			}
		});

		JButton btnBuscarPorCpf = new JButton("Buscar Por CPF");
		btnBuscarPorCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMensagem.setText("Mensagem: ");
				ClienteController cliCon = new ClienteController();
				Cliente cli = cliCon.pesquisarPorCpf(txtCpf.getText());
				
				if(cli == null)
				{
					JOptionPane.showMessageDialog(null,"Cliente Não Encontrado!");
					
				}
				else {
				txtNome.setText(cli.getNome());
				txtCpf.setText(cli.getCpf());
				txtContato.setText(cli.getContato());
				txtEndereco.setText(cli.getEndereco());
				}
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtCpf.setText("");
				txtContato.setText("");
				txtEndereco.setText("");
				lblMensagem.setText("Mensagem: ");
				
				DefaultTableModel tbm = (DefaultTableModel)tblConsulta.getModel();
				for(int i = tbm.getRowCount()-1; i >= 0; i--){
				tbm.removeRow(i);
				}
				
			}
		});
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCliente.this.dispose();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(btnCadastrar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAtualizarDados)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExcluir)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPesquisar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuscarPorCpf)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLimpar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSair)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar)
						.addComponent(btnAtualizarDados)
						.addComponent(btnExcluir)
						.addComponent(btnPesquisar)
						.addComponent(btnBuscarPorCpf)
						.addComponent(btnLimpar)
						.addComponent(btnSair))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);

		JLabel lblNome = new JLabel("Nome:");

		JLabel lblCpf = new JLabel("CPF:");

		JLabel lblContato = new JLabel("Contato:");

		JLabel lblEndereo = new JLabel("Endere\u00E7o:");

		txtNome = new JTextField();
		txtNome.setColumns(10);

		txtCpf = new JTextField();
		txtCpf.setColumns(10);

		txtContato = new JTextField();
		txtContato.setColumns(10);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		
		JLabel lblMensagem = new JLabel("Mensagem:");
		lblMensagem.setForeground(Color.BLUE);
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblEndereo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblContato)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtContato, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCpf)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblMensagem))
					.addContainerGap(255, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContato)
						.addComponent(txtContato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEndereo)
						.addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addComponent(lblMensagem))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
