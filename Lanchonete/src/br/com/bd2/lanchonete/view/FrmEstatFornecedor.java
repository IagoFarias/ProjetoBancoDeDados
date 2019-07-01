package br.com.bd2.lanchonete.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.bd2.lanchonete.controller.FornecedorController;
import br.com.bd2.lanchonete.negocio.ClienteEstat;
import br.com.bd2.lanchonete.negocio.FornecedorEstat;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmEstatFornecedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeFornecedor;
	private JTable tblConsulta;
	private JTextField txtNomeIngrediente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEstatFornecedor frame = new FrmEstatFornecedor();
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
	public FrmEstatFornecedor() {
		setTitle("Fornecimento Estoque");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 713, 396);
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
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 677, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		
		JLabel lblCdigoIngrediente = new JLabel("Nome Fornecedor:");
		
		txtNomeFornecedor = new JTextField();
		txtNomeFornecedor.setColumns(10);
		
		JLabel lblNomeIngrediente = new JLabel("Nome Ingrediente:");
		
		txtNomeIngrediente = new JTextField();
		txtNomeIngrediente.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNomeIngrediente)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNomeIngrediente))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCdigoIngrediente)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNomeFornecedor, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(320, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCdigoIngrediente)
						.addComponent(txtNomeFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNomeIngrediente)
						.addComponent(txtNomeIngrediente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		panel.setLayout(gl_panel);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FornecedorController fornCo = new FornecedorController();
				List<FornecedorEstat> listaFornEst= fornCo.pesquisarEstat();
				
				DefaultTableModel tbm = (DefaultTableModel)tblConsulta.getModel();
				for(int i = tbm.getRowCount()-1; i >= 0; i--){
				tbm.removeRow(i);
				}
				int i = 0;
				for(FornecedorEstat fornEst : listaFornEst ){
				tbm.addRow(new String[1]);
				tblConsulta.setValueAt(fornEst.nomeIngrediente, i, 0);
				tblConsulta.setValueAt(fornEst.qtdEstoque, i, 1);
				tblConsulta.setValueAt(fornEst.nomeFornecedor, i, 2);
				tblConsulta.setValueAt(fornEst.contato, i, 3);
				tblConsulta.setValueAt(fornEst.cNPJ, i, 4);
				i++;
				}
				
			}
		});
		
		JButton btnPesquisarComCdigo = new JButton("Pesquisar Por Ingrediente");
		btnPesquisarComCdigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FornecedorController fornCo = new FornecedorController();
				List<FornecedorEstat> listaFornEst= fornCo.pesquisarEstatNomeIng(txtNomeIngrediente.getText());
				
				DefaultTableModel tbm = (DefaultTableModel)tblConsulta.getModel();
				for(int i = tbm.getRowCount()-1; i >= 0; i--){
				tbm.removeRow(i);
				}
				int i = 0;
				for(FornecedorEstat fornEst : listaFornEst ){
				tbm.addRow(new String[1]);
				tblConsulta.setValueAt(fornEst.nomeIngrediente, i, 0);
				tblConsulta.setValueAt(fornEst.qtdEstoque, i, 1);
				tblConsulta.setValueAt(fornEst.nomeFornecedor, i, 2);
				tblConsulta.setValueAt(fornEst.contato, i, 3);
				tblConsulta.setValueAt(fornEst.cNPJ, i, 4);
				i++;
				}
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tbm = (DefaultTableModel)tblConsulta.getModel();
				for(int i = tbm.getRowCount()-1; i >= 0; i--){
				tbm.removeRow(i);
				}
				txtNomeIngrediente.setText("");
				txtNomeFornecedor.setText("");
			}
		});
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmEstatFornecedor.this.dispose();			}
		});
		
		JButton btnPesquisarPorFornecedor = new JButton("Pesquisar Por Fornecedor");
		btnPesquisarPorFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FornecedorController fornCo = new FornecedorController();
				List<FornecedorEstat> listaFornEst= fornCo.pesquisarEstatNomeForn(txtNomeFornecedor.getText());
				
				DefaultTableModel tbm = (DefaultTableModel)tblConsulta.getModel();
				for(int i = tbm.getRowCount()-1; i >= 0; i--){
				tbm.removeRow(i);
				}
				int i = 0;
				for(FornecedorEstat fornEst : listaFornEst ){
				tbm.addRow(new String[1]);
				tblConsulta.setValueAt(fornEst.nomeIngrediente, i, 0);
				tblConsulta.setValueAt(fornEst.qtdEstoque, i, 1);
				tblConsulta.setValueAt(fornEst.nomeFornecedor, i, 2);
				tblConsulta.setValueAt(fornEst.contato, i, 3);
				tblConsulta.setValueAt(fornEst.cNPJ, i, 4);
				i++;
				}
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(btnPesquisar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPesquisarComCdigo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPesquisarPorFornecedor)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLimpar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSair)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPesquisar)
						.addComponent(btnPesquisarComCdigo)
						.addComponent(btnPesquisarPorFornecedor)
						.addComponent(btnLimpar)
						.addComponent(btnSair))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 677, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tblConsulta = new JTable();
		tblConsulta.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome Ingrediente", "Qtde. Estoque", "Nome Fornecedor", "Contato", "CNPJ"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Double.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
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
