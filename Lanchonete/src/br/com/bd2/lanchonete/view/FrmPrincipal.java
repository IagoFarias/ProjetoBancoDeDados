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
					frame.setTitle("Lanchonete TOP");
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnGerenciamentoDeClientes = new JButton("Gerenciamento de Clientes");
		btnGerenciamentoDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCliente frame = new FrmCliente();
				frame.setTitle("Gerenciamento de Clientes");
				frame.setVisible(true);
			}
		});
		
		JButton btnGerenciamentoDeFuncionrios = new JButton("Gerenciamento de Funcion\u00E1rios");
		
		JButton btnGerenciamentoDeFornecedores = new JButton("Gerenciamento de Fornecedores");
		
		JButton btnNewButton = new JButton("Gerenciamento de Pedidos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnGerenciamentoDeClientes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGerenciamentoDeFuncionrios)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGerenciamentoDeFornecedores)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addContainerGap(70, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(55)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGerenciamentoDeClientes)
						.addComponent(btnGerenciamentoDeFuncionrios)
						.addComponent(btnGerenciamentoDeFornecedores)
						.addComponent(btnNewButton))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
