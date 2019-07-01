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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.bd2.lanchonete.controller.PedidoController;
import br.com.bd2.lanchonete.negocio.Cliente;
import br.com.bd2.lanchonete.negocio.PedidoEstat;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmEstatPedido extends JFrame {

	private JPanel contentPane;
	private JTable tblConsulta;
	private JTextField txtMes;
	private JTextField txtAno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEstatPedido frame = new FrmEstatPedido();
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
	public FrmEstatPedido() {
		setTitle("Estat\u00EDstica Pedidos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 599, 399);
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
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 573, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 574, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		
		JLabel lblMs = new JLabel("M\u00EAs:");
		
		JLabel lblAno = new JLabel("Ano:");
		
		txtMes = new JTextField();
		txtMes.setColumns(10);
		
		txtAno = new JTextField();
		txtAno.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblMs)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblAno)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(462, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMs)
						.addComponent(txtMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAno)
						.addComponent(txtAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoController pedCo = new PedidoController();
				List<PedidoEstat> listaCli = pedCo.pesquisaEstat();
				
				DefaultTableModel tbm = (DefaultTableModel)tblConsulta.getModel();
				for(int i = tbm.getRowCount()-1; i >= 0; i--){
				tbm.removeRow(i);
				}
				int i = 0;
				for(PedidoEstat pedEs : listaCli ){
				tbm.addRow(new String[1]);
				tblConsulta.setValueAt(pedEs.totalMes, i, 0);
				tblConsulta.setValueAt(pedEs.qtdPedidos, i, 1);
				tblConsulta.setValueAt(pedEs.mes, i, 2);
				tblConsulta.setValueAt(pedEs.ano, i, 3);
				i++;
				}
				
			}
		});
		
		JButton btnPesquisarMsano = new JButton("Pesquisar M\u00EAs/Ano");
		btnPesquisarMsano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoController pedCo = new PedidoController();
				List<PedidoEstat> listaCli = pedCo.pesquisaEstatAnoMes(Integer.parseInt(txtAno.getText()), Integer.parseInt(txtMes.getText()));
				
				DefaultTableModel tbm = (DefaultTableModel)tblConsulta.getModel();
				for(int i = tbm.getRowCount()-1; i >= 0; i--){
				tbm.removeRow(i);
				}
				int i = 0;
				for(PedidoEstat pedEs : listaCli ){
				tbm.addRow(new String[1]);
				tblConsulta.setValueAt(pedEs.totalMes, i, 0);
				tblConsulta.setValueAt(pedEs.qtdPedidos, i, 1);
				tblConsulta.setValueAt(pedEs.mes, i, 2);
				tblConsulta.setValueAt(pedEs.ano, i, 3);
				i++;
				}
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTabela();
				txtMes.setText("");
				txtAno.setText("");
			}
		});
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmEstatPedido.this.dispose();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(btnPesquisar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnPesquisarMsano)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLimpar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSair)
					.addContainerGap(197, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPesquisar)
						.addComponent(btnPesquisarMsano)
						.addComponent(btnLimpar)
						.addComponent(btnSair))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 573, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
		);
		
		tblConsulta = new JTable();
		tblConsulta.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Total M\u00EAs Corrente", "Numero Pedidos", "M\u00EAs", "Ano"
			}
		) {
			Class[] columnTypes = new Class[] {
				Double.class, Integer.class, Object.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, true, false
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
		contentPane.setLayout(gl_contentPane);
	}
}
