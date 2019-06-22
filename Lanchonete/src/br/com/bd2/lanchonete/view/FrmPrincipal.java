package br.com.bd2.lanchonete.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class FrmPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
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
	public FrmPrincipal() {
		setTitle("Top Lanches");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 314);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenus = new JMenu("Menus");
		menuBar.add(mnMenus);
		
		JMenuItem mntmGerenciamentoDeClientes = new JMenuItem("Gerenciamento de Clientes");
		mntmGerenciamentoDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCliente fcli = new FrmCliente();
				fcli.setVisible(true);
			}
		});
		mnMenus.add(mntmGerenciamentoDeClientes);
		
		JMenuItem mntmGerenciamentoDeFuncionrios = new JMenuItem("Gerenciamento de Funcion\u00E1rios");
		mntmGerenciamentoDeFuncionrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmFuncionario ffun = new FrmFuncionario();
				ffun.setVisible(true);
			}
		});
		mnMenus.add(mntmGerenciamentoDeFuncionrios);
		
		JMenuItem mntmGerenciamentoDeFornecedores = new JMenuItem("Gerenciamento de Fornecedores");
		mntmGerenciamentoDeFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmFornecedor fforn = new FrmFornecedor();
				fforn.setVisible(true);
			}
		});
		mnMenus.add(mntmGerenciamentoDeFornecedores);
		
		JMenuItem mntmGerenciamentoDeEstoque = new JMenuItem("Gerenciamento de Estoque");
		mntmGerenciamentoDeEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmEstoque festq = new FrmEstoque();
				festq.setVisible(true);
			}
		});
		mnMenus.add(mntmGerenciamentoDeEstoque);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTopLanches = new JLabel("Top Lanches");
		lblTopLanches.setForeground(Color.BLUE);
		lblTopLanches.setFont(new Font("Vineta BT", Font.PLAIN, 35));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(91)
					.addComponent(lblTopLanches)
					.addContainerGap(97, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(83)
					.addComponent(lblTopLanches)
					.addContainerGap(112, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
