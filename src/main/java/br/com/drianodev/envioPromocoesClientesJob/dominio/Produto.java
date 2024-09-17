package br.com.drianodev.envioPromocoesClientesJob.dominio;

import lombok.Data;

@Data
public class Produto {

	private int id;
	private String nome;
	private String descricao;
	private Double preco;
}
