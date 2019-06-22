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
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.bd2.lanchonete.controller.FornecedorController;
import br.com.bd2.lanchonete.controller.IngredienteController;
import br.com.bd2.lanchonete.negocio.Fornecedor;
import br.com.bd2.lanchonete.negocio.Ingrediente;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

public class FrmEstoque extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtQtd;
	private JTextField txtCod;
	private JTable tblConsulta;
	JLabel lblMensagem = new JLabel("Mensagem:");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEstoque frame = new FrmEstoque();
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
	public FrmEstoque() {
		setTitle("Gerenciamento de Estoque");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 633, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 633, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 633, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 631, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tblConsulta = new JTable();
		tblConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer linha = tblConsulta.getSelectedRow();
				
				String codigo = tblConsulta.getValueAt(linha, 0).toString();
				String nome = tblConsulta.getValueAt(linha, 1).toString();
				String quantidade = tblConsulta.getValueAt(linha, 2).toString();
				
				txtNome.setText(nome);
				txtQtd.setText(quantidade);
				txtCod.setText(codigo);
			}
		});
		tblConsulta.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome", "Quantidade em Estoque"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblConsulta.getColumnModel().getColumn(0).setResizable(false);
		tblConsulta.getColumnModel().getColumn(1).setResizable(false);
		tblConsulta.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(tblConsulta);
		panel_2.setLayout(gl_panel_2);
		
		/**** FAZER COM PROCEDURE OU FUNÇÃO ****/
		JButton btnAtualizarQuantidade = new JButton("Atualizar Quantidade");
		btnAtualizarQuantidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngredienteController ingCo = new IngredienteController();
				
				lblMensagem.setText("Mensagem: "+ingCo.atualizaEstoque(Double.parseDouble(txtQtd.getText()),Integer.parseInt(txtCod.getText())));
				
			}
		});
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMensagem.setText("Mensagem:");
				List<Ingrediente> listaIng = new ArrayList<Ingrediente>();
				IngredienteController ingCo = new IngredienteController();

				listaIng = ingCo.listarTodos();

				DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();
				// Limpa a Tabela
				for (int i = tbm.getRowCount() - 1; i >= 0; i--) {
					tbm.removeRow(i);
				}
				// Preenche com os ingrdientes
				int i = 0;
				for (Ingrediente ing : listaIng) {
					tbm.addRow(new String[1]);
					tblConsulta.setValueAt(ing.getCodIngrediente(), i, 0);
					tblConsulta.setValueAt(ing.getNome(), i, 1);
					tblConsulta.setValueAt(ing.getQuantidade(), i, 2);

					i++;
				}
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ingrediente ing = new Ingrediente();
				IngredienteController ingCo = new IngredienteController();
				
				ing.setCodIngrediente(Integer.parseInt(txtCod.getText()));
				
				Object[] opcoes = { "Sim", "Não" };
				int i = JOptionPane.showOptionDialog(null,
						"Deseja excluir esse Ingrediente: " + txtNome.getText() + "?", "Exclusão",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
				if (JOptionPane.YES_OPTION == i) {
					lblMensagem.setText("Mensagem: "+ingCo.excluir(ing));
				}
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtQtd.setText("");
				txtCod.setText("");
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
				FrmEstoque.this.dispose();
			}
		});
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ingrediente ing = new Ingrediente();
				IngredienteController ingCo = new IngredienteController();
				
				ing.setNome(txtNome.getText());
				ing.setQuantidade(Double.parseDouble(txtQtd.getText()));
				
				lblMensagem.setText("Mensagem: "+ingCo.inserir(ing));
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(btnAtualizarQuantidade)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPesquisar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCadastrar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExcluir)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLimpar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSair)
					.addContainerGap(133, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAtualizarQuantidade)
						.addComponent(btnPesquisar)
						.addComponent(btnCadastrar)
						.addComponent(btnExcluir)
						.addComponent(btnLimpar)
						.addComponent(btnSair))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblIngrediente = new JLabel("Ingrediente:");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		
		txtQtd = new JTextField();
		txtQtd.setColumns(10);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		
		txtCod = new JTextField();
		txtCod.setColumns(10);
		
		
		lblMensagem.setForeground(Color.BLUE);
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblIngrediente)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblQuantidade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtQtd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCdigo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblMensagem))
					.addContainerGap(404, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIngrediente)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantidade)
						.addComponent(txtQtd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCdigo)
						.addComponent(txtCod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(lblMensagem))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
