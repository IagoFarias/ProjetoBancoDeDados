package br.com.bd2.lanchonete.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.bd2.lanchonete.controller.FuncionarioController;
import br.com.bd2.lanchonete.negocio.Cliente;
import br.com.bd2.lanchonete.negocio.Funcionario;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEndereco;
	private JTextField txtContato;
	private JTable tblConsulta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmFuncionario frame = new FrmFuncionario();
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
	public FrmFuncionario() {
		setTitle("Gerenciamento de Funcion\u00E1rios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 755, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 664, Short.MAX_VALUE))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(14, Short.MAX_VALUE)));

		JLabel lblNome = new JLabel("Nome:");

		JLabel lblNewLabel = new JLabel("CPF:");

		JLabel lblEndereo = new JLabel("Endere\u00E7o:");

		JLabel lblContato = new JLabel("Contato:");

		txtNome = new JTextField();
		txtNome.setColumns(10);

		txtCpf = new JTextField();
		txtCpf.setColumns(10);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);

		txtContato = new JTextField();
		txtContato.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblNome)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblContato, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtContato, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblEndereo)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtEndereco,
										GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(253, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblNome).addComponent(txtNome,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel).addComponent(txtCpf,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblEndereo).addComponent(
						txtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblContato).addComponent(
						txtContato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		JButton btnInserir = new JButton("Cadastrar");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario func = new Funcionario();
				FuncionarioController funcCO = new FuncionarioController();

				func.setNome(txtNome.getText());
				func.setCpf(txtCpf.getText());
				func.setEndereco(txtEndereco.getText());
				func.setContato(txtContato.getText());

				funcCO.inserir(func);

			}
		});

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioController funcCO = new FuncionarioController();

				Funcionario func = funcCO.pesquisarPorCpf(txtCpf.getText());

				if (func == null) {
					JOptionPane.showMessageDialog(null, "Funcionário Não Encontrado!");

				} else {
					Object[] opcoes = { "Sim", "Não" };
					int i = JOptionPane.showOptionDialog(null,
							"Deseja excluir esse Funcionário: " + txtNome.getText() + "?", "Exclusão",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
					if (JOptionPane.YES_OPTION == i) {
						funcCO.excluir(func);
					}
				}

			}
		});

		JButton btnAtualizarDados = new JButton("Atualizar Dados");
		btnAtualizarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario func = new Funcionario();
				FuncionarioController funcCO = new FuncionarioController();

				func.setNome(txtNome.getText());
				func.setCpf(txtCpf.getText());
				func.setEndereco(txtEndereco.getText());
				func.setContato(txtContato.getText());
				
				funcCO.alterar(func);
			}
		});

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Funcionario> listaFunc = new ArrayList<Funcionario>();
				FuncionarioController funcCO = new FuncionarioController();

				listaFunc = funcCO.listarTodos();

				DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
				// Limpa a Tabela
				for (int i = tbm.getRowCount() - 1; i >= 0; i--) {
					tbm.removeRow(i);
				}
				// Preenche com os funcionários
				int i = 0;
				for (Funcionario func : listaFunc) {
					tbm.addRow(new String[1]);
					tblConsulta.setValueAt(func.getCodFuncionario(), i, 0);
					tblConsulta.setValueAt(func.getNome(), i, 1);
					tblConsulta.setValueAt(func.getCpf(), i, 2);
					tblConsulta.setValueAt(func.getEndereco(), i, 3);
					tblConsulta.setValueAt(func.getContato(), i, 4);
					i++;
				}
			}
		});

		JButton btnPesquisarPorCpf = new JButton("Pesquisar Por CPF");
		btnPesquisarPorCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioController funcCO = new FuncionarioController();
				Funcionario func = funcCO.pesquisarPorCpf(txtCpf.getText());
				
				if(func == null)
				{
					JOptionPane.showMessageDialog(null,"Funcionário Não Encontrado!");
					
				}
				else {
				txtNome.setText(func.getNome());
				txtCpf.setText(func.getCpf());
				txtContato.setText(func.getContato());
				txtEndereco.setText(func.getEndereco());
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

				DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
				for (int i = tbm.getRowCount() - 1; i >= 0; i--) {
					tbm.removeRow(i);
				}
			}
		});

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmFuncionario.this.dispose();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addComponent(btnInserir)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnAtualizarDados)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnExcluir)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnPesquisar)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnPesquisarPorCpf)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnLimpar)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSair)
						.addContainerGap(27, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnInserir)
								.addComponent(btnAtualizarDados).addComponent(btnExcluir).addComponent(btnPesquisar)
								.addComponent(btnPesquisarPorCpf).addComponent(btnLimpar).addComponent(btnSair))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		tblConsulta = new JTable();
		tblConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer linha = tblConsulta.getSelectedRow();
				String nome = tblConsulta.getValueAt(linha, 1).toString();
				String cpf = tblConsulta.getValueAt(linha, 2).toString();
				String endereco = tblConsulta.getValueAt(linha, 3).toString();
				String contato = tblConsulta.getValueAt(linha, 4).toString();
				
				txtNome.setText(nome);
				txtCpf.setText(cpf);
				txtContato.setText(contato);
				txtEndereco.setText(endereco);
			}
		});
		tblConsulta.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Nome", "CPF", "Endere\u00E7o", "Fones" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblConsulta.getColumnModel().getColumn(0).setResizable(false);
		tblConsulta.getColumnModel().getColumn(1).setResizable(false);
		tblConsulta.getColumnModel().getColumn(2).setResizable(false);
		tblConsulta.getColumnModel().getColumn(3).setResizable(false);
		tblConsulta.getColumnModel().getColumn(4).setResizable(false);
		scrollPane.setViewportView(tblConsulta);
		panel_2.setLayout(gl_panel_2);
		contentPane.setLayout(gl_contentPane);
	}
}
