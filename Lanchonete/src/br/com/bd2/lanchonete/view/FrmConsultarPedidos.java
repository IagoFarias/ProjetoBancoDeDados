package br.com.bd2.lanchonete.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.bd2.lanchonete.controller.PedidoController;
import br.com.bd2.lanchonete.negocio.ItemPedido;
import br.com.bd2.lanchonete.negocio.Pedido;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmConsultarPedidos extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumPedido;
	private JTextField txtCpfCliente;
	private JTextField txtCodFuncionario;
	private JTable tblConsulta;
	private String statusPedido = "Pendente";
	private String statusPedEsp;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultarPedidos frame = new FrmConsultarPedidos();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
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
	public FrmConsultarPedidos() {
		setTitle("Pedidos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1210, 501);
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
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 778, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(14, Short.MAX_VALUE)));

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		tblConsulta = new JTable();
		tblConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer linha = tblConsulta.getSelectedRow();
				String numeroPedido = tblConsulta.getValueAt(linha, 0).toString();
				String cpfCliente = tblConsulta.getValueAt(linha, 1).toString();
				String codFuncionario = tblConsulta.getValueAt(linha, 2).toString();
				statusPedEsp = tblConsulta.getValueAt(linha, 3).toString();

				txtNumPedido.setText(numeroPedido);
				txtCpfCliente.setText(cpfCliente);
				txtCodFuncionario.setText(codFuncionario);
			}
		});
		tblConsulta.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "N\u00FAmero Pedido",
				"CPF Cliente", "C\u00F3digo Funcion\u00E1rio", "Status", "Valor", "Data Hora" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, Integer.class, String.class, Double.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblConsulta.getColumnModel().getColumn(0).setResizable(false);
		tblConsulta.getColumnModel().getColumn(1).setResizable(false);
		tblConsulta.getColumnModel().getColumn(2).setResizable(false);
		tblConsulta.getColumnModel().getColumn(3).setResizable(false);
		tblConsulta.getColumnModel().getColumn(4).setResizable(false);
		tblConsulta.getColumnModel().getColumn(5).setResizable(false);
		scrollPane.setViewportView(tblConsulta);
		panel_2.setLayout(gl_panel_2);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoController pedCo = new PedidoController();

				List<Pedido> listaPed = pedCo.listarPedidos(statusPedido);

				DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();

				limparTabela();

				int i = 0;
				for (Pedido ped : listaPed) {
					tbm.addRow(new String[1]);
					tblConsulta.setValueAt(ped.getNumeroPedido(), i, 0);
					tblConsulta.setValueAt(ped.getCpfCliente(), i, 1);
					tblConsulta.setValueAt(ped.getCodFuncionario(), i, 2);
					tblConsulta.setValueAt(ped.getStatus(), i, 3);
					tblConsulta.setValueAt(ped.getValor(), i, 4);
					tblConsulta.setValueAt(ped.getDataHora(), i, 5);

					i++;
				}
			}
		});

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTabela();

				txtNumPedido.setText("");
				txtCpfCliente.setText("");
				txtCodFuncionario.setText("");
			}
		});

		JButton btnPesquisarPorCpf = new JButton("Pesquisar Por CPF Cliente");
		btnPesquisarPorCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoController pedCo = new PedidoController();

				List<Pedido> listaPed = pedCo.pesquisarPorCpfCliente(txtCpfCliente.getText(), statusPedido);

				DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();

				limparTabela();

				int i = 0;
				for (Pedido ped : listaPed) {
					tbm.addRow(new String[1]);
					tblConsulta.setValueAt(ped.getNumeroPedido(), i, 0);
					tblConsulta.setValueAt(ped.getCpfCliente(), i, 1);
					tblConsulta.setValueAt(ped.getCodFuncionario(), i, 2);
					tblConsulta.setValueAt(ped.getStatus(), i, 3);
					tblConsulta.setValueAt(ped.getValor(), i, 4);
					tblConsulta.setValueAt(ped.getDataHora(), i, 5);

					i++;
				}

			}
		});

		JButton btnPesquisarPorCdigo = new JButton("Pesquisar Por C\u00F3digo Funcion\u00E1rio");
		btnPesquisarPorCdigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoController pedCo = new PedidoController();
				Integer cod = Integer.parseInt(txtCodFuncionario.getText());
				List<Pedido> listaPed = pedCo.pesquisarPorCodigoFuncionario(cod, statusPedido);

				DefaultTableModel tbm = (DefaultTableModel) tblConsulta.getModel();

				limparTabela();

				int i = 0;
				for (Pedido ped : listaPed) {
					tbm.addRow(new String[1]);
					tblConsulta.setValueAt(ped.getNumeroPedido(), i, 0);
					tblConsulta.setValueAt(ped.getCpfCliente(), i, 1);
					tblConsulta.setValueAt(ped.getCodFuncionario(), i, 2);
					tblConsulta.setValueAt(ped.getStatus(), i, 3);
					tblConsulta.setValueAt(ped.getValor(), i, 4);
					tblConsulta.setValueAt(ped.getDataHora(), i, 5);

					i++;
				}
			}
		});

		JButton btnCancelarPedido = new JButton("Cancelar Pedido");
		btnCancelarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (statusPedEsp.equals("Pendente")) {
					Pedido ped = new Pedido();
					PedidoController pedCo = new PedidoController();

					ped.setNumeroPedido(Integer.parseInt(txtNumPedido.getText()));

					Object[] opcoes = { "Sim", "Não" };
					int i = JOptionPane.showOptionDialog(null,
							"Deseja excluir o Pedido Número: " + txtNumPedido.getText() + "?", "Exclusão",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
					if (JOptionPane.YES_OPTION == i) {
						JOptionPane.showMessageDialog(null, pedCo.excluir(ped));
					}
				}

				else {
					JOptionPane.showMessageDialog(null,
							"Para cancelar um pedido ele deve ter como status \"Pendente\"!");
				}
			}
		});

		JButton btnVerObservacoes = new JButton("Ver Observa\u00E7\u00F5es");
		btnVerObservacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoController pedCo = new PedidoController();
				Integer nPed = Integer.parseInt(txtNumPedido.getText());
				
				JOptionPane.showMessageDialog(null,"Número do Pedido: "+nPed+"\nItens:\n "+pedCo.visualizaItensPedido(nPed)+"\n Observações: \n"+pedCo.visualizaObservacao(nPed));
			}
		});

		JButton btnNewButton = new JButton("Finalizar Pedido");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (statusPedEsp.equals("Em Andamento")) {
					Pedido ped = new Pedido();
					PedidoController pedCo = new PedidoController();

					ped.setNumeroPedido(Integer.parseInt(txtNumPedido.getText()));
					ped.setStatus("Concluído");

					JOptionPane.showMessageDialog(null, pedCo.executarPedido(ped));
					limparTabela();
				}

				else {
					JOptionPane.showMessageDialog(null,
							"Para finalizar um pedido ele deve ter como status \"Em Andamento\"!");
				}
			}
		});

		JButton btnExecutarPedido = new JButton("Executar Pedido");
		btnExecutarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (statusPedEsp.equals("Pendente")) {
					Pedido ped = new Pedido();
					PedidoController pedCo = new PedidoController();

					ped.setNumeroPedido(Integer.parseInt(txtNumPedido.getText()));
					ped.setStatus("Em Andamento");

					JOptionPane.showMessageDialog(null, pedCo.executarPedido(ped));
					limparTabela();
				}

				else {
					JOptionPane.showMessageDialog(null,
							"Para iniciar a execução de um pedido ele deve ter como status \"Pendente\"!");
				}
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
					.addComponent(btnPesquisarPorCdigo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancelarPedido)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVerObservacoes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLimpar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnExecutarPedido)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addContainerGap(118, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPesquisar)
						.addComponent(btnPesquisarPorCpf)
						.addComponent(btnPesquisarPorCdigo)
						.addComponent(btnCancelarPedido)
						.addComponent(btnVerObservacoes)
						.addComponent(btnLimpar)
						.addComponent(btnExecutarPedido)
						.addComponent(btnNewButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);

		JLabel lblNmeroPedido = new JLabel("N\u00FAmero Pedido:");

		JLabel lblCpfCliente = new JLabel("CPF Cliente:");

		JLabel lblCdigoFuncionrio = new JLabel("C\u00F3digo Funcion\u00E1rio:");

		txtNumPedido = new JTextField();
		txtNumPedido.setColumns(10);

		txtCpfCliente = new JTextField();
		txtCpfCliente.setColumns(10);

		txtCodFuncionario = new JTextField();
		txtCodFuncionario.setColumns(10);

		JRadioButton btnRadioPendente = new JRadioButton("Pendente");
		btnRadioPendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusPedido = "Pendente";
			}
		});
		btnRadioPendente.setSelected(true);

		JRadioButton btnRadioAndamento = new JRadioButton("Em Andamento");
		btnRadioAndamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusPedido = "Em Andamento";
			}
		});

		JRadioButton btnRadioConcluido = new JRadioButton("Conclu\u00EDdo");
		btnRadioConcluido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusPedido = "Concluído";
			}
		});

		ButtonGroup bg = new ButtonGroup();

		bg.add(btnRadioPendente);
		bg.add(btnRadioAndamento);
		bg.add(btnRadioConcluido);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblNmeroPedido)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtNumPedido, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblCpfCliente)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtCpfCliente,
										GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblCdigoFuncionrio)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtCodFuncionario,
										GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addComponent(btnRadioPendente)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnRadioAndamento)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnRadioConcluido)))
						.addContainerGap(598, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNmeroPedido).addComponent(
						txtNumPedido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel
						.createParallelGroup(Alignment.BASELINE).addComponent(lblCpfCliente).addComponent(txtCpfCliente,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblCdigoFuncionrio)
						.addComponent(txtCodFuncionario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnRadioPendente)
						.addComponent(btnRadioAndamento).addComponent(btnRadioConcluido))));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
