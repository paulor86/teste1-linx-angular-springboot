package com.linx.teste.entities.enums;

public enum StatusPedido {
	
	AGUARDANDO_PAGAMENTO(1),
	PAGAMENTO_EFETUADO(2),
	PEDIDO_ENVIADO(3),
	PEDIDO_ENTREGEUE(4),
	PEDIDO_CANCELADO(5);
	
	private int codigo;
	
	private StatusPedido(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public static StatusPedido valueOf(int codigo) {
		for (StatusPedido value : StatusPedido.values()) {
			if (value.getCodigo() == codigo) {
				return value;
			}
		}
		throw new IllegalArgumentException("Status do Código Invalido");
	}
	
	
}
