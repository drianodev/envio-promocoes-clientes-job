package br.com.drianodev.envioPromocoesClientesJob.dominio;

import lombok.Data;

@Data
public class InteresseProdutoCliente {

	private Cliente cliente;
	private Produto produto;
}
