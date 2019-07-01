package br.com.bd2.lanchonete.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.bd2.lanchonete.controller.ClienteController;
import br.com.bd2.lanchonete.controller.ItemPedidoController;
import br.com.bd2.lanchonete.controller.ItensCardapioController;
import br.com.bd2.lanchonete.controller.PedidoController;
import br.com.bd2.lanchonete.negocio.Cliente;
import br.com.bd2.lanchonete.negocio.ItemCardapio;
import br.com.bd2.lanchonete.negocio.ItemPedido;
import br.com.bd2.lanchonete.negocio.Pedido;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmItensPedido1 extends JFrame {

	private JPanel contentPane;
	private JTextField txtQtd;
	private JTable tblConsulta;
	private JComboBox cbxItensCardapio;
	private List<ItemCardapio> lista;
	private PedidoController co = new PedidoController();
	private Pedido pedido = co.getUltimo();
	private Double valorTotal=0.0;
	private JTextPane txtObservacoes=new JTextPane();
	private String nomeItem;
	private Integer quantidadeItem;
	private JLabel lblValorTotal = new JLabel("Valor Total : ");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmItensPedido1 frame = new FrmItensPedido1();
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
	public FrmItensPedido1() {
		setTitle("Sele\u00E7\u00E3o de Itens");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 645, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pedido.setValor(valorTotal);
				pedido.setObservacao(txtObservacoes.getText());
				JOptionPane.showMessageDialog(null,co.salvar(pedido));
				FrmItensPedido1.this.dispose();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,co.cancelar());
				FrmItensPedido1.this.dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(19, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(110)
					.addComponent(btnSalvar)
					.addPreferredGap(ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
					.addComponent(btnCancelar)
					.addGap(178))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addContainerGap())
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tblConsulta = new JTable();
		tblConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer linha = tblConsulta.getSelectedRow();
				nomeItem = tblConsulta.getValueAt(linha, 1).toString();
				quantidadeItem=Integer.parseInt(tblConsulta.getValueAt(linha, 2).toString());
			}
		});
		tblConsulta.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo Pedido", "Nome", "Quantidade"
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
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblItemCardpio = new JLabel("Item Card\u00E1pio:");
		
		
		cbxItensCardapio = new JComboBox();
		ItensCardapioController itemCo = new ItensCardapioController();
		lista = itemCo.listarTodos();
		
		for(ItemCardapio i : lista) {
			cbxItensCardapio.addItem(i.getNome());
		}
		
		
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		
		txtQtd = new JTextField();
		txtQtd.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemPedido itemP = new ItemPedido();
				List<ItemPedido> listaPed = new ArrayList<ItemPedido>();
				ItemPedidoController pedCo = new ItemPedidoController();
				int pos=0;
				
				for(ItemCardapio itemC : lista) {
					if(itemC.getNome().equals(cbxItensCardapio.getSelectedItem())) {
						pos = lista.indexOf(itemC);
					}
						
				}
				
				ItemCardapio itemC = lista.get(pos);
				
				itemP.setCodItemCardapio(itemC.getCodItem());
				itemP.setCodPedido(pedido.getNumeroPedido());
				itemP.setQuantidade(Integer.parseInt(txtQtd.getText()));
				
			   System.out.println(pedCo.inserir(itemP)); 
			   
				//atualiza tabela
				listaPed=pedCo.listarItensPedido(pedido.getNumeroPedido());
				
				
				DefaultTableModel tbm = (DefaultTableModel)tblConsulta.getModel();
				
				for(int i = tbm.getRowCount()-1; i >= 0; i--){
				tbm.removeRow(i);
				}
				
				int i = 0;
				for(ItemPedido item : listaPed ){
				tbm.addRow(new String[1]);
				tblConsulta.setValueAt(item.getCodPedido(), i, 0);
				tblConsulta.setValueAt(item.getNomeItem(), i, 1);
				tblConsulta.setValueAt(item.getQuantidade(), i, 2);
	
				i++;
				}
				
				valorTotal=valorTotal+(itemP.getQuantidade()*itemC.getValor());
				
				lblValorTotal.setText("Valor Total : "+valorTotal.toString());
				
			}
		});
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemPedidoController pedCo = new ItemPedidoController();
				ItemPedido itemP = new ItemPedido();
				List<ItemPedido> listaPed = new ArrayList<ItemPedido>();
				
				int pos=0;
				
				for(ItemCardapio itemC : lista) {
					if(itemC.getNome().equals(nomeItem)) {
						pos = lista.indexOf(itemC);
					}
						
				}
				
				ItemCardapio itemC = lista.get(pos);	
				
				itemP.setCodItemCardapio(itemC.getCodItem());
				itemP.setCodPedido(pedido.getNumeroPedido());
				
				System.out.println(pedCo.excluir(itemP));
				
				valorTotal=valorTotal-(itemC.getValor()*quantidadeItem);
				
				//atualiza tabela
				listaPed=pedCo.listarItensPedido(pedido.getNumeroPedido());
				
				
				DefaultTableModel tbm = (DefaultTableModel)tblConsulta.getModel();
				
				for(int i = tbm.getRowCount()-1; i >= 0; i--){
				tbm.removeRow(i);
				}
				
				int i = 0;
				for(ItemPedido item : listaPed ){
				tbm.addRow(new String[1]);
				tblConsulta.setValueAt(item.getCodPedido(), i, 0);
				tblConsulta.setValueAt(item.getNomeItem(), i, 1);
				tblConsulta.setValueAt(item.getQuantidade(), i, 2);
	
				i++;
				}
				
				lblValorTotal.setText("Valor Total : "+valorTotal.toString());
			}
		});
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es:");
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblObservaes)
						.addComponent(txtObservacoes, GroupLayout.PREFERRED_SIZE, 594, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblItemCardpio)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cbxItensCardapio, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnAdicionar)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemover)))
							.addGap(31)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblValorTotal)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblQuantidade)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtQtd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblItemCardpio)
						.addComponent(cbxItensCardapio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuantidade)
						.addComponent(txtQtd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionar)
						.addComponent(btnRemover)
						.addComponent(lblValorTotal))
					.addGap(18)
					.addComponent(lblObservaes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtObservacoes, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
