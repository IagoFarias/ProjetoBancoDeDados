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
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.bd2.lanchonete.controller.ClienteController;
import br.com.bd2.lanchonete.controller.PedidoController;
import br.com.bd2.lanchonete.negocio.ClienteEstat;
import br.com.bd2.lanchonete.negocio.PedidoEstat;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmEstatCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCpf;
	private JTable tblConsulta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEstatCliente frame = new FrmEstatCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limparTabela() {
		DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();

		for (int i = tbm.getRowCount() - 1; i >= 0; i--) {
			tbm.removeRow(i);
		}

	}

	/**
	 * Create the frame.
	 */
	public FrmEstatCliente() {
		setTitle("Estat\u00EDstica Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 598, 370);
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
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 571, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tblConsulta = new JTable();
		tblConsulta.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "CPF Cliente", "N\u00FAmero de Pedidos", "Total Gasto"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
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
		
		JLabel lblCpfCliente = new JLabel("CPF Cliente:");
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblCpfCliente)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(348, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpfCliente)
						.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteController pedCo = new ClienteController();
				List<ClienteEstat> listaCli = pedCo.pesquisarEstat();
				
				DefaultTableModel tbm = (DefaultTableModel)tblConsulta.getModel();
				for(int i = tbm.getRowCount()-1; i >= 0; i--){
				tbm.removeRow(i);
				}
				int i = 0;
				for(ClienteEstat cliEst : listaCli ){
				tbm.addRow(new String[1]);
				tblConsulta.setValueAt(cliEst.nome, i, 0);
				tblConsulta.setValueAt(cliEst.cpf, i, 1);
				tblConsulta.setValueAt(cliEst.qtdPedidos, i, 2);
				tblConsulta.setValueAt(cliEst.totalGasto, i, 3);
				i++;
				}
			}
		});
		
		JButton btnPesquisarPorCpf = new JButton("Pesquisar Por CPF");
		btnPesquisarPorCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteController pedCo = new ClienteController();
				List<ClienteEstat> listaCli = pedCo.pesquisarEstatCpf(txtCpf.getText());
				
				DefaultTableModel tbm = (DefaultTableModel)tblConsulta.getModel();
				for(int i = tbm.getRowCount()-1; i >= 0; i--){
				tbm.removeRow(i);
				}
				int i = 0;
				for(ClienteEstat cliEst : listaCli ){
				tbm.addRow(new String[1]);
				tblConsulta.setValueAt(cliEst.nome, i, 0);
				tblConsulta.setValueAt(cliEst.cpf, i, 1);
				tblConsulta.setValueAt(cliEst.qtdPedidos, i, 2);
				tblConsulta.setValueAt(cliEst.totalGasto, i, 3);
				i++;
				}
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTabela();
				txtCpf.setText("");
			}
		});
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmEstatCliente.this.dispose();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(btnPesquisar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPesquisarPorCpf)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLimpar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSair)
					.addContainerGap(203, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPesquisar)
						.addComponent(btnPesquisarPorCpf)
						.addComponent(btnLimpar)
						.addComponent(btnSair))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
	}

}
