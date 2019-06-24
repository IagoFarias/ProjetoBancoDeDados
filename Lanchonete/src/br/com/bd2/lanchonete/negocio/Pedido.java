package br.com.bd2.lanchonete.negocio;

public class Pedido {
	Integer numeroPedido;
	String cpfCliente;
	Integer codFuncionario;
	String status;
	Double valor;
	String observacao;
	String dataHora;
	
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public Integer getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	public Integer getCodFuncionario() {
		return codFuncionario;
	}
	public void setCodFuncionario(Integer codFuncionario) {
		this.codFuncionario = codFuncionario;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
