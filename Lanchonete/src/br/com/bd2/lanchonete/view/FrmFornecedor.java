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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.bd2.lanchonete.controller.FornecedorController;
import br.com.bd2.lanchonete.negocio.Fornecedor;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmFornecedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCnpj;
	private JTextField txtEndereco;
	private JTextField txtContato;
	private JLabel lblMensagem;
	private JPanel panel_1;
	private JTable tblConsulta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmFornecedor frame = new FrmFornecedor();
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
	public FrmFornecedor() {
		setTitle("Gerenciamento de Fornecedores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 745, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 709, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(19, Short.MAX_VALUE)));

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 708, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		tblConsulta = new JTable();
		tblConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer linha = tblConsulta.getSelectedRow();
				
				String nome = tblConsulta.getValueAt(linha, 0).toString();
				String cnpj = tblConsulta.getValueAt(linha, 1).toString();
				String endereco = tblConsulta.getValueAt(linha, 2).toString();
				String contato = tblConsulta.getValueAt(linha, 3).toString();
				
				txtNome.setText(nome);
				txtCnpj.setText(cnpj);
				txtContato.setText(contato);
				txtEndereco.setText(endereco);
			}
		});
		tblConsulta.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "CNPJ", "Endere\u00E7o", "Contato" }) {
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
				Fornecedor forn = new Fornecedor();
				FornecedorController fornCo = new FornecedorController();

				forn.setNome(txtNome.getText());
				forn.setCpnj(txtCnpj.getText());
				forn.setEndereco(txtEndereco.getText());
				forn.setContato(txtContato.getText());

				lblMensagem.setText("Mensagem:" + fornCo.inserir(forn));

			}
		});

		JButton btnAtualizarDados = new JButton("Atualizar Dados");
		btnAtualizarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedor forn = new Fornecedor();
				FornecedorController fornCo = new FornecedorController();

				forn.setNome(txtNome.getText());
				forn.setCpnj(txtCnpj.getText());
				forn.setEndereco(txtEndereco.getText());
				forn.setContato(txtContato.getText());

				lblMensagem.setText("Mensagem:" + fornCo.alterar(forn));

			}
		});

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FornecedorController fornCo = new FornecedorController();

				Fornecedor forn = fornCo.pesquisarPorCnpj(txtCnpj.getText());

				if (forn == null) {
					JOptionPane.showMessageDialog(null, "Fornecedor Não Encontrado!");

				} else {
					Object[] opcoes = { "Sim", "Não" };
					int i = JOptionPane.showOptionDialog(null,
							"Deseja excluir esse Fornecedor: " + txtNome.getText() + "?", "Exclusão",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
					if (JOptionPane.YES_OPTION == i) {
						lblMensagem.setText("Mensagem:" + fornCo.excluir(forn));
					}
				}

			}
		});

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMensagem.setText("Mensagem:");
				List<Fornecedor> listaForn = new ArrayList<Fornecedor>();
				FornecedorController fornCo = new FornecedorController();

				listaForn = fornCo.listarTodos();

				DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
				// Limpa a Tabela
				for (int i = tbm.getRowCount() - 1; i >= 0; i--) {
					tbm.removeRow(i);
				}
				// Preenche com os funcionários
				int i = 0;
				for (Fornecedor forn : listaForn) {
					tbm.addRow(new String[1]);
					tblConsulta.setValueAt(forn.getNome(), i, 0);
					tblConsulta.setValueAt(forn.getCpnj(), i, 1);
					tblConsulta.setValueAt(forn.getEndereco(), i, 2);
					tblConsulta.setValueAt(forn.getContato(), i, 3);
					i++;
				}
			}
		});

		JButton btnPesquisarPorCnpj = new JButton("Pesquisar Por CNPJ");
		btnPesquisarPorCnpj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FornecedorController fornCo = new FornecedorController();

				Fornecedor forn = fornCo.pesquisarPorCnpj(txtCnpj.getText());
				
				if(forn == null)
				{
					JOptionPane.showMessageDialog(null, "Fornecedor Não Encontrado!");
				}
				
				else {
					txtNome.setText(forn.getNome());
					txtCnpj.setText(forn.getCpnj());
					txtContato.setText(forn.getContato());
					txtEndereco.setText(forn.getEndereco());
				}

			}
		});

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtCnpj.setText("");
				txtContato.setText("");
				txtEndereco.setText("");
				lblMensagem.setText("Mensagem:");

				DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
				for (int i = tbm.getRowCount() - 1; i >= 0; i--) {
					tbm.removeRow(i);
				}
			}
		});

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmFornecedor.this.dispose();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addComponent(btnCadastrar)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnAtualizarDados)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnExcluir)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnPesquisar)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnPesquisarPorCnpj)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnLimpar)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSair)
						.addContainerGap(29, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_1.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnCadastrar)
								.addComponent(btnAtualizarDados).addComponent(btnExcluir).addComponent(btnPesquisar)
								.addComponent(btnPesquisarPorCnpj).addComponent(btnLimpar).addComponent(btnSair))
						.addContainerGap()));
		panel_1.setLayout(gl_panel_1);

		JLabel lblNome = new JLabel("Nome:");

		txtNome = new JTextField();
		txtNome.setColumns(10);

		JLabel lblCnpj = new JLabel("Cnpj:");

		txtCnpj = new JTextField();
		txtCnpj.setColumns(10);

		JLabel lblEndereo = new JLabel("Endere\u00E7o:");

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);

		JLabel lblContato = new JLabel("Contato:");

		txtContato = new JTextField();
		txtContato.setColumns(10);

		lblMensagem = new JLabel("Mensagem:");
		lblMensagem.setForeground(Color.BLUE);
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblNome)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblCnpj)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtCnpj, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblEndereo)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblContato)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtContato, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblMensagem)).addContainerGap(327, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblNome).addComponent(txtNome,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblCnpj).addComponent(txtCnpj,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblEndereo).addComponent(
						txtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblContato).addComponent(
						txtContato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE).addComponent(lblMensagem)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
