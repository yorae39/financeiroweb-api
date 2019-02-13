package com.example.financeirowebapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lancamento")
public class Lancamento implements Serializable{

	private static final long serialVersionUID = -5105517627986763330L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String descricao;
	@Column(name = "data_vencimento")
	private Date datVencimento;
	@Column(name = "data_pagamento")
	private Date dataPagamento;	
	private BigDecimal valor;	
	private String observacao;
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipoLancamento;
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
	public Lancamento() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDatVencimento() {
		return datVencimento;
	}

	public void setDatVencimento(Date datVencimento) {
		this.datVencimento = datVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", descricao=" + descricao + ", datVencimento=" + datVencimento
				+ ", dataPagamento=" + dataPagamento + ", valor=" + valor + ", observacao=" + observacao
				+ ", tipoLancamento=" + tipoLancamento + ", categoria=" + categoria + ", pessoa=" + pessoa + "]";
	}
	
	
}
